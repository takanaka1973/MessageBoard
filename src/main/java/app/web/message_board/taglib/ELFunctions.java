package app.web.message_board.taglib;

import java.io.UnsupportedEncodingException;
import org.springframework.web.util.UriUtils;
import lombok.NonNull;

/**
 * カスタムEL関数を定義したクラス。
 */
public class ELFunctions {

    private ELFunctions() {
    }

    /**
     * URLエンコードを行う。
     */
    public static String urlEncode(@NonNull String source) {
        try {
            return UriUtils.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("should never happen", e);
        }
    }

}
