package com.hotel.core.service;

import com.hotel.core.entity.Opinion;

import java.util.List;
import java.util.Map;

public interface OpinionService {

    //添加意见
    public int insertOpinion(Opinion opinion, String KeyList);

    //修改意见
    public int updateOpinion(Opinion opinion, String KeyList);

    //根据id删除意见
    public int deleteOpinionById(Integer id, String KeyList);

    //根据id查询意见
    public Opinion selectOpinionById(Integer id);

    //获取意见分页列表
    public List<Opinion> selectOpinionListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改意见状态
    public int updateOpinionByStates(Opinion opinion, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
