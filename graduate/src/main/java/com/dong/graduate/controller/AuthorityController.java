package com.dong.graduate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.graduate.service.CommonCnicListOrgImp;
import com.dong.graduate.model.CommonCnicListOrg;
import com.dong.graduate.model.ResultOfUserPage;
import com.dong.graduate.model.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/Au")
public class AuthorityController {

    @Autowired
    CommonCnicListOrgImp commonCnicListOrgImp;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("/Read")
    @ResponseBody
    public ResultOfUserPage readAu(Integer limit, Integer page){
        log.info("**********Enter the readAu method");
        String parentid = httpServletRequest.getParameter("pid");
        log.info("**********Get the pid ,to start Pagination");
        IPage<CommonCnicListOrg> ipage = commonCnicListOrgImp.getbyAuId(limit,page,Integer.valueOf(parentid));
        ResultOfUserPage res  = new ResultOfUserPage(0,"ok",ipage.getRecords(),Long.valueOf(ipage.getTotal()));
        return res;
    }

    @RequestMapping("/Write")
    @ResponseBody
    public ResultOfUserPage writeAu(Integer limit ,Integer page){
        IPage<CommonCnicListOrg> ipage = commonCnicListOrgImp.getbyAuAll(limit,page);
        ResultOfUserPage res  = new ResultOfUserPage(0,"ok",ipage.getRecords(),Long.valueOf(ipage.getTotal()));
        return res;
    }

    @RequestMapping("/Modify")
    @ResponseBody
    public ResultVO modify(Integer id, Integer val){
        commonCnicListOrgImp.updateAuthority(id,val);
        ResultVO res = new ResultVO(0,"ok",null);
        return res;
    }
}
