package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Copyright;
import com.hotel.core.mapper.CopyrightMapper;
import com.hotel.core.service.CopyrightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CopyrightServiceImpl implements CopyrightService {

    @Autowired
    private CopyrightMapper copyrightMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "copyright",key = "#copyright.id"),
                      @CacheEvict(cacheNames = "copyrightList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "copyrightCounts",key = "#KeyList")})
    @Override
    public int insertCopyright(Copyright copyright, String KeyList) {
        System.out.println("启用Service...insertCopyright");
        return copyrightMapper.insertSelective(copyright);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "copyright",key="#copyright.id"),
                      @CacheEvict(cacheNames = "copyrightList",key="#KeyList"),
                      @CacheEvict(cacheNames = "copyrightCounts",key="#KeyList")})

    @Override
    public int updateCopyright(Copyright copyright, String KeyList) {
        System.out.println("启用Service...updateCopyright");
        return copyrightMapper.updateByPrimaryKeySelective(copyright);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "copyright",key="#id"),
                      @CacheEvict(cacheNames = "copyrightList",key="#KeyList"),
                      @CacheEvict(cacheNames = "copyrightCounts",key="#KeyList")})
    @Override
    public int deleteCopyrightById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteCopyrightById");
        return copyrightMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "copyright",key="#id")
    @Override
    public Copyright selectCopyrightById(Integer id) {
        System.out.println("启用Service...selectCopyrightById");
        return copyrightMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Copyright> selectCopyrightListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectCopyrightListByLimit");
        return copyrightMapper.selectCopyrightByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return copyrightMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "copyright", key = "#copyright.id"),
                      @CacheEvict(cacheNames = "copyrightList", key = "#KeyList"),
                      @CacheEvict(cacheNames = "copyrightCounts", key = "#KeyList")})
    @Override
    public int updateCopyrightByStates(Copyright copyright, String KeyList) {
        System.out.println("启用Service...updateCopyrightByStates");
        return copyrightMapper.updateCopyrightByStates(copyright);
    }


    @Caching(evict = {@CacheEvict(cacheNames = "copyrightList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "copyrightCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return copyrightMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "copyrightList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "copyrightCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return copyrightMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "copyrightList",key = "#KeyList"),
            @CacheEvict(cacheNames = "copyrightCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
