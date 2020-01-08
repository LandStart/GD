package com.dong.graduate.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("send_message")
public class SendMessage {

    @TableId("id")
    private Integer id;

    private String username;
    private String usermessage;
    private Date sendtime;
    private Integer themeid;

    public SendMessage(String username, String usermessage, Date sendtime,Integer themeid) {
        this.username = username;
        this.usermessage = usermessage;
        this.sendtime = sendtime;
        this.themeid = themeid;
    }
}
