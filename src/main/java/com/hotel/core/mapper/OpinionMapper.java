package com.hotel.core.mapper;

import com.hotel.core.entity.Opinion;

import java.util.List;
import java.util.Map;

public interface OpinionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Opinion opinion);

    int insertSelective(Opinion opinion);

    Opinion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Opinion opinion);

    int updateByPrimaryKey(Opinion opinion);

    //获取意见反馈分页列表
    List<Opinion> selectOpinionByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改意见反馈状态
    int updateOpinionByStates(Opinion opinion);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}