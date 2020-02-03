package io.github.aioves.community.mapper;

import io.github.aioves.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Results(id = "usrMap", value = {
            @Result(id = true, column = "user_id", property = "userId"),
            @Result(column = "login", property = "login"),
            @Result(column = "name", property = "name"),
            @Result(column = "bio", property = "bio"),
            @Result(column = "avatar_url", property = "avatarUrl"),
            @Result(column = "account_id", property = "accountId"),
            @Result(column = "token", property = "token"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "update_date", property = "updateDate")
    })
    @Select("select * from user where token=#{token}")
    User findUserByToken(@Param("token") String token);

    @ResultMap(value = {"usrMap"})
    @Select("select * from user")
    List<User> findAll();
}
