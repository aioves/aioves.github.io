package io.github.aioves.community.service;

import io.github.aioves.community.dto.PaginationDTO;
import io.github.aioves.community.dto.QuestionDTO;
import io.github.aioves.community.mapper.QuestionMapper;
import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.Question;
import io.github.aioves.community.model.QuestionExample;
import io.github.aioves.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, pageSize));

        for(int index=0, size=questionList.size(); index<size; index++) {
            QuestionDTO questionDTO = new QuestionDTO();

            Question question = questionList.get(index);
            BeanUtils.copyProperties(question, questionDTO);

            User user = userMapper.selectByPrimaryKey(question.getCreatedBy());
            questionDTO.setUser(user);

            questionDTOArrayList.add(questionDTO);
        }

        int totalCount = (int) questionMapper.countByExample(new QuestionExample());
        if(totalCount==0) {
            return new PaginationDTO();
        }

        int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(page>totalPage && totalPage>0) {
            page = totalPage;
        }

        PaginationDTO pagination = new PaginationDTO();
        pagination.setQuestions(questionDTOArrayList);
        pagination.setPagination(totalPage, page, pageSize);

        return pagination;
    }

    public PaginationDTO list(int userId, Integer page, Integer pageSize) {
        ArrayList<QuestionDTO> questionDTOArrayList = new ArrayList<>();

        Integer offset = pageSize * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatedByEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, pageSize));

        for(int index=0, size=questionList.size(); index<size; index++) {
            QuestionDTO questionDTO = new QuestionDTO();

            Question question = questionList.get(index);
            BeanUtils.copyProperties(question, questionDTO);

            User user = userMapper.selectByPrimaryKey(question.getCreatedBy());
            questionDTO.setUser(user);

            questionDTOArrayList.add(questionDTO);
        }

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatedByEqualTo(userId);
        int totalCount = (int) questionMapper.countByExample(example);
        int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(page>totalPage) {
            page = totalPage;
        }

        PaginationDTO pagination = new PaginationDTO();
        pagination.setQuestions(questionDTOArrayList);
        pagination.setPagination(totalPage, page, pageSize);

        return pagination;
    }

    public Question findQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    public void insert(Question question) {
        question.setCommentCount(0);
        question.setLikeCount(0);
        question.setViewCount(0);
        question.setCreateDate(Calendar.getInstance().getTime());
        question.setUpdateDate(Calendar.getInstance().getTime());
        questionMapper.insert(question);
    }

    public void update(Question question) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andIdEqualTo(question.getId());
        questionMapper.updateByExampleSelective(question, example);
    }
}
