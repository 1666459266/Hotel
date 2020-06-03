package com.hotel.core.mapper;

import com.hotel.core.entity.Features;

import java.util.List;
import java.util.Map;

public interface FeaturesMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Features features);

    int insertSelective(Features features);

    Features selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Features features);

    int updateByPrimaryKey(Features features);

    //获取特色分页列表
    List<Features> selectFeaturesByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改存在状态
    int updateFeaturesByStates(Features features);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}