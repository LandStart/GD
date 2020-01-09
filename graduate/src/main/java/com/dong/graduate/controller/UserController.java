package com.dong.graduate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.graduate.model.ResultOfUserPage;
import com.dong.graduate.model.ResultVO;
import com.dong.graduate.model.TrackUser;
import com.dong.graduate.service.TrackUserImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/User")
@Controller
public class UserController<T> {

    @Autowired
    TrackUserImp trackUserImp;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("GetTrackUser")
    @ResponseBody
    public ResultOfUserPage getUsers(Integer limit, Integer page){
        log.info("**********Enter the getUsers method ");
        IPage<TrackUser> pageResult = trackUserImp.getuser(page,limit);
        ResultOfUserPage resultOfTrackUserPage = new ResultOfUserPage(Integer.valueOf(0),"ok",pageResult.getRecords(),Long.valueOf(pageResult.getTotal()));

        return resultOfTrackUserPage;
    }

    @RequestMapping("/registry")
    @ResponseBody
    public ResultVO registry(@RequestParam("username") String username,
                             @RequestParam("accesstoken") String accesstoken){
       log.info("enter the registry method ");
        boolean ifexist = trackUserImp.insetUser(username,accesstoken);
        ResultVO resultVO =  null;
        System.out.println(ifexist);
        if(ifexist){
            resultVO  =new ResultVO(1,"ok",null);
        }else{
            resultVO  =new ResultVO(0,"ok",null);
        }
        return resultVO;
    }

    @RequestMapping("/Modify")
    @ResponseBody
    public ResultVO modify(Integer userid , String val){

        trackUserImp.updateCumtomerinfo(userid,val);
        ResultVO res = new ResultVO(0,"ok",null);
        return res;
    }

    @RequestMapping("/GetCusLoginInfo")
    @ResponseBody
    public ResultOfUserPage GetCusLoginInfo(Integer limit, Integer page){
        log.info("**********Enter the GetCusLoginInfo method ");
        String username =(String) httpServletRequest.getSession().getAttribute("username");
        IPage<TrackUser> pageResult = trackUserImp.GetCusLoginInfo(page,limit,username);
        ResultOfUserPage resultOfTrackUserPage = new ResultOfUserPage(Integer.valueOf(0),"ok",pageResult.getRecords(),Long.valueOf(pageResult.getTotal()));

        return resultOfTrackUserPage;
    }

    @RequestMapping("/GetAllCusLoginInfo")
    @ResponseBody
    public ResultOfUserPage GetAllCusLoginInfo(Integer limit, Integer page){
        log.info("**********Enter the GetAllCusLoginInfo method ");
        IPage<TrackUser> pageResult = trackUserImp.GetCusLoginInfo(page,limit);
        ResultOfUserPage resultOfTrackUserPage = new ResultOfUserPage(Integer.valueOf(0),"ok",pageResult.getRecords(),Long.valueOf(pageResult.getTotal()));

        return resultOfTrackUserPage;
    }







}
