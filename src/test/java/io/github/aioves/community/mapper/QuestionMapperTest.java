package io.github.aioves.community.mapper;

import io.github.aioves.community.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void testFindAll() {
        List<Question> questionList = questionMapper.findAll();
        questionList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {

        for(int index = 0; index<77; index++) {
            Question question = new Question();
            question.setTitle(UUID.randomUUID().toString());
            question.setCreatedBy(1L);
            questionMapper.insert(question);
        }

    }

    @Test
    public void testCount() {
        int count = questionMapper.count();
        System.out.println(count);
    }
}