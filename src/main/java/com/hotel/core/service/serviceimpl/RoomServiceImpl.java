package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Room;
import com.hotel.core.mapper.RoomMapper;
import com.hotel.core.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "room",key = "#room.id"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roomCounts",key = "#KeyList")})
    @Override
    public int insertRoom(Room room,String KeyList) {
        System.out.println("启用Service...insertRoom");
        return roomMapper.insertSelective(room);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "room",key = "#room.id"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roomCounts",key = "#KeyList")})
    @Override
    public int updateRoom(Room room,String KeyList) {
        System.out.println("启用Service...updateRoom");
        return roomMapper.updateByPrimaryKeySelective(room);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "room",key = "#id"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roomCounts",key = "#KeyList")})
    @Override
    public int deleteRoomById(Integer id,String KeyList) {
        System.out.println("启用Service...deleteRoomById");
        return roomMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "room",key = "#id")
    @Override
    public Room selectRoomById(Integer id) {
        System.out.println("启用Service...selectRoomById");
        return roomMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Room> selectRoomListByLimit(Map<String, Object> map,String KeyList) {
        System.out.println("启用Service...selectRoomByList");
        return roomMapper.selectRoomListByLimit(map);
    }

    @Override
    public int selectCounts(Integer states,String KeyList) {
        System.out.println("启用Service...selectCounts");
        return roomMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "room",key = "#room.id"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roomCounts",key = "#KeyList")})
    @Override
    public int updateRoomByStates(Room room,String KeyList) {
        System.out.println("启用Service...updateRoomByStates");
        return roomMapper.updateRoomByStates(room);
    }

    @Override
    public List<Room> searchRoom(Integer roomNumber) {
        System.out.println("启用Service...searchRoom");
        return roomMapper.searchRoom(roomNumber);
    }

    @Override
    public List<Room> selectRoomListByRoomFloor(Map<String,Object> map) {
        System.out.println("启用Service...roomListByRoomFloor");
        return roomMapper.selectRoomListByRoomFloor(map);
    }

    @Override
    public List<Room> selectRoomListByRoomType(Map<String,Object> map) {
        System.out.println("启用Service...roomListByRoomType");
        return roomMapper.selectRoomListByRoomType(map);
    }

    @Override
    public List<Room> selectRoomListByRoomBedType(Map<String,Object> map) {
        System.out.println("启用Service...roomListByRoomBedType");
        return roomMapper.selectRoomListByRoomBedType(map);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roomCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list,Integer states,String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return roomMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roomCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return roomMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roomCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
