package com.dong.graduate.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.graduate.model.TrackUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TrackUserMapper extends BaseMapper<TrackUser> {
    int deleteByPrimaryKey(Integer userid);

    int insert(TrackUser record);

    int insertSelective(TrackUser record);

    TrackUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(TrackUser record);

    int updateByPrimaryKey(TrackUser record);

    @Select("Select * from track_user")
    List<TrackUser> getAlluser();

}