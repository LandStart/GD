package com.dong.graduate.controller;

import com.dong.graduate.model.ResultVO;
import com.dong.graduate.service.ThemeClassifyImp;
import com.dong.graduate.service.ThemeContentImp;
import com.dong.graduate.service.ThemeImp;
import com.dong.graduate.service.TrackUserImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/theme")
@Controller
@Slf4j
public class ThemeController {

    @Autowired
    ThemeClassifyImp themeClassifyImp;

    @Autowired
    ThemeImp themeImp;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    ThemeContentImp themeContentImp;

    @Autowired
    TrackUserImp trackUserImp;


    @RequestMapping("/Getclassify")
    @ResponseBody
    public ResultVO<Object> getThemeClassify(){
        log.info("enter the getThemeClassify");
        List datas = themeClassifyImp.getThemeClassify();
        ResultVO<Object> resultVO = ResultVO.builder().build();

        resultVO.setCode(0);
        resultVO.setMsg("ok");
        resultVO.setData(datas);
        return resultVO;
    }

    @RequestMapping("/Read")
    @ResponseBody
    public ResultVO<Object> getThemelist(){
        String themeclassifyId = httpServletRequest.getParameter("pid");
        List datas = themeImp.getList(Integer.valueOf(themeclassifyId));
        ResultVO<Object> resultVO = ResultVO.builder().build();
        resultVO.setCode(0);
        resultVO.setMsg("ok");
        resultVO.setData(datas);
        return resultVO;
    }

    @RequestMapping("/Content")
    @ResponseBody
    public ResultVO<Object> getThemeContent(){
        String themeid = httpServletRequest.getParameter("themeid");
        List datas = themeContentImp.getlist(Integer.valueOf(themeid));
        ResultVO<Object> resultVO = ResultVO.builder().build();

        //通过Themeid 查userid

        //通过themeid  查themename

        resultVO.setCode(0);
        resultVO.setMsg("ok");
        resultVO.setData(datas);
        return resultVO;
    }
}
