package com.hotel.core.service;

import com.hotel.core.entity.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {

    //添加房间
    public int insertRoom(Room room,String KeyList);

    //修改房间
    public int updateRoom(Room room,String KeyList);

    //根据id删除房间
    public int deleteRoomById(Integer id,String KeyList);

    //根据id查询房间
    public Room selectRoomById(Integer id);

    //获取房间分页列表
    public List<Room> selectRoomListByLimit(Map<String,Object> map,String KeyList);

    //统计条数
    public int selectCounts(Integer states,String KeyList);

    //修改房间状态
    public int updateRoomByStates(Room room,String KeyList);

    //搜索房间
    public List<Room> searchRoom(Integer roomNumber);

    //根据楼层筛选
    public List<Room> selectRoomListByRoomFloor(Map<String,Object> map);

    //根据房间类型筛选
    public List<Room> selectRoomListByRoomType(Map<String,Object> map);

    //根据床型筛选
    public List<Room> selectRoomListByRoomBedType(Map<String,Object> map);

    //批量修改状态
    public int updateStatesBatch(List list,Integer states,String KeyList);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
