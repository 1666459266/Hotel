package com.hotel.core.mapper;

import com.hotel.core.entity.Coupon;

import java.util.List;
import java.util.Map;

public interface CouponMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon coupon);

    int insertSelective(Coupon coupon);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon coupon);

    int updateByPrimaryKey(Coupon coupon);

    //获取优惠券分页列表
    List<Coupon> selectCouponByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改版权信息状态
    int updateCouponByStates(Coupon coupon);

    //批量修改状态
    int updateStatesBatch(List list, Integer states);

    //批量删除
    int deleteBatch(List list);

}