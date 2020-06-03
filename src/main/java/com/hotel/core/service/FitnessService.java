package com.hotel.core.service;

import com.hotel.core.entity.Fitness;

import java.util.List;
import java.util.Map;

public interface FitnessService {

    //添加器材
    public int insertFitness(Fitness fitness, String KeyList);

    //修改器材
    public int updateFitness(Fitness fitness, String KeyList);

    //根据id删除器材
    public int deleteFitnessById(Integer id, String KeyList);

    //根据id查询器材
    public Fitness selectFitnessById(Integer id);

    //获取器材分页列表
    public List<Fitness> selectFitnessListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改器材状态
    public int updateFitnessByStates(Fitness fitness, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
