package com.dong.graduate.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.graduate.mapper.ThemeContentMapper;
import com.dong.graduate.model.ThemeContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ThemeContentImp extends ServiceImpl<ThemeContentMapper, ThemeContent> {

    public List getlist(Integer themeId ){
        QueryWrapper<ThemeContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true,"themeId",themeId);
        return  baseMapper.selectList(queryWrapper);
    }

    public IPage<ThemeContent> getlistofpage(Integer limit, Integer page, String themeId){
        log.info("===========>Enter the getlistofpage method , start select id,orgName,abbreviation,ipScope,parentId");
        Page<ThemeContent> ipage = new Page<>(page,limit);
        QueryWrapper<ThemeContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true,"themeId",themeId);
        queryWrapper.select("id","themeId","content","updateTime","parentId");
        baseMapper.selectPage(ipage,queryWrapper);
        return ipage;
    }


    public void appendLevelInsert(Integer themeid, Integer userid, String content, Integer deepth, Date inserttime, Integer parentid){
        ThemeContent themeContent = new ThemeContent();
        themeContent.setContent(content);
        themeContent.setDeepth(deepth);
        themeContent.setThemeid(themeid);
        themeContent.setParentid(parentid);
        themeContent.setUpdatetime(inserttime);
        themeContent.setUserid(userid);
        baseMapper.insert(themeContent);
    }
}
