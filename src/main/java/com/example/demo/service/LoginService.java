package com.example.demo.service;


import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.User;

public interface LoginService {


    ResponseResult login(User user);

    ResponseResult logout();


}
