package io.github.aioves.community.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 16:45
 */
@Controller
public class IndexController {

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }
}
