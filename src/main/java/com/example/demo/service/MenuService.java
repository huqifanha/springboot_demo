package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<String> getPermsByUid(Long userId);

}
