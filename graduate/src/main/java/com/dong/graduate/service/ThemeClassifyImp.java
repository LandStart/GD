package com.dong.graduate.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.graduate.mapper.ThemeClassifyMapper;
import com.dong.graduate.model.ThemeClassify;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeClassifyImp extends ServiceImpl<ThemeClassifyMapper, ThemeClassify> {
    
    public List<ThemeClassify> getThemeClassify(){
        QueryWrapper<ThemeClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","classifyName");
        return baseMapper.selectList(queryWrapper);
    }
}
