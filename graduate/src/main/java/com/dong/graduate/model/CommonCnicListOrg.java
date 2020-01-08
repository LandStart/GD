package com.dong.graduate.model;

import lombok.Data;

import java.util.Date;

@Data
public class CommonCnicListOrg {
    private Integer id;

    private String orgname;

    private String abbreviation;

    private String longitude;

    private String latitude;

    private String ipscope;

    private String code;

    private Integer parentid;

    private String remark;

    private Date createtime;

    private Date updated;

    private String reserve;

  }