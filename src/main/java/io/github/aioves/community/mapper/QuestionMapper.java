package io.github.aioves.community.mapper;

import io.github.aioves.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
