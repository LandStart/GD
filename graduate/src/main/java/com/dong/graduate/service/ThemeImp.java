package com.dong.graduate.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.graduate.mapper.ThemeMapper;
import com.dong.graduate.model.Theme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeImp extends ServiceImpl<ThemeMapper, Theme> {

    public List<Theme> getList(Integer themeclassifyId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(true,"themeclassifyId",themeclassifyId);
        queryWrapper.select("id","name","description","themeclassifyId","themelabelId");
        List list = baseMapper.selectList(queryWrapper);
        return list;
    }
}
