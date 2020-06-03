package com.hotel.core.service;

import com.hotel.core.entity.AccommodationRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccommodationRecordService {

    //添加用户的房间信息
    public int insertAccommodationRecordRoom(AccommodationRecord accommodationRecord, String KeyList);

    //修改住宿记录
    public int updateAccommodationRecord(AccommodationRecord accommodationRecord, String KeyList);

    //根据id删除记录
    public int deleteAccommodationRecordById(Integer id, String KeyList);

    //根据id查询记录
    public AccommodationRecord selectAccommodationRecordById(Integer id);

    //获取用户的住宿记录
    public List<AccommodationRecord> selectAccommodationRecordListByLimit(Map<String, Object> map, String KeyList);

    //根据用户名获取信息
    public List<AccommodationRecord>  selectAccommodationRecordByUsername(String username);

    //统计条数
    public int selectCounts(String KeyList);

    //根据下单时间降序排序
    public List<AccommodationRecord> selectAccommodationRecordListByCheckinDateSortDesc(Map<String, Object> map, String KeyList);

    //根据下单时间升序排序
    public List<AccommodationRecord> selectAccommodationRecordListByCheckinDateSortAsc(Map<String, Object> map, String KeyList);

    //根据离店时间降序排序
    public List<AccommodationRecord> selectAccommodationRecordListByDepartureDateSortDesc(Map<String, Object> map, String KeyList);

    //根据离店时间升序排序
    public List<AccommodationRecord> selectAccommodationRecordListByDepartureDateSortAsc(Map<String, Object> map, String KeyList);

    //查询某个用户的所有订单
    public List<AccommodationRecord> selectAccommodationRecordListByUserId(Integer userId);

    //查询某个用户任意时间段的住房记录
    public List<AccommodationRecord> selectAccommodationRecordListByUserIdAndTime(Integer userId, Date start, Date end);

    //查询任意时间段的所有住房记录
    public List<AccommodationRecord> selectAccommodationRecordListByTime(Date start, Date end);

    //统计任意时间段的总数量（以天分组）
    public List<Map<Object,Object>> selectCountsByDay(String roomType, Date start, Date end);

    //统计任意时间段的总数量（以月分组）
    public List<Map<Object,Object>> selectCountsByMonth(String roomType, Date start, Date end);

    //统计任意时间段的总数量（以年分组）
    public List<Map<Object,Object>> selectCountsByYear(String roomType, Date start, Date end);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
