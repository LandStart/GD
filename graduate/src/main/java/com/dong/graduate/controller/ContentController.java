package com.dong.graduate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.graduate.model.ResultOfUserPage;
import com.dong.graduate.model.ResultVO;
import com.dong.graduate.model.ThemeContent;
import com.dong.graduate.service.ThemeContentImp;
import com.dong.graduate.service.TrackUserImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequestMapping("/Append")
@Controller
@Slf4j
public class ContentController {

    @Autowired
    ThemeContentImp themeContentImp;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    TrackUserImp trackUserImp;

    @RequestMapping("/history")
    @ResponseBody
    public ResultOfUserPage<ThemeContent> toAppendHistory(Integer limit, Integer page) {
        log.info("Enter the toAppendHistory method");
        String themeid = httpServletRequest.getParameter("pid");

        IPage<ThemeContent> iPage = themeContentImp.getlistofpage(limit,page,themeid);
        ResultOfUserPage<ThemeContent> resultVO = new ResultOfUserPage<>(0,"ok",iPage.getRecords(),iPage.getTotal());

        return resultVO;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public ResultVO toAppendInsert(@RequestParam("themeid") String themeid
            , @RequestParam("content") String content, @RequestParam("deepth") String deepth, @RequestParam("parentid") String parentid
            , @RequestParam("userid") String userid, @RequestParam("appendtime") Date appendtime
    ) {
        log.info("Enter the toAppendLevel method");
        System.out.println("start verticalappend .......... ");
        //获取该用户的id;
        String username = (String)httpServletRequest.getSession().getAttribute("username");
        Integer currentuserId = trackUserImp.getIdByUserName(username);
        System.out.println("当前用户ID为 : ------------->" + currentuserId);
        themeContentImp.appendLevelInsert(Integer.valueOf(themeid),
                Integer.valueOf(currentuserId),
                content,
                Integer.valueOf(deepth),
                appendtime,
                Integer.valueOf(parentid));
        ResultVO resultVO = ResultVO.builder().build();
        resultVO.setMsg("ok");
        resultVO.setCode(1);
        resultVO.setData(null);

        return resultVO;
    }

}
