package app.web.message_board.taglib;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * newLineToBrタグのハンドラ。
 * 全ての改行文字をbrタグに置き換える。
 */
public class NewLineToBr extends SimpleTagSupport {

    private boolean useXhtmlStyleBr = false;

    /**
     * XHTML形式のbrタグを使用するかどうかを設定する。
     */
    public void setUseXhtmlStyleBr(boolean useXhtmlStyleBr) {
        this.useXhtmlStyleBr = useXhtmlStyleBr;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspFragment fragment = getJspBody();

        if (fragment != null) {
            StringWriter sw = new StringWriter();
            fragment.invoke(sw);

            String brTag = useXhtmlStyleBr ? "<br />" : "<br>";
            String s = sw.toString();

            s = s.replace("\r\n", brTag);   // must handle this first
            s = s.replace("\r", brTag);
            s = s.replace("\n", brTag);

            getJspContext().getOut().write(s);
        }
    }

}
