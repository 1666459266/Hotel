package com.hotel.core.mapper;

import com.hotel.core.entity.History;

import java.util.List;
import java.util.Map;

public interface HistoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(History history);

    int insertSelective(History history);

    History selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(History history);

    int updateByPrimaryKey(History history);

    //获取历史分页列表
    List<History> selectHistoryByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改历史存在状态
    int updateHistoryByStates(History history);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}