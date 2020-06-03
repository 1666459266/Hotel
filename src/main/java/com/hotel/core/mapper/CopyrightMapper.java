package com.hotel.core.mapper;

import com.hotel.core.entity.Copyright;

import java.util.List;
import java.util.Map;

public interface CopyrightMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Copyright copyright);

    int insertSelective(Copyright copyright);

    Copyright selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Copyright copyright);

    int updateByPrimaryKey(Copyright copyright);

    //获取版权信息分页列表
    List<Copyright> selectCopyrightByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改版权信息状态
    int updateCopyrightByStates(Copyright copyright);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}