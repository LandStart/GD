package com.dong.graduate.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.graduate.mapper.CommonCnicListOrgMapper;
import com.dong.graduate.model.CommonCnicListOrg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommonCnicListOrgImp extends ServiceImpl<CommonCnicListOrgMapper, CommonCnicListOrg> {
    public IPage<CommonCnicListOrg> getbyAuId(Integer limit, Integer page, Integer parentid) {
        log.info("===========>Enter the ServiceImpl method , start select id ,orgname ,parentid where parentid = x");
        Page<CommonCnicListOrg> iPage =  new Page<>(page,limit);
        QueryWrapper<CommonCnicListOrg> queryWrapper = new QueryWrapper();
        queryWrapper.eq(true,"parentId",parentid);
        queryWrapper.select("id","orgName","parentId");
        baseMapper.selectPage(iPage,queryWrapper);
        return iPage;
    }

    public IPage<CommonCnicListOrg> getbyAuAll(Integer limit, Integer page) {
        log.info("===========>Enter the getbyAuAll method , start select id ,orgname ,parentid");
        Page<CommonCnicListOrg> iPage =  new Page<>(page,limit);
        QueryWrapper<CommonCnicListOrg> queryWrapper = new QueryWrapper();
        queryWrapper.select("id","orgName","parentId");
        baseMapper.selectPage(iPage,queryWrapper);
        return iPage;
    }

    public IPage<CommonCnicListOrg> getCommonCnicListOrg(Integer page,Integer limit,String authority){
        log.info("===========>Enter the getCommonCnicListOrg method , start select id,orgName,abbreviation,ipScope,parentId");
        Page<CommonCnicListOrg> ipage = new Page<>(page,limit);
        QueryWrapper<CommonCnicListOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true,"parentId",authority);
        queryWrapper.select("id","orgName","abbreviation","ipScope","parentId");
        baseMapper.selectPage(ipage,queryWrapper);
        return ipage;
    }

    //方法有问题
    public void updateAuthority(Integer id,Integer val){
        log.info("===========>Enter the updateAuthority method , start update parentId");
        CommonCnicListOrg commonCnicListOrg = new CommonCnicListOrg();
        commonCnicListOrg.setId(id);
        commonCnicListOrg.setParentid(val);
        UpdateWrapper<CommonCnicListOrg> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("parentId",val);
        baseMapper.updateById(commonCnicListOrg);
    }
}
