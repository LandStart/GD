package com.dong.graduate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.graduate.model.CommonCnicListOrg;
import com.dong.graduate.model.ResultOfUserPage;
import com.dong.graduate.service.CommonCnicListOrgImp;
import com.dong.graduate.service.TrackUserImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("CommonCnicListOrg")
public class CommonCnicListOrgController  {

    @Autowired
    CommonCnicListOrgImp commonCnicListOrgImp;

    @Autowired
    TrackUserImp trackUserImp;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("Get")
    @ResponseBody
    public ResultOfUserPage getCommonCnicListOrg(Integer limit, Integer page){
       log.info("**********Enter the getCommonCnicListOrg Method ");
        //先获取authorid,有可能Session被清除
        String username = (String)httpServletRequest.getSession().getAttribute("username");
        ResultOfUserPage resultOfCommonCnicListOrgUserPage;
        if(!username.equals(null)){
            String authority = trackUserImp.getAuthority(username);
            log.info("********** Accord the authority of username to Pagination");
            IPage<CommonCnicListOrg> pageResult = commonCnicListOrgImp.getCommonCnicListOrg(page,limit,authority);
            resultOfCommonCnicListOrgUserPage = new ResultOfUserPage(Integer.valueOf(0),"ok",pageResult.getRecords(),Long.valueOf(pageResult.getTotal()));
            return resultOfCommonCnicListOrgUserPage;
        }
        resultOfCommonCnicListOrgUserPage = new ResultOfUserPage(Integer.valueOf(-1),"fial",null,Long.valueOf(1));

        return  resultOfCommonCnicListOrgUserPage;

    }
}
