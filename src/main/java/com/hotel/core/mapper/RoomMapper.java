package com.hotel.core.mapper;

import com.hotel.core.entity.Room;

import java.util.List;
import java.util.Map;

public interface RoomMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Room room);

    int insertSelective(Room room);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room room);

    int updateByPrimaryKey(Room room);

    //获取房间分页列表
    List<Room> selectRoomListByLimit(Map<String,Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改房间状态
    int updateRoomByStates(Room room);

    //搜索房间
    List<Room> searchRoom(Integer roomNumber);

    //根据楼层筛选
    List<Room> selectRoomListByRoomFloor(Map<String,Object> map);

    //根据房间类型筛选
    List<Room> selectRoomListByRoomType(Map<String,Object> map);

    //根据床型筛选
    List<Room> selectRoomListByRoomBedType(Map<String,Object> map);

    //批量修改状态
    int updateStatesBatch(List list,Integer states);

    //批量删除
    int deleteBatch(List list);

}