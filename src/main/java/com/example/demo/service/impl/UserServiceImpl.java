package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Menu;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hqf
 * @Date: 2023/2/21 16:42
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Autowired
//    private MenuMapper menuMapper;

    @Override
    public User getByName(String userName) {
        User user = getBaseMapper().getByName(userName);
//        Menu menu = menuMapper.selectById(1);
        return user;
    }


    @Override
    public Integer addUser(User user) {
        return getBaseMapper().addUser(user);
    }


}
