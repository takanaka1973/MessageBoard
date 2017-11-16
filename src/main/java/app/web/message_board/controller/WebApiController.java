package app.web.message_board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import app.web.message_board.entity.Message;
import app.web.message_board.json_binding.CreateMessageRequest;
import app.web.message_board.json_binding.ErrorResponse;
import app.web.message_board.json_binding.GetMessageResponse;
import app.web.message_board.service.MessageService;

/**
 * Web APIのコントローラ。
 */
@RestController
@RequestMapping("/api")
public class WebApiController {

    //@SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WebApiController.class);

    @Autowired
    private MessageService messageService;

    /**
     * メッセージ取得要求のハンドラ。
     */
    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GetMessageResponse> getMessage(@PathVariable("messageId") long messageId) throws Exception {
        Message message = messageService.findMessage(messageId);

        if (message == null) {
            return new ResponseEntity<GetMessageResponse>(HttpStatus.NOT_FOUND);
        }

        GetMessageResponse response = new GetMessageResponse();

        response.setTitle(message.getTitle());
        response.setContent(message.getContent());

        return new ResponseEntity<GetMessageResponse>(response, HttpStatus.OK);
    }

    /**
     * メッセージ登録要求のハンドラ。
     */
    @RequestMapping(value = "/messages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createMessage(@Validated @RequestBody CreateMessageRequest request) throws Exception {
        // リクエストボディの例: {"title":"Hello","content":"Hello, World!","validPeriodInDays":20}

        Message message = new Message();

        message.setTitle(request.getTitle());
        message.setContent(request.getContent());
        message.setValidPeriodInDays(request.getValidPeriodInDays());

        messageService.saveMessage(message);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * 例外発生時のハンドラ。
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(WebRequest request, Exception e) {
        logger.error(request.getDescription(false), e);

        ErrorResponse response = new ErrorResponse();

        response.setErrorSummary(e.getClass().getSimpleName());
        response.setErrorDetail(e.toString());

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof MethodArgumentTypeMismatchException || e instanceof MethodArgumentNotValidException
                || e instanceof HttpMessageConversionException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<ErrorResponse>(response, httpStatus);
    }

}
