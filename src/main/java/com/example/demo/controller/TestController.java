package com.example.demo.controller;

import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hqf
 * @Date: 2023/2/21 16:48
 */
@RestController
public class TestController {

    @Autowired
    LoginService loginService;

    @GetMapping("/test")
    public ResponseResult test() {
        return new ResponseResult(200, "测试成功");
    }


    @PostMapping("/authority")
    @PreAuthorize("hasAnyAuthority('menu:main')")
    public ResponseResult testAuthority() {
        return new ResponseResult(200, "测试权限");
    }


    @PostMapping("/custom/authority")
    @PreAuthorize("@ex.hasAuthority('menu:main')")
    public ResponseResult testCustomAuthority() {
        return new ResponseResult(200, "测试自定义权限");
    }


    @PostMapping("/role")
    @PreAuthorize("hasRole('admin')")
    public ResponseResult testRole() {
        return new ResponseResult(200, "测试角色");
    }


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }


    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}
