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

    @Update("update question set comment_count=#{commentCount} where id=#{id} ")
    void updateCommentCountById(@Param("id") Long id, @Param("commentCount") Integer commentCount);

    @Update("update question set view_count=#{viewCount} where id=#{id} ")
    void updateViewCountById(@Param("id") Long id, @Param("viewCount") Integer viewCount);

    @Update("update question set like_count=#{likeCount} where id=#{id} ")
    void updateLikeCountById(@Param("id") Long id, @Param("likeCount") Integer likeCount);

    @Select("select * from question")
    List<Question> findAll();

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question limit #{offset}, #{pageSize}")
    List<Question> findQuestionByPager(@Param("offset") Integer offset,
                                       @Param("pageSize") Integer pageSize);

    @Select("select count(1) from question where created_by=#{userId}")
    Integer countByUserId(@Param("userId") Long userId);

    @Select("select * from question where created_by=#{userId} limit #{offset}, #{pageSize}")
    List<Question> findQuestionByUserWithPager(@Param("userId") Long userId,
                                                @Param("offset") Integer offset,
                                                @Param("pageSize") Integer pageSize);
}
