package com.hotel.core.service;

import com.hotel.core.entity.Background;

import java.util.List;
import java.util.Map;

public interface BackgroundService {

    //添加背景信息
    public int insertBackground(Background background, String KeyList);

    //修改菜背景信息
    public int updateBackground(Background background, String KeyList);

    //根据id删除背景
    public int deleteBackgroundById(Integer id, String KeyList);

    //根据id查询菜背景图片和信息
    public Background selectBackgroundById(Integer id);

    //获取菜单分页列表
    public List<Background> selectBackgroundListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改背景图片状态
    public int updateBackgroundByStates(Background background, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
