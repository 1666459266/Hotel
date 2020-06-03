package com.hotel.core.service;

import com.hotel.core.entity.Features;

import java.util.List;
import java.util.Map;

public interface FeaturesService {

    //添加特色
    public int insertFeatures(Features features, String KeyList);

    //修改特色
    public int updateFeatures(Features features, String KeyList);

    //根据id删除特色
    public int deleteFeaturesById(Integer id, String KeyList);

    //根据id查询特色
    public Features selectFeaturesById(Integer id);

    //获取特色分页列表
    public List<Features> selectFeaturesListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改特色状态
    public int updateFeaturesByStates(Features features, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
