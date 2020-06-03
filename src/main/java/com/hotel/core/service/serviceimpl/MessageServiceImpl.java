package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Message;
import com.hotel.core.mapper.MessageMapper;
import com.hotel.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "message",key = "#message.id"),
            @CacheEvict(cacheNames = "messageList",key = "#KeyList"),
            @CacheEvict(cacheNames = "messageCounts",key = "#KeyList")})
    @Override
    public int insertMessage(Message message, String KeyList) {
        System.out.println("启用Service...insertMessage");
        return messageMapper.insertSelective(message);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "message",key = "#message.id"),
            @CacheEvict(cacheNames = "messageList",key = "#KeyList"),
            @CacheEvict(cacheNames = "messageCounts",key = "#KeyList")})
    @Override
    public int updateMessage(Message message, String KeyList) {
        System.out.println("启用Service...updateMessage");
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "message",key = "#message.id"),
            @CacheEvict(cacheNames = "messageList",key = "#KeyList"),
            @CacheEvict(cacheNames = "messageCounts",key = "#KeyList")})
    @Override
    public int deleteMessageById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteMessageById");
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "message",key = "#id")
    @Override
    public Message selectMessageById(Integer id) {
        System.out.println("启用Service...selectMessageById");
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Message> selectMessageListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectMessageListByLimit");
        return messageMapper.selectMessageByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return messageMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "message",key = "#message.id"),
            @CacheEvict(cacheNames = "messageList",key = "#KeyList"),
            @CacheEvict(cacheNames = "messageCounts",key = "#KeyList")})
    @Override
    public int updateMessageByStates(Message message, String KeyList) {
        System.out.println("启用Service...updateMessageByStates");
        return messageMapper.updateMessageByStates(message);
    }


    @Caching(evict = {@CacheEvict(cacheNames = "messageList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "messageCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return messageMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "messageList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "messageCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return messageMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "messageList",key = "#KeyList"),
            @CacheEvict(cacheNames = "messageCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
