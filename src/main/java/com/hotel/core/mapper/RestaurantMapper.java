package com.hotel.core.mapper;

import com.hotel.core.entity.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Restaurant restaurant);

    int insertSelective(Restaurant restaurant);

    Restaurant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Restaurant restaurant);

    int updateByPrimaryKey(Restaurant restaurant);

    //获取菜单分页列表
    List<Restaurant> selectRestaurantByLimit(Map<String,Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改菜单状态
    int updateRestaurantByStates(Restaurant restaurant);

    //搜索菜单（模糊查询）
    List<Restaurant> searchRestaurant(String restaurantIntro);

    //根据商品热度降序排序
    List<Restaurant> selectRestaurantListByFoodPopularitySortDesc(Map<String,Object> map);

    //根据菜单分类筛选
    List<Restaurant> selectRestaurantListByRestaurantType(Map<String,Object> map);

    //批量修改状态
    int updateStatesBatch(List list,Integer states);

    //批量删除
    int deleteBatch(List list);

}