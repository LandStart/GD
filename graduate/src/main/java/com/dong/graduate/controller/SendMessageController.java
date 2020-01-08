package com.dong.graduate.controller;

import com.dong.graduate.model.ResultVO;
import com.dong.graduate.model.SendMessage;
import com.dong.graduate.service.SendMessageImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/Message")
public class SendMessageController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    SendMessageImp sendMessageImp;

    @RequestMapping("/Insert")
    @ResponseBody
    public ResultVO insertMessage(@RequestParam("usermessage") String usermessage,@RequestParam("themeid") String themeid) {
        log.info("enter the inserMessage method .....");
        ResultVO resultVO = null;
        String username =(String) httpServletRequest.getSession().getAttribute("username");
        System.out.println(username);
        Date date = new Date();
        int ifsuccess = sendMessageImp.insertMessage(username,usermessage,date,themeid);

        if(ifsuccess == 1){
            resultVO  = new ResultVO(1,"ok",null);
        }else{
            resultVO  = new ResultVO(0,"nok",null);
        }
        return resultVO;
    }

    @RequestMapping("/Get")
    @ResponseBody
    public ResultVO<Object> getMessage(){
        log.info("enter the getMessage method .....");
        String themeid = httpServletRequest.getParameter("pid");
        System.out.println(themeid);
        List<Object> data = sendMessageImp.getMessage(themeid);

        ResultVO<Object> resultVO = ResultVO.builder().build();
        resultVO.setCode(0);
        resultVO.setMsg("ok");
        resultVO.setData(data);
        return resultVO;
    }
}
