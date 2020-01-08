package com.dong.graduate.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.graduate.mapper.SendMessageMapper;
import com.dong.graduate.model.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SendMessageImp extends ServiceImpl<SendMessageMapper, SendMessage> {

    public int  insertMessage(String username,String usermessage,Date date,String themeid){
        SendMessage sendMessage  = new SendMessage(username,usermessage,date,Integer.valueOf(themeid));
        if(sendMessage.getUsermessage() == ""){
            return 0;
        }else{
            return baseMapper.insert(sendMessage);
        }
    }

    public List<Object> getMessage(String themeid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(true,"themeId",themeid);
        queryWrapper.select("userName","userMessage","sendTime","themeid");
        return baseMapper.selectList(queryWrapper);
    }
}
