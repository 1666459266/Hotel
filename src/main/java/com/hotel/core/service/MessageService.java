package com.hotel.core.service;

import com.hotel.core.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    //添加信息
    public int insertMessage(Message message, String KeyList);

    //修改信息
    public int updateMessage(Message message, String KeyList);

    //根据id删除信息
    public int deleteMessageById(Integer id, String KeyList);

    //根据id查询信息
    public Message selectMessageById(Integer id);

    //获取信息分页列表
    public List<Message> selectMessageListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改信息状态
    public int updateMessageByStates(Message message, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
