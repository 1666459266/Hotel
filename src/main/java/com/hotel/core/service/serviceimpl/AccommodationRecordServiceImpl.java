package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.AccommodationRecord;
import com.hotel.core.entity.Room;
import com.hotel.core.entity.Users;
import com.hotel.core.mapper.AccommodationRecordMapper;
import com.hotel.core.mapper.RoomMapper;
import com.hotel.core.mapper.UsersMapper;
import com.hotel.core.service.AccommodationRecordService;
import com.hotel.core.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AccommodationRecordServiceImpl implements AccommodationRecordService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private AccommodationRecordMapper accommodationRecordMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "accommodationRecord",key = "#accommodationRecord.id"),
                      @CacheEvict(cacheNames = "room",key = "#accommodationRecord.roomId"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByUserId",key = "#accommodationRecord.userId")})
    @Override
    public int insertAccommodationRecordRoom(AccommodationRecord accommodationRecord, String KeyList) {
        System.out.println("启用Service...insertAccommodationRecord");
        return accommodationRecordMapper.insertSelective(accommodationRecord);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "accommodationRecord",key = "#accommodationRecord.id"),
            @CacheEvict(cacheNames = "room",key = "#accommodationRecord.roomId"),
            @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordList",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordCounts",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortDesc",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortAsc",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortDesc",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortAsc",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByUserId",key = "#accommodationRecord.userId")})
    @Override
    public int updateAccommodationRecord(AccommodationRecord accommodationRecord, String KeyList) {
        System.out.println("启用Service...updateAccommodationRecord");
        int i = 0;
        int j = 0;
        try {
            //根据传入的userId和roomId查询用户和房间
            Users users = usersMapper.selectByPrimaryKey(accommodationRecord.getUserId());
            Room room = roomMapper.selectByPrimaryKey(accommodationRecord.getRoomId());
            if (room.getRoomStates() == 1){
                return 0;
            }
            //将房间的状态修改为无人
            room.setRoomStates(2);
            i = roomMapper.updateByPrimaryKeySelective(room);
            accommodationRecord.setUserId(users.getId());
            accommodationRecord.setRoomId(room.getId());
            accommodationRecord.setUsername(users.getUsername());
            accommodationRecord.setRoomNumber(room.getRoomNumber());
            accommodationRecord.setRoomType(room.getRoomType());
            accommodationRecord.setRoomPrice(room.getRoomPrice());
            if(room.getRoomStates() == 2){
                accommodationRecord.setDepartureDate(new Date());
            }
            j = accommodationRecordMapper.updateByPrimaryKeySelective(accommodationRecord);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Caching(evict = {@CacheEvict(cacheNames = "accommodationRecord",key = "#id"),
                      @CacheEvict(cacheNames = "accommodationRecordList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "accommodationRecordListByUserId",key = "#id")})
    @Override
    public int deleteAccommodationRecordById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteAccommodationRecord");
        return accommodationRecordMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "accommodationRecordList",key = "#id")
    @Override
    public AccommodationRecord selectAccommodationRecordById(Integer id) {
        System.out.println("启用Service...selectAccommodationRecord");
        return accommodationRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserRoomListByLimit");
        return accommodationRecordMapper.selectAccommodationRecordByLimit(map);
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordByUsername(String username) {
        System.out.println("启用Service...selectAccommodationRecordByUsername");
        return accommodationRecordMapper.selectAccommodationRecordByUserName(username);
    }

    @Override
    public int selectCounts(String KeyList) {
        System.out.println("启用Service...selectCounts");
        return accommodationRecordMapper.selectCounts();
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByCheckinDateSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectAccommodationRecordListByCheckinDateSortDesc");
        return accommodationRecordMapper.selectAccommodationRecordListByCheckinDateSortDesc(map);
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByCheckinDateSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectAccommodationRecordListByCheckinDateSortAsc");
        return accommodationRecordMapper.selectAccommodationRecordListByCheckinDateSortAsc(map);
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByDepartureDateSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectAccommodationRecordListByDepartureDateSortDesc");
        return accommodationRecordMapper.selectAccommodationRecordListByDepartureDateSortDesc(map);
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByDepartureDateSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectAccommodationRecordListByDepartureDateSortAsc");
        return accommodationRecordMapper.selectAccommodationRecordListByDepartureDateSortAsc(map);
    }

    @Cacheable(cacheNames = "userRoomListByUserId",key = "#userId")
    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByUserId(Integer userId) {
        System.out.println("启用Service...selectAccommodationRecordListByUserId");
        return accommodationRecordMapper.selectAccommodationRecordListByUserId(userId);
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByUserIdAndTime(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectUserRoomListByUserIdAndTime");
        return accommodationRecordMapper.selectAccommodationRecordListByUserIdAndTime(userId,start,end);
    }

    @Override
    public List<AccommodationRecord> selectAccommodationRecordListByTime(Date start, Date end) {
        System.out.println("启用Service...selectAccommodationRecordListByTime");
        return accommodationRecordMapper.selectAccommodationRecordListByTime(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByDay(String roomType, Date start, Date end) {
        System.out.println("启用Service...selectUserPriceByDay");
        return accommodationRecordMapper.selectCountsByDay(roomType, start, end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByMonth(String roomType, Date start, Date end) {
        System.out.println("启用Service...selectCountsByMonth");
        return accommodationRecordMapper.selectCountsByMonth(roomType, start, end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByYear(String roomType, Date start, Date end) {
        System.out.println("启用Service...selectCountsByYear");
        return accommodationRecordMapper.selectCountsByYear(roomType, start, end);
    }

    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return accommodationRecordMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "roomList",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordList",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordCounts",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortDesc",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByCheckinDateSortAsc",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortDesc",key = "#KeyList"),
            @CacheEvict(cacheNames = "accommodationRecordListByDepartureDateSortAsc",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
