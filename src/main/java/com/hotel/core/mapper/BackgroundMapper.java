package com.hotel.core.mapper;

import com.hotel.core.entity.Background;

import java.util.List;
import java.util.Map;

public interface BackgroundMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Background background);

    int insertSelective(Background background);

    Background selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Background background);

    int updateByPrimaryKey(Background background);

    //获取背景图片分页列表
    List<Background> selectBackgroundByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改背景图片状态
    int updateBackgroundByStates(Background background);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}