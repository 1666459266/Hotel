package com.hotel.core.mapper;

import com.hotel.core.entity.UserRestaurant;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserRestaurantMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRestaurant userRestaurant);

    int insertSelective(UserRestaurant userRestaurant);

    UserRestaurant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRestaurant userRestaurant);

    int updateByPrimaryKey(UserRestaurant userRestaurant);

    //获取用户的房间订单分页列表
    List<UserRestaurant> selectUserRestaurantListByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts();

    //交易完成
    int cancel(UserRestaurant userRestaurant);

    //搜索订单（模糊查询）
    List<UserRestaurant> searchUserRestaurant(String orderNumber, String username);

    //查询已付款的订单
    List<UserRestaurant> selectPaidUserRestaurant(Map<String, Object> map);

    //查询未付款的订单
    List<UserRestaurant> selectUnPaidUserRestaurant(Map<String, Object> map);

    //根据下单时间降序排序
    List<UserRestaurant> selectUserRestaurantListByOrderTimeSortDesc(Map<String, Object> map);

    //根据下单时间升序排序
    List<UserRestaurant> selectUserRestaurantListByOrderTimeSortAsc(Map<String, Object> map);

    //查询某个用户的所有订单
    List<UserRestaurant> selectUserRestaurantListByUserId(Integer userId);

    //查询某个用户任意时间段的订单
    List<UserRestaurant> selectUserRestaurantListByUserIdAndTime(Integer userId, Date start, Date end);

    //查询任意时间段的所有订单
    List<UserRestaurant> selectUserRestaurantListByTime(Date start, Date end);

    //统计某个用户任意时间段的消费（以天分组）
    List<Map<Object,Object>> selectUserPriceByDay(Integer userId, Date start, Date end);

    //统计某个用户任意时间段的消费（以月分组）
    List<Map<Object,Object>> selectUserPriceByMonth(Integer userId, Date start, Date end);

    //统计某个用户任意时间段的消费（以年分组）
    List<Map<Object,Object>> selectUserPriceByYear(Integer userId, Date start, Date end);

    //统计任意时间段的总数量（以天分组）
    List<Map<Object,Object>> selectCountsByDay(Date start, Date end);

    //统计任意时间段的总金额（以天分组）
    List<Map<Object,Object>> selectPriceByDay(Date start, Date end);

    //统计任意时间段的总数量（以月分组）
    List<Map<Object,Object>> selectCountsByMonth(Date start, Date end);

    //统计任意时间段的总金额（以月分组）
    List<Map<Object,Object>> selectPriceByMonth(Date start, Date end);

    //统计任意时间段的总数量（以年分组）
    List<Map<Object,Object>> selectCountsByYear(Date start, Date end);

    //统计任意时间段的总金额（以年分组）
    List<Map<Object,Object>> selectPriceByYear(Date start, Date end);

    //批量删除
    int deleteBatch(List list);

    //查询所有信息
    public List<UserRestaurant> selectAll();
}
