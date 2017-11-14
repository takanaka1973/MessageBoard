package app.web.message_board.json_binding;

import java.io.Serializable;
import lombok.Data;

/**
 * メッセージ取得要求のレスポンス。
 */
@Data
public class GetMessageResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * メッセージのタイトル。
     */
    private String title;

    /**
     * メッセージの本文。
     */
    private String content;

}
