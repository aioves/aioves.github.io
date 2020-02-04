package io.github.aioves.community.web;

import io.github.aioves.community.dto.PaginationDTO;
import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.User;
import io.github.aioves.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-04 12:43
 */
@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(path = "{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page",required = false, defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize,
                          HttpServletRequest request,
                          Model model) {

        User user = (User) request.getSession().getAttribute("usr");

        if(null == user) {
            model.addAttribute("error", "用户未登录");
            return "index";
        }

        if("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");

        } else if("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
        }

        PaginationDTO paginationDTO = questionService.list(user.getUserId(), page, pageSize);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
