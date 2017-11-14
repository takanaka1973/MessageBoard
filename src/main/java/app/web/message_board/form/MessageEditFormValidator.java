package app.web.message_board.form;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import app.web.message_board.entity.Message;

/**
 * メッセージ編集フォームのバリデータ。
 */
@Component
public class MessageEditFormValidator implements SmartValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MessageEditForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, new Object[] {});
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        MessageEditForm form = (MessageEditForm) target;
        Message messageInForm = form.getMessage();

        String fieldName = "message.title";

        if (!errors.hasFieldErrors(fieldName)) {
            String title = messageInForm.getTitle();
            int maxLength = 20;

            if (StringUtils.isEmpty(title)) {
                errors.rejectValue(fieldName, "NotEmpty.messageEditForm." + fieldName, new Object[] { fieldName },
                        "error");
            } else if (title.length() > maxLength) {
                errors.rejectValue(fieldName, "Size.messageEditForm." + fieldName,
                        new Object[] { fieldName, maxLength }, "error");
            }
        }

        fieldName = "message.content";

        if (!errors.hasFieldErrors(fieldName)) {
            String content = messageInForm.getContent();
            int maxLength = 500;

            if (StringUtils.isEmpty(content)) {
                errors.rejectValue(fieldName, "NotEmpty.messageEditForm." + fieldName, new Object[] { fieldName },
                        "error");
            } else if (content.length() > maxLength) {
                errors.rejectValue(fieldName, "Size.messageEditForm." + fieldName,
                        new Object[] { fieldName, maxLength }, "error");
            }
        }

        fieldName = "message.validPeriodInDays";

        if (!errors.hasFieldErrors(fieldName)) {
            Integer validPeriodInDays = messageInForm.getValidPeriodInDays();

            if (validPeriodInDays == null) {
                errors.rejectValue(fieldName, "NotNull.messageEditForm." + fieldName, new Object[] { fieldName },
                        "error");
            } else {
                int intValidPeriodInDays = validPeriodInDays.intValue();
                int min = 1;
                int max = 60;

                if (intValidPeriodInDays < min) {
                    errors.rejectValue(fieldName, "Min.messageEditForm." + fieldName, new Object[] { fieldName, min },
                            "error");
                } else if (intValidPeriodInDays > max) {
                    errors.rejectValue(fieldName, "Max.messageEditForm." + fieldName, new Object[] { fieldName, max },
                            "error");
                }
            }
        }
    }

}
