package com.dong.graduate.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Theme {

    private Integer id;
    private String name;
    private String description;
    private Integer themeclassifyid;
    private Integer themelabelid;



}
