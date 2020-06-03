package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Background;
import com.hotel.core.mapper.BackgroundMapper;
import com.hotel.core.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundMapper backgroundMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "background", key = "#background.id"),
                      @CacheEvict(cacheNames = "backgroundList", key = "#KeyList"),
                      @CacheEvict(cacheNames = "backgroundCounts", key = "#KeyList")})
    @Override
    public int insertBackground(Background background, String KeyList) {
        return backgroundMapper.insertSelective(background);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "background", key = "#background.id"),
                      @CacheEvict(cacheNames = "backgroundList", key = "#KeyList"),
                      @CacheEvict(cacheNames = "backgroundCounts", key = "#KeyList")})
    @Override
    public int updateBackground(Background background, String KeyList) {
        return backgroundMapper.updateByPrimaryKeySelective(background);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "background", key = "#id"),
                      @CacheEvict(cacheNames = "backgroundList", key = "#KeyList"),
                      @CacheEvict(cacheNames = "backgroundCounts", key = "#KeyList")})
    @Override
    public int deleteBackgroundById(Integer id, String KeyList) {
        return backgroundMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "background", key = "#id")
    @Override
    public Background selectBackgroundById(Integer id) {
        return backgroundMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Background> selectBackgroundListByLimit(Map<String, Object> map, String KeyList) {
        return backgroundMapper.selectBackgroundByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        return backgroundMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "background", key = "#background.id"),
                      @CacheEvict(cacheNames = "backgroundList", key = "#KeyList"),
                      @CacheEvict(cacheNames = "backgroundCounts", key = "#KeyList")})
    @Override
    public int updateBackgroundByStates(Background background, String KeyList) {
        return backgroundMapper.updateBackgroundByStates(background);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "backgroundList", key = "#KeyList"),
                      @CacheEvict(cacheNames = "backgroundCounts", key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return backgroundMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "backgroundList", key = "#KeyList"),
                      @CacheEvict(cacheNames = "backgroundCounts", key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return backgroundMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "backgroundList", key = "#KeyList"),
            @CacheEvict(cacheNames = "backgroundCounts", key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
