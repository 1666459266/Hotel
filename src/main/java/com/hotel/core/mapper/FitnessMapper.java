package com.hotel.core.mapper;

import com.hotel.core.entity.Fitness;

import java.util.List;
import java.util.Map;

public interface FitnessMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Fitness fitness);

    int insertSelective(Fitness fitness);

    Fitness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fitness fitness);

    int updateByPrimaryKey(Fitness fitness);

    //获取商品分页列表
    List<Fitness> selectFitnessByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改商品状态
    int updateFitnessByStates(Fitness fitness);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}