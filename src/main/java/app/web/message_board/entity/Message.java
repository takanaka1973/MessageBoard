package app.web.message_board.entity;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import lombok.Data;

/**
 * メッセージを表すクラス。
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * メッセージのID。
     */
    private Long messageId;

    /**
     * メッセージのタイトル。
     */
    @NotEmpty
    @Size(max = 20)
    private String title;

    /**
     * メッセージの本文。
     */
    @NotEmpty
    @Size(max = 500)
    private String content;

    /**
     * メッセージの有効期間(日)。
     */
    @NotNull
    @Min(value = 1)
    @Max(value = 60)
    private Integer validPeriodInDays;

    /**
     * メッセージの作成日時。
     */
    private Date createdDatetime;

}
