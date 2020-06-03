package com.hotel.core.service;

import com.hotel.core.entity.History;

import java.util.List;
import java.util.Map;

public interface HistoryService {

    //添加历史
    public int insertHistory(History history, String KeyList);

    //修改历史
    public int updateHistory(History history, String KeyList);

    //根据id删除历史
    public int deleteHistoryById(Integer id, String KeyList);

    //根据id查询历史
    public History selectHistoryById(Integer id);

    //获取历史分页列表
    public List<History> selectHistoryListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改历史状态
    public int updateHistoryByStates(History history, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
