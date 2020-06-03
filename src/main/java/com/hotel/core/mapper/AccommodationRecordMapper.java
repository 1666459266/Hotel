package com.hotel.core.mapper;

import com.hotel.core.entity.AccommodationRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccommodationRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AccommodationRecord accommodationRecord);

    int insertSelective(AccommodationRecord accommodationRecord);

    AccommodationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccommodationRecord accommodationRecord);

    int updateByPrimaryKey(AccommodationRecord accommodationRecord);

    //根据用户名查询信息
    List<AccommodationRecord> selectAccommodationRecordByUserName(String username);

    //获取记录分页列表
    List<AccommodationRecord> selectAccommodationRecordByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts();

    //搜索住房记录
    List<AccommodationRecord> searchAccommodationRecord(String username);

    //根据入住时间降序排序
    List<AccommodationRecord> selectAccommodationRecordListByCheckinDateSortDesc(Map<String, Object> map);

    //根据入住时间升序排序
    List<AccommodationRecord> selectAccommodationRecordListByCheckinDateSortAsc(Map<String, Object> map);

    //根据离开时间降序排序
    List<AccommodationRecord> selectAccommodationRecordListByDepartureDateSortDesc(Map<String, Object> map);

    //根据离开时间升序排序
    List<AccommodationRecord> selectAccommodationRecordListByDepartureDateSortAsc(Map<String, Object> map);

    //查询某个用户的所有住房记录
    List<AccommodationRecord> selectAccommodationRecordListByUserId(Integer userId);

    //查询某个用户任意时间段的住房记录
    List<AccommodationRecord> selectAccommodationRecordListByUserIdAndTime(Integer userId, Date start, Date end);

    //查询任意时间段的所有住房记录
    List<AccommodationRecord> selectAccommodationRecordListByTime(Date start, Date end);

    //统计任意时间段的总数量（以天分组）
    List<Map<Object,Object>> selectCountsByDay(String roomType, Date start, Date end);

    //统计任意时间段的总数量（以月分组）
    List<Map<Object,Object>> selectCountsByMonth(String roomType, Date start, Date end);

    //统计任意时间段的总数量（以年分组）
    List<Map<Object,Object>> selectCountsByYear(String roomType, Date start, Date end);

    //批量删除
    int deleteBatch(List list);

}