package com.hotel.core.mapper;

import com.hotel.core.entity.UserRoom;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserRoomMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoom record);

    int insertSelective(UserRoom record);

    UserRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoom record);

    int updateByPrimaryKey(UserRoom record);

    //获取用户的房间订单分页列表
    List<UserRoom> selectUserRoomListByLimit(Map<String,Object> map);

    //统计条数
    int selectCounts();

    //交易完成
    int cancel(UserRoom userRoom);

    //搜索订单（模糊查询）
    List<UserRoom> searchUserRoom(String orderNumber,String username);

    //查询已付款的订单
    List<UserRoom> selectPaidUserRoom(Map<String,Object> map);

    //查询未付款的订单
    List<UserRoom> selectUnPaidUserRoom(Map<String,Object> map);

    //根据下单时间降序排序
    List<UserRoom> selectUserRoomListByOrderTimeSortDesc(Map<String,Object> map);

    //根据下单时间升序排序
    List<UserRoom> selectUserRoomListByOrderTimeSortAsc(Map<String,Object> map);

    //根据离店时间降序排序
    List<UserRoom> selectUserRoomListByLeaveTimeSortDesc(Map<String,Object> map);

    //根据离店时间升序排序
    List<UserRoom> selectUserRoomListByLeaveTimeSortAsc(Map<String,Object> map);

    //查询某个用户的所有订单
    List<UserRoom> selectUserRoomListByUserId(Integer userId);

    //查询某个用户任意时间段的订单
    List<UserRoom> selectUserRoomListByUserIdAndTime(Integer userId,Date start,Date end);

    //查询任意时间段的所有订单
    List<UserRoom> selectUserRoomListByTime(Date start,Date end);

    //统计某个用户任意时间段的消费（以天分组）
    List<Map<Object,Object>> selectUserPriceByDay(Integer userId,Date start,Date end);

    //统计某个用户任意时间段的消费（以月分组）
    List<Map<Object,Object>> selectUserPriceByMonth(Integer userId,Date start,Date end);

    //统计某个用户任意时间段的消费（以年分组）
    List<Map<Object,Object>> selectUserPriceByYear(Integer userId,Date start,Date end);

    //统计任意时间段的总数量（以天分组）
    List<Map<Object,Object>> selectCountsByDay(Date start,Date end);

    //统计任意时间段的总金额（以天分组）
    List<Map<Object,Object>> selectPriceByDay(Date start,Date end);

    //统计任意时间段的总数量（以月分组）
    List<Map<Object,Object>> selectCountsByMonth(Date start,Date end);

    //统计任意时间段的总金额（以月分组）
    List<Map<Object,Object>> selectPriceByMonth(Date start,Date end);

    //统计任意时间段的总数量（以年分组）
    List<Map<Object,Object>> selectCountsByYear(Date start,Date end);

    //统计任意时间段的总金额（以年分组）
    List<Map<Object,Object>> selectPriceByYear(Date start,Date end);

    //批量删除
    int deleteBatch(List list);

}