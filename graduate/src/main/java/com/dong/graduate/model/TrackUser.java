package com.dong.graduate.model;

import lombok.Data;

import java.util.Date;

@Data
public class TrackUser {

    private Integer userid;

    private String username;

    private String accesstoken;

    private String phone;

    private String remark;

    private Date createtime;

}