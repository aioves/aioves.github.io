package io.github.aioves.community.mapper;

import io.github.aioves.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-03 12:28
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(login, name, bio, avatar_url, account_id, token) values(#{login}, #{name}, #{bio}, #{avatarUrl}, #{accountId}, #{token})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findUserByToken(@Param("token") String token);
}
