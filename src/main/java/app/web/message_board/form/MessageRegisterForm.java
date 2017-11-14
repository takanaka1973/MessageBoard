package app.web.message_board.form;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import app.web.message_board.entity.Message;
import lombok.Data;

/**
 * 新規メッセージ登録フォームのクラス。
 */
@Data
public class MessageRegisterForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * メッセージ。
     */
    @NotNull
    @Valid
    private Message message;

}
