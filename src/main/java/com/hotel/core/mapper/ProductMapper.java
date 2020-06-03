package com.hotel.core.mapper;

import com.hotel.core.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Product product);

    int insertSelective(Product product);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product product);

    int updateByPrimaryKey(Product product);

    //获取商品分页列表
    List<Product> selectProductByLimit(Map<String,Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改商品状态
    int updateProductByStates(Product product);

    //搜索商品（模糊查询）
    List<Product> searchProduct(String productName);

    //根据商品热度降序排序
    List<Product> selectProductListByProductPopularitySortDesc(Map<String,Object> map);

    //根据商品分类筛选
    List<Product> selectProductListByProductType(Map<String,Object> map);

    //批量修改状态
    int updateStatesBatch(List list,Integer states);

    //批量删除
    int deleteBatch(List list);

}