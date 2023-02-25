package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> getPermsByUid(Long userId);

}
