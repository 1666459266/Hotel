package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.AccommodationRecord;
import com.hotel.core.entity.Room;
import com.hotel.core.entity.UserRoom;
import com.hotel.core.entity.Users;
import com.hotel.core.mapper.AccommodationRecordMapper;
import com.hotel.core.mapper.UserRoomMapper;
import com.hotel.core.service.RoomService;
import com.hotel.core.service.UserRoomService;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.BaseController;
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
public class UserRoomServiceImpl implements UserRoomService {

    @Autowired
    private UsersService usersService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserRoomMapper userRoomMapper;
    @Autowired
    private AccommodationRecord accommodationRecord;
    @Autowired
    private AccommodationRecordMapper accommodationRecordMapper;


    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userRoom",key = "#userRoom.id"),
                      @CacheEvict(cacheNames = "room",key = "#userRoom.roomId"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByUserId",key = "#userRoom.userId")})
    @Override
    public int insertUserRoom(UserRoom userRoom, String KeyList) {
        System.out.println("启用Service...insertUserRoom");
        return userRoomMapper.insertSelective(userRoom);
    }

    //只清除修改的房间缓存
    @CacheEvict(cacheNames = "room",key = "#userRoom.roomId")
    @Override
    public int updateRoomByUserRoom(UserRoom userRoom, String KeyList) {
        System.out.println("启用Service...updateRoomByUserRoom");
        Room room = roomService.selectRoomById(userRoom.getRoomId());
        //将房间状态设置为无人
        room.setRoomStates(1);
        return roomService.updateRoom(room, BaseController.KEYLIST);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userRoom",key = "#userRoom.id"),
                      @CacheEvict(cacheNames = "room",key = "#userRoom.roomId"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByUserId",key = "#userRoom.userId")})
    @Override
    public int updateUserRoom(UserRoom userRoom, String KeyList, Integer roomIdBefore) {
        System.out.println("启用Service...updateUserRoom");
        int i = 0;
        int j = 0;
        try {
            //根据传入的userId和roomId查询用户和房间
            Users users = usersService.selectUsersById(userRoom.getUserId());
            Room room = roomService.selectRoomById(userRoom.getRoomId());
            //判断是否修改房间号
            if (roomIdBefore == -1) {
                //未修改房间号 只修改入住和离店时间
                return userRoomMapper.updateByPrimaryKeySelective(userRoom);
            }else {
                //已修改房间号 判断房间是否有人或已预定
                if (room.getRoomStates() == 2 || room.getRoomStates() == 3){
                    return 0;
                }
                //将房间的状态修改为已预定
                room.setRoomStates(3);
                i = roomService.updateRoom(room, BaseController.KEYLIST);
                //存入用户房间订单
                userRoom.setUserId(users.getId());
                userRoom.setRoomId(room.getId());
                userRoom.setUsername(users.getUsername());
                userRoom.setRoomNumber(room.getRoomNumber());
                userRoom.setRoomType(room.getRoomType());
                userRoom.setRoomPrice(room.getRoomPrice());
                j = userRoomMapper.updateByPrimaryKeySelective(userRoom);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRoom",key = "#id"),
                      @CacheEvict(cacheNames = "room",key = "#roomId"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByUserId",key = "#userId")})
    @Override
    public int deleteUserRoomById(Integer id,Integer userId,Integer roomId, String KeyList) {
        System.out.println("启用Service...deleteUserRoomById");
        int i = 0;
        int j = 0;
        try {
            Room room = roomService.selectRoomById(roomId);
            //将房间状态设置为无人
            room.setRoomStates(1);
            i = roomService.updateRoom(room, BaseController.KEYLIST);
            j = userRoomMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Cacheable(cacheNames = "userRoom",key = "#id")
    @Override
    public UserRoom selectUserRoomById(Integer id) {
        System.out.println("启用Service...selectUserRoomById");
        return userRoomMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserRoom> selectUserRoomListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserRoomListByLimit");
        return userRoomMapper.selectUserRoomListByLimit(map);
    }

    @Override
    public int selectCounts(String KeyList) {
        System.out.println("启用Service...selectCounts");
        return userRoomMapper.selectCounts();
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userRoom",key = "#userRoom.id"),
                      @CacheEvict(cacheNames = "room",key = "#userRoom.roomId"),
                      @CacheEvict(cacheNames = "roomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserRoom",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByUserId",key = "#userRoom.userId")})
    @Override
    public int cancel(UserRoom userRoom, String KeyList) {
        System.out.println("启用Service...cancel");
        int i = 0;
        int j = 0;
        int l = 0;
        try {
            Room room = roomService.selectRoomById(userRoom.getRoomId());
//            //将房间状态设置为无人
            room.setRoomStates(1);
            Users users = usersService.selectUsersById(userRoom.getUserId());
            //交易完成后增加用户积分 提升会员等级
            Integer MembershipScore = Integer.parseInt(users.getMembershipScore());
            Integer FoodUnitPrice = Integer.parseInt(userRoom.getRoomPrice());
            String MembershipScoreX = Integer.toString(MembershipScore+FoodUnitPrice);
            users.setMembershipScore(MembershipScoreX);
            Integer score = MembershipScore + FoodUnitPrice;
            if (score >= 0 && score < 600){
                users.setMembershipLevel(1);
            }else if (score >= 600 && score < 1800){
                users.setMembershipLevel(2);
            }else if (score >= 1800 && score < 3600){
                users.setMembershipLevel(3);
            }else if (score >= 3600 && score < 6000){
                users.setMembershipLevel(4);
            }else if (score >= 6000 && score < 10800){
                users.setMembershipLevel(5);
            }else if (score >= 10800 && score < 32400){
                users.setMembershipLevel(6);
            }else if (score >= 32400 && score < 46800){
                users.setMembershipLevel(7);
            }else {
                users.setMembershipLevel(8);
            }

            accommodationRecord.setUserId(userRoom.getUserId());
            accommodationRecord.setRoomId(userRoom.getRoomId());
            accommodationRecord.setUsername(userRoom.getUsername());
            accommodationRecord.setOrderNumber(userRoom.getOrderNumber());
            accommodationRecord.setRoomType(userRoom.getRoomType());
            accommodationRecord.setRoomNumber(userRoom.getRoomNumber());
            accommodationRecord.setRoomPrice(userRoom.getRoomPrice());
            accommodationRecord.setCheckinDate(userRoom.getOrderTime());
            accommodationRecord.setDepartureDate(userRoom.getLeaveTime());
            accommodationRecordMapper.insert(accommodationRecord);

            //更新会员等级和用户积分
            l = usersService.updateUser(users, BaseController.KEYLIST);
            //更新房间状态
            i = roomService.updateRoom(room, BaseController.KEYLIST);
            //将订单设置为过期
            j = userRoomMapper.cancel(userRoom);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j+l;
    }

    @Override
    public List<UserRoom> searchUserRoom(String orderNumber, String username) {
        System.out.println("启用Service...searchUserRoom");
        return userRoomMapper.searchUserRoom(orderNumber,username);
    }

    @Override
    public List<UserRoom> selectPaidUserRoom(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectPaidUserRoom");
        return userRoomMapper.selectPaidUserRoom(map);
    }

    @Override
    public List<UserRoom> selectUnPaidUserRoom(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserRoom");
        return userRoomMapper.selectUnPaidUserRoom(map);
    }

    @Override
    public List<UserRoom> selectUserRoomListByOrderTimeSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserRoom");
        return userRoomMapper.selectUserRoomListByOrderTimeSortDesc(map);
    }

    @Override
    public List<UserRoom> selectUserRoomListByOrderTimeSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserRoom");
        return userRoomMapper.selectUserRoomListByOrderTimeSortAsc(map);
    }

    @Override
    public List<UserRoom> selectUserRoomListByLeaveTimeSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserRoom");
        return userRoomMapper.selectUserRoomListByLeaveTimeSortDesc(map);
    }

    @Override
    public List<UserRoom> selectUserRoomListByLeaveTimeSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserRoom");
        return userRoomMapper.selectUserRoomListByLeaveTimeSortAsc(map);
    }

    @Cacheable(cacheNames = "userRoomListByUserId",key = "#userId")
    @Override
    public List<UserRoom> selectUserRoomListByUserId(Integer userId) {
        System.out.println("启用Service...selectUserRoomListByUserId");
        return userRoomMapper.selectUserRoomListByUserId(userId);
    }

    @Override
    public List<UserRoom> selectUserRoomListByUserIdAndTime(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectUserRoomListByUserIdAndTime");
        return userRoomMapper.selectUserRoomListByUserIdAndTime(userId,start,end);
    }

    @Override
    public List<UserRoom> selectUserRoomListByTime(Date start, Date end) {
        System.out.println("启用Service...selectUserRoomListByTime");
        return userRoomMapper.selectUserRoomListByTime(start,end);
    }

    @Override
    public List<Map<Object,Object>> selectUserPriceByDay(Integer userId, Date start,Date end) {
        System.out.println("启用Service...selectUserPriceByDay");
        return userRoomMapper.selectUserPriceByDay(userId, start, end);
    }

    @Override
    public List<Map<Object,Object>> selectUserPriceByMonth(Integer userId, Date start,Date end) {
        System.out.println("启用Service...selectUserPriceByMonth");
        return userRoomMapper.selectUserPriceByMonth(userId, start, end);
    }

    @Override
    public List<Map<Object,Object>> selectUserPriceByYear(Integer userId, Date start,Date end) {
        System.out.println("启用Service...selectUserPriceByYear");
        return userRoomMapper.selectUserPriceByYear(userId, start, end);
    }

    @Override
    public List<Map<Object,Object>> selectCountsByDay(Date start,Date end) {
        System.out.println("启用Service...selectCountsByDay");
        return userRoomMapper.selectCountsByDay(start, end);
    }

    @Override
    public List<Map<Object,Object>> selectPriceByDay(Date start,Date end) {
        System.out.println("启用Service...selectPriceByDay");
        return userRoomMapper.selectPriceByDay(start, end);
    }

    @Override
    public List<Map<Object,Object>> selectCountsByMonth(Date start,Date end) {
        System.out.println("启用Service...selectCountsByMonth");
        return userRoomMapper.selectCountsByMonth(start, end);
    }

    @Override
    public List<Map<Object,Object>> selectPriceByMonth(Date start,Date end) {
        System.out.println("启用Service...selectPriceByMonth");
        return userRoomMapper.selectPriceByMonth(start, end);
    }

    @Override
    public List<Map<Object,Object>> selectCountsByYear(Date start,Date end) {
        System.out.println("启用Service...selectCountsByYear");
        return userRoomMapper.selectCountsByYear(start, end);
    }

    @Override
    public List<Map<Object,Object>> selectPriceByYear(Date start,Date end) {
        System.out.println("启用Service...selectPriceByYear");
        return userRoomMapper.selectPriceByYear(start, end);
    }

    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return userRoomMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRoomListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoomListByLeaveTimeSortAsc",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
