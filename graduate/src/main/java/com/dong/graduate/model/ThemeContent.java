package com.dong.graduate.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ThemeContent {

    private Integer id;
    private Integer themeid;
    private Integer userid;
    private String content;
    private Integer deepth;
    private Date updatetime;
    private Integer parentid;


}
