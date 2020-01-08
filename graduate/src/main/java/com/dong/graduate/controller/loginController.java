package com.dong.graduate.controller;

import com.dong.graduate.model.ResultVO;
import com.dong.graduate.service.TrackUserImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
public class loginController {

    @Autowired
    TrackUserImp trackUserImp;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/")
    public String getIndex(){
        if(httpServletRequest.getSession().getAttribute("username") != null){
            System.out.println("检查到原有session ,直接登录");
             return "forward:/index.html";
        }
        return "login";
    }

    @RequestMapping("/authentication")
    @ResponseBody
    public ResultVO authentication(@RequestBody Map<String,Object> map){
        log.info("**********Enter the authentication method, Start Check the  Username and Password ");
        ResultVO<Object> build =
                ResultVO.builder().build();
        String userName = map.get("username").toString();
        String password = map.get("password").toString();

        boolean isAccure = trackUserImp.getPasswordByUser(userName,password);

        log.info("**********Get the result of execute wiht sql (username | password) ");
        if(isAccure){
            build.setCode(0);
            build.setMsg(userName);
            log.info("**********Set the username Attribute into the Session and into the index.html");
            httpServletRequest.getSession().setAttribute("username",userName);

            return build;
        }

        if("admin".equals(userName) && "qazwsxplmokn".equals(password) ){
            build.setCode(0);
            build.setMsg("admin");
            log.info("**********the Admin enter plantfrom and Set username Attribute into the Session and into the admin.html");
            httpServletRequest.getSession().setAttribute("username",userName);
            return build;
        }

        httpServletRequest.getSession().setAttribute("username",userName);
        build.setCode(-1);
        build.setData(null);
        return  build;
    }

    @ResponseBody
    @RequestMapping("/usernameFromSession")
    public ResultVO getUsernameFromSession(){
        log.info("Enter getUsernameFromSession Method Check the session's username status");
        ResultVO<Object> build = ResultVO.builder().build();
        String username = (String)httpServletRequest.getSession().getAttribute("username");
        build.setMsg(username);
        build.setCode(0);
        build.setData(null);
        return  build;
    }

    @ResponseBody
    @RequestMapping("/exitAndclearSession")
    public ResultVO toExitAndclearSession(){
        log.info("**********Enter the toExitAndclearSession Method ");
        ResultVO<Object> build = ResultVO.builder().build();
        String username = (String)httpServletRequest.getSession().getAttribute("username");
        log.info("**********Clear the username of the session and invalidate the session");
        httpServletRequest.removeAttribute(username);
        //httpServletRequest.getSession().invalidate();
        build.setMsg(username);
        build.setCode(0);
        build.setData(null);
        return  build;
    }



}

