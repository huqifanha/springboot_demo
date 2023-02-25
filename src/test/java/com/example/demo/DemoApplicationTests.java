package com.example.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RedisCache redisCache;

    @Test
    void contextLoads() {
        User user = userMapper.selectById(2);
        System.out.println(user.getUserName());
//        User user = new User();
//        user.setUser_name("hqf");
//        user.setPassword(passwordEncoder.encode("1234"));
//        userService.addUser(user);
//        Integer name = redisCache.getCacheObject("name");
//        System.out.println(name);
    }


    /**
     * Mybatis-plus 分页插件
     */
    @Test
    void testPage() {
        IPage<User> page = new Page<>();
        page.setSize(1);
        page.setCurrent(2);
//        IPage<User> page1 = userMapper.selectPage(page, null);
        userService.page(page);
        System.out.println(page.getRecords());
    }

}
