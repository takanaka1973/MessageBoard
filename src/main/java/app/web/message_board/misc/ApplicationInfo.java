package app.web.message_board.misc;

import lombok.Data;

/**
 * アプリケーション情報のクラス。
 */
@Data
public class ApplicationInfo {

    /**
     * 表示用の名称。
     */
    private String displayName;

    /**
     * 著作権表示。
     */
    private String copyrightNotice;

}
