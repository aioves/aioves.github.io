package io.github.aioves.community.mapper;

import io.github.aioves.community.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll() {
        List<User> list =  userMapper.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void testFindUserByToken() {
        User user = userMapper.findUserByToken("0922925f-6ea2-4816-86c4-c3debb6c5d11");
        System.out.println(user);
    }
}