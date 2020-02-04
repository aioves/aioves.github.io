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

    @Select("select * from user where token=#{token}")
    User findUserByToken(@Param("token") String token);

    @Update("update user set token=#{token}, update_date=CURRENT_TIMESTAMP where user_id=#{userId}")
    void  updateTokenByUserId(@Param("userId") Long userId, @Param("token") String token);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where user_id=#{userId}")
    User findUserByUserId(@Param("userId") Long userId);

    @Select("select * from user where account_id=#{accountId}")
    User findUserByAccountId(@Param("accountId") Long accountId);
}
