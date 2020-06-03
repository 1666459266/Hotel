package com.hotel.core.service;

import com.hotel.core.entity.Coupon;

import java.util.List;
import java.util.Map;

public interface CouponService {

    //添加优惠券
    public int insertCoupon(Coupon coupon, String KeyList);

    //修改优惠券
    public int updateCoupon(Coupon coupon, String KeyList);

    //根据id删除优惠券
    public int deleteCouponById(Integer id, String KeyList);

    //根据id查询优惠券
    public Coupon selectCouponById(Integer id);

    //获取商品分页列表
    public List<Coupon> selectCouponListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改优惠券状态
    public int updateCouponByStates(Coupon coupon, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
