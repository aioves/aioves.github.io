package io.github.aioves.community.service;

import io.github.aioves.community.dto.QuestionDTO;
import io.github.aioves.community.mapper.QuestionMapper;
import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.Question;
import io.github.aioves.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-04 0:18
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        ArrayList<QuestionDTO> questionDTOArrayList = new ArrayList<>();

        List<Question> questionList = questionMapper.findAll();

        for(int index=0, size=questionList.size(); index<size; index++) {
            QuestionDTO questionDTO = new QuestionDTO();

            Question question = questionList.get(index);
            BeanUtils.copyProperties(question, questionDTO);

            User user = userMapper.findUserByUserId(question.getCreatedBy());
            questionDTO.setUser(user);

            questionDTOArrayList.add(questionDTO);
        }

        return questionDTOArrayList;
    }
}
