package io.github.aioves.community.web;

import io.github.aioves.community.dto.PaginationDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(path = "/")
    public String home(@RequestParam(name = "page",required = false, defaultValue = "1") Integer page,
                       @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize,
                       Model model,
                       HttpServletRequest request) {

        /*页从1开始*/
        if(page<=0){
            page = 1;
        }

        /*页数最小为10*/
        if(pageSize<10) {
            pageSize = 10;
        }

        PaginationDTO paginationDTO = questionService.list(page, pageSize);
        model.addAttribute("pagination", paginationDTO);

        log.info("page info: {}", paginationDTO);

        return "index";
    }

    @GetMapping(path = "/index")
    public String index(@RequestParam(name = "page",required = false, defaultValue = "1") Integer page,
                        @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize,
                        Model model,
                        HttpServletRequest request) {
        return home(page, pageSize, model, request);
    }

}
