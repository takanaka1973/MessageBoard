package app.web.message_board.json_binding;

import java.io.Serializable;
import lombok.Data;

/**
 * エラー発生時のレスポンス。
 */
@Data
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * エラーの概要。
     */
    private String errorSummary;

    /**
     * エラーの詳細。
     */
    private String errorDetail;

}
