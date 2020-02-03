package io.github.aioves.community.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-03 16:34
 */
@Controller
public class PublishController {

    @GetMapping(path = "/publish")
    public String publish() {

        return "publish";
    }
}
