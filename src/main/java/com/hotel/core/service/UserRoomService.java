package com.hotel.core.service;

import com.hotel.core.entity.UserRoom;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserRoomService {

    //添加用户的房间订单
    public int insertUserRoom(UserRoom userRoom, String KeyList);

    //修改用户房间订单之前先修改之前预订的房间
    public int updateRoomByUserRoom(UserRoom userRoom,String KeyList);

    //修改用户的房间订单
    public int updateUserRoom(UserRoom userRoom,String KeyList,Integer roomIdBefore);

    //根据id删除用户的房间订单
    public int deleteUserRoomById(Integer id,Integer userId,Integer roomId,String KeyList);

    //根据id查询用户的房间订单
    public UserRoom selectUserRoomById(Integer id);

    //获取用户的房间订单
    public List<UserRoom> selectUserRoomListByLimit(Map<String,Object> map,String KeyList);

    //统计条数
    public int selectCounts(String KeyList);

    //交易完成
    public int cancel(UserRoom userRoom,String KeyList);

    //搜索订单（模糊查询）
    public List<UserRoom> searchUserRoom(String orderNumber,String username);

    //查询已付款的订单
    public List<UserRoom> selectPaidUserRoom(Map<String,Object> map,String KeyList);

    //查询未付款的订单
    public List<UserRoom> selectUnPaidUserRoom(Map<String,Object> map,String KeyList);

    //根据下单时间降序排序
    public List<UserRoom> selectUserRoomListByOrderTimeSortDesc(Map<String,Object> map,String KeyList);

    //根据下单时间升序排序
    public List<UserRoom> selectUserRoomListByOrderTimeSortAsc(Map<String,Object> map,String KeyList);

    //根据离店时间降序排序
    public List<UserRoom> selectUserRoomListByLeaveTimeSortDesc(Map<String,Object> map,String KeyList);

    //根据离店时间升序排序
    public List<UserRoom> selectUserRoomListByLeaveTimeSortAsc(Map<String,Object> map,String KeyList);

    //查询某个用户的所有订单
    public List<UserRoom> selectUserRoomListByUserId(Integer userId);

    //查询某个用户任意时间段的订单
    public List<UserRoom> selectUserRoomListByUserIdAndTime(Integer userId,Date start,Date end);

    //查询任意时间段的所有订单
    public List<UserRoom> selectUserRoomListByTime(Date start,Date end);

    //统计某个用户任意时间段的消费（以天分组）
    public List<Map<Object,Object>> selectUserPriceByDay(Integer userId,Date start,Date end);

    //统计某个用户任意时间段的消费（以月分组）
    public List<Map<Object,Object>> selectUserPriceByMonth(Integer userId,Date start,Date end);

    //统计某个用户任意时间段的消费（以年分组）
    public List<Map<Object,Object>> selectUserPriceByYear(Integer userId,Date start,Date end);

    //统计任意时间段的总数量（以天分组）
    public List<Map<Object,Object>> selectCountsByDay(Date start,Date end);

    //统计任意时间段的总金额（以天分组）
    public List<Map<Object,Object>> selectPriceByDay(Date start,Date end);

    //统计任意时间段的总数量（以月分组）
    public List<Map<Object,Object>> selectCountsByMonth(Date start,Date end);

    //统计任意时间段的总金额（以月分组）
    public List<Map<Object,Object>> selectPriceByMonth(Date start,Date end);

    //统计任意时间段的总数量（以年分组）
    public List<Map<Object,Object>> selectCountsByYear(Date start,Date end);

    //统计任意时间段的总金额（以年分组）
    public List<Map<Object,Object>> selectPriceByYear(Date start,Date end);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
