package io.github.aioves.community.web;

import io.github.aioves.community.dto.QuestionDTO;
import io.github.aioves.community.mapper.QuestionMapper;
import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.Question;
import io.github.aioves.community.model.User;
import io.github.aioves.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(path = "/")
    public String home(Model model, HttpServletRequest request) {
        Integer state = random.nextInt(10)*12+17;
        log.info("state={}", state);

        model.addAttribute("authorizeUrl", authorizeUrl + state);


        Cookie[] cookies = request.getCookies();
        if(null!=cookies) {
            for(int index = 0, len = cookies.length; index<len; index++) {
                Cookie cookie = cookies[index];
                if("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    log.info("token={}", token);
                    User user = userMapper.findUserByToken(token);
                    if(null!=user) {
                        request.getSession().setAttribute("usr", user);
                    }
                }
            }
        }

        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions", questionList);

        log.info("{}", questionList);

        return "index";
    }

    @GetMapping(path = "/index")
    public String index(Model model, HttpServletRequest request) {
        return home(model, request);
    }
}
