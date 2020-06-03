package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.History;
import com.hotel.core.mapper.HistoryMapper;
import com.hotel.core.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "history",key = "#history.id"),
            @CacheEvict(cacheNames = "historyList",key = "#KeyList"),
            @CacheEvict(cacheNames = "historyCounts",key = "#KeyList")})
    @Override
    public int insertHistory(History history, String KeyList) {
        System.out.println("启用Service...insertHistory");
        return historyMapper.insertSelective(history);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "history",key = "#history.id"),
            @CacheEvict(cacheNames = "historyList",key = "#KeyList"),
            @CacheEvict(cacheNames = "historyCounts",key = "#KeyList")})
    @Override
    public int updateHistory(History history, String KeyList) {
        System.out.println("启用Service...updateHistory");
        return historyMapper.updateByPrimaryKeySelective(history);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "history",key = "#history.id"),
            @CacheEvict(cacheNames = "historyList",key = "#KeyList"),
            @CacheEvict(cacheNames = "historyCounts",key = "#KeyList")})
    @Override
    public int deleteHistoryById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteHistoryById");
        return historyMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "history",key = "#id")
    @Override
    public History selectHistoryById(Integer id) {
        System.out.println("启用Service...selectHistoryById");
        return historyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<History> selectHistoryListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...insertProduct");
        return historyMapper.selectHistoryByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...insertProduct");
        return historyMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "history",key = "#history.id"),
            @CacheEvict(cacheNames = "historyList",key = "#KeyList"),
            @CacheEvict(cacheNames = "historyCounts",key = "#KeyList")})
    @Override
    public int updateHistoryByStates(History history, String KeyList) {
        System.out.println("启用Service...insertProduct");
        return historyMapper.updateHistoryByStates(history);
    }


    @Caching(evict = {@CacheEvict(cacheNames = "historyList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "historyCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return historyMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "historyList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "historyCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return historyMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "historyList",key = "#KeyList"),
            @CacheEvict(cacheNames = "historyCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
