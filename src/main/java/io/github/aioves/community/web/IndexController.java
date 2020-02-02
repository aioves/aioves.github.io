package io.github.aioves.community.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 16:45
 */
@Slf4j
@Controller
public class IndexController {

    private final static Random random = new Random();

    @Value("${github.app.login.authorize.url}")
    private String authorizeUrl;

    @GetMapping(path = "/")
    public String index(Model model) {
        Integer state = random.nextInt(10)*12+17;
        log.info("state={}", state);
        model.addAttribute("authorizeUrl", authorizeUrl + state);

        return "index";
    }

}
