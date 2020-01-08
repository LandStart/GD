package com.dong.graduate.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
@Builder
public class Result<T> {

    private String msg;
    private Integer code;
    private ArrayList<T> objs;

    public Result(String msg, Integer code, ArrayList<T> objs) {
        this.msg = msg;
        this.code = code;
        this.objs = objs;
    }
}
