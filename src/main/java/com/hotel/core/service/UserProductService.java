package com.hotel.core.service;

import com.hotel.core.entity.UserProduct;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserProductService {

    //查询用户商品订单
    public int insertUserProduct(UserProduct userProduct,String KeyList);

    //修改用户商品订单之前先将之前购买的商品恢复原样
    public int updateProductByUserProduct(UserProduct userProduct,String KeyList);

    //修改用户商品订单
    public int updateUserProduct(UserProduct userProduct,String KeyList);

    //根据id删除用户商品订单
    public int deleteUserProductById(Integer id,Integer userId,Integer productId,Integer productNum,String KeyList);

    //根据id查询用户商品订单
    public UserProduct selectUserProductById(Integer id);

    //获取用户商品订单分页列表
    public List<UserProduct> selectUserProductListByLimit(Map<String,Object> map,String KeyList);

    //统计条数
    public int selectCounts(String KeyList);

    //交易完成
    public int cancel(UserProduct userProduct,String KeyList);

    //搜索订单（模糊查询）
    public List<UserProduct> searchUserProduct(String orderNumber,String username);

    //查询已付款的订单
    public List<UserProduct> selectPaidUserProduct(Map<String,Object> map,String KeyList);

    //查询未付款的订单
    public List<UserProduct> selectUnPaidUserProduct(Map<String,Object> map,String KeyList);

    //根据下单时间降序排序
    public List<UserProduct> selectUserProductListByOrderTimeSortDesc(Map<String,Object> map,String KeyList);

    //根据下单时间升序排序
    public List<UserProduct> selectUserProductListByOrderTimeSortAsc(Map<String,Object> map,String KeyList);

    //查询某个用户的所有订单
    public List<UserProduct> selectUserProductListByUserId(Integer userId);

    //查询某个用户任意时间段的订单
    public List<UserProduct> selectUserProductListByUserIdAndTime(Integer userId, Date start, Date end);

    //查询任意时间段的所有订单
    public List<UserProduct> selectUserProductListByTime(Date start,Date end);

    //统计某个用户任意时间段的消费（以天分组）
    public List<Map<Object,Object>> selectUserPriceByDay(Integer userId,Date start,Date end);

    //统计某个用户任意时间段的消费（以月分组）
    public List<Map<Object,Object>> selectUserPriceByMonth(Integer userId,Date start,Date end);

    //统计某个用户任意时间段的消费（以年分组）
    public List<Map<Object,Object>> selectUserPriceByYear(Integer userId,Date start,Date end);

    //统计任意时间段的总数量（以天分组）
    public List<Map<Object,Object>> selectCountsByDay(Date start,Date end);

    //统计任意时间段的总金额（以天分组）
    public List<Map<Object,Object>> selectPriceByDay(Date start,Date end);

    //统计任意时间段的总数量（以月分组）
    public List<Map<Object,Object>> selectCountsByMonth(Date start,Date end);

    //统计任意时间段的总金额（以月分组）
    public List<Map<Object,Object>> selectPriceByMonth(Date start,Date end);

    //统计任意时间段的总数量（以年分组）
    public List<Map<Object,Object>> selectCountsByYear(Date start,Date end);

    //统计任意时间段的总金额（以年分组）
    public List<Map<Object,Object>> selectPriceByYear(Date start,Date end);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
