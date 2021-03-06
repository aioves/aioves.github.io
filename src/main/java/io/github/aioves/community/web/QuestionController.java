package io.github.aioves.community.web;

import io.github.aioves.community.dto.QuestionDTO;
import io.github.aioves.community.exception.CustomizeException;
import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.Question;
import io.github.aioves.community.model.User;
import io.github.aioves.community.service.QuestionService;
import io.github.aioves.community.utils.Contents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-04 21:05
 */
@Slf4j
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(path = "/question/{id}")
    public String question(@PathVariable(name = "id") int id,
                           Model model){

        Question question = questionService.findQuestionById(id);

        if(null == question) {
            log.warn("quesstion id({}) is error.", id);
            throw new CustomizeException(Contents.GREETING);
        }

        questionService.incView(id);

        User user = userMapper.selectByPrimaryKey(question.getCreatedBy());

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);

        model.addAttribute("question", questionDTO);

        return "question";
    }
}
