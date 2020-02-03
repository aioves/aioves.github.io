package io.github.aioves.community.mapper;

import io.github.aioves.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-03 18:00
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, detail, tags, created_by) values(#{title}, #{detail}, #{tags}, #{createdBy})")
    void insert(Question question);

    @Select("select * from question")
    List<Question> findAll();

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question limit #{offset}, #{pageSize}")
    List<Question> findQuestionByPager(@Param("offset") Integer offset,
                                       @Param("pageSize") Integer pageSize);
}