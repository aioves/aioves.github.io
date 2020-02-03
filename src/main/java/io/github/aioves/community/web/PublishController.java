package io.github.aioves.community.web;

import io.github.aioves.community.mapper.QuestionMapper;
import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.Question;
import io.github.aioves.community.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-03 16:34
 */
@Slf4j
@Controller
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping
    public String goPublish() {

        return "publish";
    }

    @PostMapping
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "detail") String detail,
                            @RequestParam(name = "tags") String tags,
                            HttpServletRequest request,
                            Model model) {
        Cookie[] cookies = request.getCookies();
        User user = null;
        if(null!=cookies) {
            for(int index = 0, len = cookies.length; index<len; index++) {
                Cookie cookie = cookies[index];
                if("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    log.info("token={}", token);
                    user = userMapper.findUserByToken(token);
                    if(null!=user) {
                        request.getSession().setAttribute("usr", user);
                    }
                }
            }
        }

        log.info("{}", user);

        if(null == user) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDetail(detail);
        question.setTags(tags);
        question.setCreatedBy(user.getUserId());

        questionMapper.insert(question);

        return "redirect:/index";
    }
}
