package com.hotel.core.service;

import com.hotel.core.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    //添加商品
    public int insertProduct(Product product,String KeyList);

    //修改商品
    public int updateProduct(Product product,String KeyList);

    //根据id删除商品
    public int deleteProductById(Integer id,String KeyList);

    //根据id查询商品
    public Product selectProductById(Integer id);

    //获取商品分页列表
    public List<Product> selectProductListByLimit(Map<String,Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states,String KeyList);

    //修改商品状态
    public int updateProductByStates(Product product,String KeyList);

    //搜索商品（模糊查询）
    public List<Product> searchProduct(String productName);

    //根据商品热度降序排序
    public List<Product> selectProductListByProductPopularitySortDesc(Map<String,Object> map);

    //根据商品分类筛选
    public List<Product> selectProductListByProductType(Map<String,Object> map);

    //批量修改状态
    public int updateStatesBatch(List list,Integer states,String KeyList);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
