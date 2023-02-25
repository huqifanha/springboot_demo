package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.pojo.Menu;
import com.example.demo.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hqf
 * @Date: 2023/2/21 16:42
 */

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> getPermsByUid(Long userId) {
        return getBaseMapper().getPermsByUid(userId);
    }
}
