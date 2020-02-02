package io.github.aioves.community.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Title:  【演示】控制层
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-02 15:34
 */
@Controller
public class GreetingController {

    /**
     * @method: greeting
     * @remarks:    跳转至欢迎页面greeting.html
     * @author: <a href="mailto:aioves@foxmail.com>aioves</a>
     * @version: 1.0.0
     * @date: 2020/2/2 15:38
     * @params:  * @param name
     * @param model
     * @return: java.lang.String
     */
    @GetMapping(path = "/greeting")
    public String greeting(@RequestParam(name = "user", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }
}
