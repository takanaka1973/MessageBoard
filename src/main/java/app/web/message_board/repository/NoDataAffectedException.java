package app.web.message_board.repository;

import org.springframework.dao.DataAccessException;

/**
 * 想定外に1件もデータが処理されなかったことを表す例外のクラス。
 */
public class NoDataAffectedException extends DataAccessException {

    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ。
     */
    public NoDataAffectedException(String msg) {
        this(msg, null);
    }

    /**
     * コンストラクタ。
     */
    public NoDataAffectedException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
