package app.web.message_board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.web.message_board.misc.ApplicationInfo;

/**
 * ホーム画面のコントローラ。
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ApplicationInfo applicationInfo;

    /**
     * ホーム画面へのリクエストを処理する。
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("applicationInfo", applicationInfo);
        return "home";
    }

}
