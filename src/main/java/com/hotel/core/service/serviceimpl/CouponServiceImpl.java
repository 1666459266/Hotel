package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Coupon;
import com.hotel.core.mapper.CouponMapper;
import com.hotel.core.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "coupon",key = "#coupon.id"),
            @CacheEvict(cacheNames = "couponList",key = "#KeyList"),
            @CacheEvict(cacheNames = "couponCounts",key = "#KeyList")})
    @Override
    public int insertCoupon(Coupon coupon, String KeyList) {
        System.out.println("启用Service...insertCoupon");
        return couponMapper.insertSelective(coupon);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "coupon",key = "#coupon.id"),
            @CacheEvict(cacheNames = "couponList",key = "#KeyList"),
            @CacheEvict(cacheNames = "couponCounts",key = "#KeyList")})
    @Override
    public int updateCoupon(Coupon coupon, String KeyList) {
        System.out.println("启用Service...updateCoupon");
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "coupon",key = "#id"),
            @CacheEvict(cacheNames = "couponList",key = "#KeyList"),
            @CacheEvict(cacheNames = "couponCounts",key = "#KeyList")})
    @Override
    public int deleteCouponById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteCouponById");
        return couponMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "coupon",key = "#id")
    @Override
    public Coupon selectCouponById(Integer id) {
        System.out.println("启用Service...selectCouponById");
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Coupon> selectCouponListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectCouponListByLimit");
        return couponMapper.selectCouponByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return couponMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "coupon",key = "#coupon.id"),
            @CacheEvict(cacheNames = "couponList",key = "#KeyList"),
            @CacheEvict(cacheNames = "couponCounts",key = "#KeyList")})
    @Override
    public int updateCouponByStates(Coupon coupon, String KeyList) {
        System.out.println("启用Service...updateCouponByStates");
        return couponMapper.updateCouponByStates(coupon);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "couponList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "couponCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list, Integer states, String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return couponMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "couponList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "couponCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return couponMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "couponList",key = "#KeyList"),
            @CacheEvict(cacheNames = "couponCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
