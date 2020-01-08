package com.dong.graduate.model;

import lombok.Data;

import java.util.List;


@Data
public class ResultOfUserPage<T> extends ResultVO<T> {

    private Long Count;



    public ResultOfUserPage(Integer code, String msg, List<T> data, Long count) {
        super(code, msg, data);
        Count = count;
    }
}
