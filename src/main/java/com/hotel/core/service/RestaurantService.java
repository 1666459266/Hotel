package com.hotel.core.service;

import com.hotel.core.entity.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantService {

    //添加菜单
    public int insertRestaurant(Restaurant restaurant,String KeyList);

    //修改菜单
    public int updateRestaurant(Restaurant restaurant,String KeyList);

    //根据id删除菜单
    public int deleteRestaurantById(Integer id,String KeyList);

    //根据id查询菜单
    public Restaurant selectRestaurantById(Integer id);

    //获取菜单分页列表
    public List<Restaurant> selectRestaurantListByLimit(Map<String,Object> map,String KeyList);

    //统计条数
    public int selectCounts(Integer states,String KeyList);

    //修改菜单状态
    public int updateRestaurantByStates(Restaurant restaurant,String KeyList);

    //搜索菜单（模糊查询）
    public List<Restaurant> searchRestaurant(String restaurantIntro);

    //根据商品热度降序排序
    public List<Restaurant> selectRestaurantListByFoodPopularitySortDesc(Map<String,Object> map);

    //根据菜单分类筛选
    public List<Restaurant> selectRestaurantListByRestaurantType(Map<String,Object> map);

    //批量修改状态
    public int updateStatesBatch(List list,Integer states,String KeyList);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
