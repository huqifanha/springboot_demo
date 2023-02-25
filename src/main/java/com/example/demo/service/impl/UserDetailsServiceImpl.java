package com.example.demo.service.impl;

import com.example.demo.pojo.LoginUser;
import com.example.demo.pojo.User;
import com.example.demo.service.MenuService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: hqf
 * @Date: 2023/2/21 16:42
 * 实现UserDetailsService接口，重写其中的方法。更加用户名从数据库中查询用户信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //根据用户查询权限信息 添加到LoginUser中
        List<String> perms = menuService.getPermsByUid(user.getId());

        //封装成UserDetails对象返回
        return new LoginUser(user, perms);
    }
}
