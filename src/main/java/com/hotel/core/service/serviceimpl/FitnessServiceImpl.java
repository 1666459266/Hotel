package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Fitness;
import com.hotel.core.mapper.FitnessMapper;
import com.hotel.core.service.FitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FitnessServiceImpl implements FitnessService {

    @Autowired
    private FitnessMapper fitnessMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "fitness",key = "#fitness.id"),
                      @CacheEvict(cacheNames = "fitnessList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "fitnessCounts",key = "#KeyList")})
    @Override
    public int insertFitness(Fitness fitness, String KeyList) {
        System.out.println("启用Service...insertFitness");
        return fitnessMapper.insertSelective(fitness);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "fitness",key = "#fitness.id"),
                      @CacheEvict(cacheNames = "fitnessList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "fitnessCounts",key = "#KeyList")})
    @Override
    public int updateFitness(Fitness fitness, String KeyList) {
        System.out.println("启用Service...updateFitness");
        return fitnessMapper.updateByPrimaryKeySelective(fitness);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "fitness",key = "#id"),
                      @CacheEvict(cacheNames = "fitnessList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "fitnessCounts",key = "#KeyList")})
    @Override
    public int deleteFitnessById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteFitnessById");
        return fitnessMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "fitness",key = "#id")
    @Override
    public Fitness selectFitnessById(Integer id) {
        System.out.println("启用Service...selectFitnessById");
        return fitnessMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Fitness> selectFitnessListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectFitnessListByLimit");
        return fitnessMapper.selectFitnessByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return fitnessMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "fitness",key = "#fitness.id"),
                      @CacheEvict(cacheNames = "fitnessList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "fitnessCounts",key = "#KeyList")})
    @Override
    public int updateFitnessByStates(Fitness fitness, String KeyList) {
        System.out.println("启用Service...updateFitnessByStates");
        return fitnessMapper.updateFitnessByStates(fitness);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "fitnessList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "fitnessCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return fitnessMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "fitnessList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "fitnessCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return fitnessMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "fitnessList",key = "#KeyList"),
            @CacheEvict(cacheNames = "fitnessCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
