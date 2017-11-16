package app.web.message_board.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import app.web.message_board.entity.Message;
import app.web.message_board.form.MessageEditForm;
import app.web.message_board.form.MessageRegisterForm;
import app.web.message_board.service.MessageService;

/**
 * メッセージ関連の画面群のコントローラ。
 */
@Controller
@RequestMapping("/messages")
public class MessageController {

    //@SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    @Qualifier("validator")
    private SmartValidator validator;

    //@Autowired
    //private MessageEditFormValidator messageEditFormValidator;

    /**
     * MessageEditForm用のバリデータを登録する。
     */
    @InitBinder("messageEditForm")
    protected void initBinderForMessageEditForm(WebDataBinder binder) {
        //binder.addValidators(messageEditFormValidator);
    }

    /**
     * メッセージ一覧画面を表示する。
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Message> messages = new ArrayList<Message>();
        String errorMessage = null;

        try {
            messages = messageService.getAllMessages();
        } catch (Exception e) {
            logger.error("Failed to load messages", e);
            errorMessage = "データの読み込みに失敗しました。";
        }

        model.addAttribute("messages", messages);
        model.addAttribute("errorMessage", errorMessage);

        // メッセージ一覧画面を表示
        return "messages/list";
    }

    /**
     * 新規メッセージ画面の初期表示。
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        Message message = new Message();
        message.setValidPeriodInDays(5);

        MessageRegisterForm form = new MessageRegisterForm();
        form.setMessage(message);

        // 新規メッセージ画面を表示
        model.addAttribute("messageRegisterForm", form);
        return "messages/register_entry";
    }

    /**
     * 新規メッセージ画面で確認ボタンが押下された場合の処理。
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "confirm")
    public String registerConfirm(Model model,
            @Validated @ModelAttribute("messageRegisterForm") MessageRegisterForm form, BindingResult result) {
        if (result.hasErrors()) {
            // 新規メッセージ画面を再表示
            return "messages/register_entry";
        }

        // 新規メッセージ確認画面を表示
        return "messages/register_confirm";
    }

    /**
     * 新規メッセージ確認画面で修正ボタンが押下された場合の処理。
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "redo")
    public String registerRedo(Model model, @ModelAttribute("messageRegisterForm") MessageRegisterForm form,
            BindingResult result) {
        // 新規メッセージ画面を表示
        return "messages/register_entry";
    }

    /**
     * 新規メッセージ確認画面で登録ボタンが押下された場合の処理。
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "commit")
    public String registerCommit(Model model,
            @Validated @ModelAttribute("messageRegisterForm") MessageRegisterForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // 新規メッセージ画面を表示
            return "messages/register_entry";
        }

        // メッセージを保存する
        boolean isMessageSaved = false;
        String errorMessage = null;

        try {
            Message message = new Message();

            message.setTitle(form.getMessage().getTitle());
            message.setContent(form.getMessage().getContent());
            message.setValidPeriodInDays(form.getMessage().getValidPeriodInDays());

            messageService.saveMessage(message);
            isMessageSaved = true;
        } catch (Exception e) {
            logger.error("Failed to save a message", e);
            errorMessage = "登録に失敗しました。";
        }

        if (isMessageSaved) {
            // 新規メッセージ登録完了画面を表示
            redirectAttributes.addFlashAttribute("messageRegisterForm", form);
            return "redirect:/messages/registercompleted";
        } else {
            // 新規メッセージ確認画面を再表示
            model.addAttribute("errorMessage", errorMessage);
            return "messages/register_confirm";
        }
    }

    /**
     * 新規メッセージ登録完了画面を表示する。
     */
    @RequestMapping(value = "/registercompleted", method = RequestMethod.GET)
    public String registerCompleted(Model model) {
        // Note that 'model' should contain 'messageRegisterForm' passed via flash attributes.
        return "messages/registercompleted";
    }

    /**
     * メッセージ編集画面の初期表示。
     */
    //@RequestMapping(value = "/id/{messageId}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("messageId") long messageId) {
        Message message = null;
        String errorMessage = null;
        boolean disableForm = false;

        try {
            message = messageService.findMessage(messageId);

            if (message == null) {
                errorMessage = "該当データがありません。";
            }
        } catch (Exception e) {
            logger.error("Failed to load a message", e);
            errorMessage = "データの読み込みに失敗しました。";
        }

        MessageEditForm form = new MessageEditForm();

        if (message == null) {
            disableForm = true;
        } else {
            form.setMessage(message);
        }

        model.addAttribute("messageEditForm", form);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("disableForm", disableForm);

        // メッセージ編集画面を表示
        return "messages/edit";
    }

    /**
     * メッセージ編集画面で更新ボタンが押下された場合の処理。
     */
    //@RequestMapping(value = "/id/{messageId}/edit", method = RequestMethod.POST, params = "commit")
    public String editCommit(Model model, @PathVariable("messageId") long messageId,
            @Validated @ModelAttribute("messageEditForm") MessageEditForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        String errorMessage = null;
        boolean disableForm = false;

        if (!result.hasErrors()) {
            // メッセージを更新する
            try {
                Message message = messageService.findMessage(messageId);

                if (message == null) {
                    errorMessage = "該当データがありません。";
                    disableForm = true;
                } else {
                    Message messageInForm = form.getMessage();

                    message.setTitle(messageInForm.getTitle());
                    message.setContent(messageInForm.getContent());
                    message.setValidPeriodInDays(messageInForm.getValidPeriodInDays());

                    messageService.updateMessage(message);

                    // メッセージ編集完了画面を表示
                    return "redirect:/messages/id/{messageId}/editcompleted";
                }
            } catch (Exception e) {
                logger.error("Failed to update a message", e);
                errorMessage = "更新に失敗しました。";
            }
        }

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("disableForm", disableForm);

        // メッセージ編集画面を再表示
        return "messages/edit";
    }

    /**
     * メッセージ編集画面。
     */
    @RequestMapping(value = "/id/{messageId}/edit", method = { RequestMethod.GET, RequestMethod.POST })
    public String editScreen(Model model, HttpMethod httpMethod, @PathVariable("messageId") long messageId,
            @ModelAttribute("messageEditForm") MessageEditForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        String errorMessage = null;
        boolean disableForm = true;

        try {
            Message message = messageService.findMessage(messageId);

            if (message == null) {
                errorMessage = "該当データがありません。";
            } else {
                disableForm = false;

                if (httpMethod == HttpMethod.GET) {
                    form.setMessage(message);
                } else if (httpMethod == HttpMethod.POST) {
                    validator.validate(form, result);

                    if (!result.hasErrors()) {
                        // メッセージを更新する
                        Message messageInForm = form.getMessage();

                        message.setTitle(messageInForm.getTitle());
                        message.setContent(messageInForm.getContent());
                        message.setValidPeriodInDays(messageInForm.getValidPeriodInDays());

                        messageService.updateMessage(message);

                        // メッセージ編集完了画面を表示
                        return "redirect:/messages/id/{messageId}/editcompleted";
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Failed to load/update a message", e);
            errorMessage = "エラーが発生しました。";
        }

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("disableForm", disableForm);

        // メッセージ編集画面を表示
        return "messages/edit";
    }

    /**
     * メッセージ編集完了画面を表示する。
     */
    @RequestMapping(value = "/id/{messageId}/editcompleted", method = RequestMethod.GET)
    public String editCompleted(Model model, @PathVariable("messageId") long messageId) {
        return "messages/editcompleted";
    }

    /**
     * メッセージ削除画面の初期表示。
     */
    @RequestMapping(value = "/id/{messageId}/delete", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("messageId") long messageId) {
        Message message = null;
        String errorMessage = null;

        try {
            message = messageService.findMessage(messageId);

            if (message == null) {
                errorMessage = "該当データがありません。";
            }
        } catch (Exception e) {
            logger.error("Failed to load a message", e);
            errorMessage = "データの読み込みに失敗しました。";
        }

        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        // メッセージ削除画面を表示
        return "messages/delete";
    }

    /**
     * メッセージ削除画面で削除ボタンが押下された場合の処理。
     */
    @RequestMapping(value = "/id/{messageId}/delete", method = RequestMethod.POST, params = "commit")
    public String deleteCommit(Model model, @PathVariable("messageId") long messageId,
            RedirectAttributes redirectAttributes) {
        Message message = null;
        String errorMessage = null;

        // メッセージを削除する
        try {
            message = messageService.findMessage(messageId);

            if (message == null) {
                errorMessage = "該当データがありません。";
            } else {
                messageService.deleteMessage(messageId);

                // メッセージ削除完了画面を表示
                return "redirect:/messages/id/{messageId}/deletecompleted";
            }
        } catch (Exception e) {
            logger.error("Failed to delete a message", e);
            errorMessage = "削除に失敗しました。";
        }

        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        // メッセージ削除画面を再表示
        return "messages/delete";
    }

    /**
     * メッセージ削除完了画面を表示する。
     */
    @RequestMapping(value = "/id/{messageId}/deletecompleted", method = RequestMethod.GET)
    public String deleteCompleted(Model model, @PathVariable("messageId") long messageId) {
        return "messages/deletecompleted";
    }

}
