package io.github.aioves.community.service;

import io.github.aioves.community.mapper.UserMapper;
import io.github.aioves.community.model.User;
import io.github.aioves.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());

        List<User> userList = userMapper.selectByExample(userExample);
        if(null == userList || userList.size()==0) { //插入
            userMapper.insert(user);
        } else { //更新
            User dbUser = userList.get(0);


            user.setUpdateDate(Calendar.getInstance(Locale.CHINA).getTime());
            user.setCreateDate(Calendar.getInstance(Locale.CHINA).getTime());
            user.setUserId(dbUser.getUserId());

            UserExample example = new UserExample();
            example.createCriteria().andUserIdEqualTo(user.getUserId());
            userMapper.updateByExampleSelective(user, example);
        }
    }
}
