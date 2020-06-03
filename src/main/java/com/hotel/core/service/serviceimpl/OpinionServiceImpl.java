package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Opinion;
import com.hotel.core.mapper.OpinionMapper;
import com.hotel.core.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "opinion",key = "#opinion.id"),
            @CacheEvict(cacheNames = "opinionList",key = "#KeyList"),
            @CacheEvict(cacheNames = "opinionCounts",key = "#KeyList")})
    @Override
    public int insertOpinion(Opinion opinion, String KeyList) {
        System.out.println("启用Service...insertOpinion");
        return opinionMapper.insertSelective(opinion);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "opinion",key = "#opinion.id"),
            @CacheEvict(cacheNames = "opinionList",key = "#KeyList"),
            @CacheEvict(cacheNames = "opinionCounts",key = "#KeyList")})
    @Override
    public int updateOpinion(Opinion opinion, String KeyList) {
        System.out.println("启用Service...updateOpinion");
        return opinionMapper.updateByPrimaryKeySelective(opinion);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "opinion",key = "#opinion.id"),
            @CacheEvict(cacheNames = "opinionList",key = "#KeyList"),
            @CacheEvict(cacheNames = "opinionCounts",key = "#KeyList")})
    @Override
    public int deleteOpinionById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteOpinionById");
        return opinionMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "opinion",key = "#id")
    @Override
    public Opinion selectOpinionById(Integer id) {
        System.out.println("启用Service...selectOpinionById");
        return opinionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Opinion> selectOpinionListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectOpinionListByLimit");
        return opinionMapper.selectOpinionByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return opinionMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "opinion",key = "#opinion.id"),
            @CacheEvict(cacheNames = "opinionList",key = "#KeyList"),
            @CacheEvict(cacheNames = "opinionCounts",key = "#KeyList")})
    @Override
    public int updateOpinionByStates(Opinion opinion, String KeyList) {
        System.out.println("启用Service...updateOpinionByStates");
        return opinionMapper.updateOpinionByStates(opinion);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "opinionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "opinionCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return opinionMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "opinionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "opinionCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return opinionMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "opinionList",key = "#KeyList"),
            @CacheEvict(cacheNames = "opinionCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
