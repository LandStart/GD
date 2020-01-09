package com.dong.graduate.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.graduate.mapper.TrackUserMapper;
import com.dong.graduate.model.CommonCnicListOrg;
import com.dong.graduate.model.TrackUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class TrackUserImp extends ServiceImpl<TrackUserMapper, TrackUser> {

    public IPage<TrackUser> getuser(Integer page, Integer limit){
        Page<TrackUser> ipage = new Page<>(page,limit);

        QueryWrapper<TrackUser> queryWrapper = new QueryWrapper<>();
        log.info("===========>Start Select userid ,username ,authorityId from the Trackuser table");
        queryWrapper.select("userId","userName");
        baseMapper.selectPage(ipage,queryWrapper);
        return ipage;
    }

    public IPage<TrackUser> GetCusLoginInfo(Integer page, Integer limit,String username){
        Page<TrackUser> ipage = new Page<>(page,limit);

        QueryWrapper<TrackUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true,"userName",username);
        log.info("===========>Start Select userid ,username ,authorityId from the Trackuser table");
        queryWrapper.select("userId","userName","accessToken","phone","remark");
        baseMapper.selectPage(ipage,queryWrapper);
        return ipage;
    }
    public IPage<TrackUser> GetCusLoginInfo(Integer page, Integer limit){
        Page<TrackUser> ipage = new Page<>(page,limit);
        QueryWrapper<TrackUser> queryWrapper = new QueryWrapper<>();
        log.info("===========>Start Select userid ,username ,authorityId from the Trackuser table");
        queryWrapper.select("userId","userName","accessToken","phone","remark");
        baseMapper.selectPage(ipage,queryWrapper);
        return ipage;
    }


    public String getAuthority(String username){
        QueryWrapper<TrackUser> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq(true,"userName",username);
        TrackUser trackUser = baseMapper.selectOne(queryWrapper);

        return  String.valueOf(trackUser.getUserid());
    }

    public boolean getPasswordByUser(String username,String password){
        QueryWrapper<TrackUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true,"userName",username);
        log.info("===========>Using the username to select the password from track_user ");
        TrackUser userByName = baseMapper.selectOne(queryWrapper);
        if(userByName == null){
            log.info("===========> The username is not exist ");
            return false;
        }else{
            queryWrapper.eq(true,"accessToken",password);
            TrackUser userBypassword = baseMapper.selectOne(queryWrapper);
            if(userBypassword == null){
                log.info("===========> The password is not correct");
                return false;
            }
        }
        return true;
    }

    public TrackUser getnamebyid(Integer userid){

        QueryWrapper queryWrapper  = new QueryWrapper();
        queryWrapper.eq(true,"userid",userid);
        queryWrapper.select("userName");

        return baseMapper.selectOne(queryWrapper);
    }

    public Integer getIdByUserName(String username){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(true,"userName",username);
        TrackUser trackUser  = baseMapper.selectOne(queryWrapper);
        return trackUser.getUserid();
    }

    public boolean insetUser(String username,String accesstoken){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(true,"userName",username);
        TrackUser OldtrackUser = baseMapper.selectOne(queryWrapper);
        if(OldtrackUser == null){
            TrackUser trackUser  = new TrackUser();
            trackUser.setAccesstoken(accesstoken);
            trackUser.setUsername(username);
            baseMapper.insert(trackUser);

            return true;
        }else{
            return false;
        }
    }

    public void updateCumtomerinfo(Integer userid,String val){
        QueryWrapper<TrackUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq(true,"userId",userid);
        TrackUser trackUser = baseMapper.selectOne(queryWrapper);
        trackUser.setAccesstoken(val);
        trackUser.setCreatetime(new Date());
        trackUser.setPhone("0");
        trackUser.setRemark("0");
        System.out.println(trackUser.toString());
        UpdateWrapper<TrackUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("accessToken",val);
        baseMapper.updateById(trackUser);
    }


}
