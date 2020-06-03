package com.hotel.core.mapper;

import com.hotel.core.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Message message);

    int insertSelective(Message message);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message message);

    int updateByPrimaryKey(Message message);

    //获取信息分页列表
    List<Message> selectMessageByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改信息状态
    int updateMessageByStates(Message message);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}