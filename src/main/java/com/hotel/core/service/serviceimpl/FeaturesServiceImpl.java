package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Features;
import com.hotel.core.mapper.FeaturesMapper;
import com.hotel.core.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeaturesServiceImpl implements FeaturesService {

    @Autowired
    private FeaturesMapper featuresMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "features",key="#features.id"),
                      @CacheEvict(cacheNames = "featuresList",key="#KeyList" ),
                      @CacheEvict(cacheNames = "featuresCounts",key="#KeyList")})
    @Override
    public int insertFeatures(Features features, String KeyList) {
        System.out.println("启用Service...insertFeatures");
        return featuresMapper.insertSelective(features);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "features",key="#features.id"),
                      @CacheEvict(cacheNames = "featuresList",key="#KeyList" ),
                      @CacheEvict(cacheNames = "featuresCounts",key="#KeyList")})
    @Override
    public int updateFeatures(Features features, String KeyList) {
        System.out.println("启用Service...updateFeatures");
        return featuresMapper.updateByPrimaryKeySelective(features);
    }
    @Caching(evict = {@CacheEvict(cacheNames = "features",key="#id"),
                      @CacheEvict(cacheNames = "featuresList",key="#KeyList" ),
                      @CacheEvict(cacheNames = "featuresCounts",key="#KeyList")})
    @Override
    public int deleteFeaturesById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteFeaturesById");
        return featuresMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "features",key = "#id")
    @Override
    public Features selectFeaturesById(Integer id) {
        System.out.println("启用Service...selectFeaturesById");
        return featuresMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Features> selectFeaturesListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectFeaturesListByLimit");
        return featuresMapper.selectFeaturesByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return featuresMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "features",key="#features.id"),
                      @CacheEvict(cacheNames = "featuresList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "featuresCounts",key = "#KeyList")})
    @Override
    public int updateFeaturesByStates(Features features, String KeyList) {
        System.out.println("启用Service...updateFeaturesByStates");
        return featuresMapper.updateFeaturesByStates(features);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "featuresList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "featuresCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return featuresMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "featuresList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "featuresCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return featuresMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "featuresList",key="#KeyList" ),
            @CacheEvict(cacheNames = "featuresCounts",key="#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
