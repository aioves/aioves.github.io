package io.github.aioves.community.service;

import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Locale;

/**
 * @Title:
 * @Remarks:
 * @Author: <a href="mailto:aioves@foxmail.com>aioves</a>
 * @Version: 1.0.0
 * @Date: 2020-02-05 13:54
 */
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public void saveOrUpdate(User user) {
        User dbUser = userMapper.findUserByAccountId(user.getAccountId());
        if(null == dbUser) { //插入
            userMapper.insert(user);
        } else { //更新
            dbUser.setLogin(user.getLogin());
            dbUser.setName(user.getName());
            dbUser.setBio(user.getBio());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            dbUser.setUpdateDate(Calendar.getInstance(Locale.CHINA).getTime());
            userMapper.update(dbUser);
        }
    }
}
