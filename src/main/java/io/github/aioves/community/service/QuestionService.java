package io.github.aioves.community.service;

import io.github.aioves.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer pageSize) {
        ArrayList<QuestionDTO> questionDTOArrayList = new ArrayList<>();

        Integer offset = pageSize * (page - 1);
        List<Question> questionList = questionMapper.findQuestionByPager(offset, pageSize);

        for(int index=0, size=questionList.size(); index<size; index++) {
            QuestionDTO questionDTO = new QuestionDTO();

            Question question = questionList.get(index);
            BeanUtils.copyProperties(question, questionDTO);

            User user = userMapper.findUserByUserId(question.getCreatedBy());
            questionDTO.setUser(user);

            questionDTOArrayList.add(questionDTO);
        }

        int totalCount = questionMapper.count();
        int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(page>totalPage) {
            page = totalPage;
        }

        PaginationDTO pagination = new PaginationDTO();
        pagination.setQuestions(questionDTOArrayList);
        pagination.setPagination(totalCount, page, pageSize);

        return pagination;
    }
}
