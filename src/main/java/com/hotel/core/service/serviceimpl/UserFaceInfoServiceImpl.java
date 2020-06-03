package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.UserFaceInfo;
import com.hotel.core.mapper.UserFaceInfoMapper;
import com.hotel.core.service.UserFaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserFaceInfoServiceImpl implements UserFaceInfoService {

    @Autowired
    private UserFaceInfoMapper userFaceInfoMapper;


    @Caching(evict = {@CacheEvict(cacheNames = "userFaceInfo",key = "#userFaceInfo.id"),
                      @CacheEvict(cacheNames = "userFaceInfoList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userFaceInfoCounts",key = "#KeyList")})
    @Override
    public int insertUserFaceSelective(UserFaceInfo userFaceInfo, String KeyList) {
        return userFaceInfoMapper.insertSelective(userFaceInfo);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userFaceInfo",key = "#userFaceInfo.id"),
                      @CacheEvict(cacheNames = "userFaceInfoList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userFaceInfoCounts",key = "#KeyList")})
    @Override
    public int updateUserFaceInfo(UserFaceInfo userFaceInfo, String KeyList) {
        return userFaceInfoMapper.updateByPrimaryKeySelective(userFaceInfo);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userFaceInfo",key = "#id"),
            @CacheEvict(cacheNames = "userFaceInfoList",key = "#KeyList"),
            @CacheEvict(cacheNames = "userFaceInfoCounts",key = "#KeyList")})
    @Override
    public int deleteUserFaceInfoById(Integer id, String KeyList) {
        return userFaceInfoMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "userFaceInfo",key = "#id")
    @Override
    public UserFaceInfo selectUserFaceInfoById(Integer id) {
        return userFaceInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserFaceInfo> selectUserFaceInfoListByLimit(Map<String, Object> map, String KeyList) {
        return userFaceInfoMapper.selectUserFaceInfoByLimit(map);
    }

    @Override
    public int selectCounts() {
        return userFaceInfoMapper.selectCounts();
    }

}
