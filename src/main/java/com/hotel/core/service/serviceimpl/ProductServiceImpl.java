package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Product;
import com.hotel.core.mapper.ProductMapper;
import com.hotel.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "product",key = "#product.id"),
                      @CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "productCounts",key = "#KeyList")})
    @Override
    public int insertProduct(Product product, String KeyList) {
        System.out.println("启用Service...insertProduct");
        return productMapper.insertSelective(product);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "product",key = "#product.id"),
                      @CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "productCounts",key = "#KeyList")})
    @Override
    public int updateProduct(Product product, String KeyList) {
        System.out.println("启用Service...updateProduct");
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "product",key = "#id"),
                      @CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "productCounts",key = "#KeyList")})
    @Override
    public int deleteProductById(Integer id,String KeyList) {
        System.out.println("启用Service...deleteProductById");
        return productMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "product",key = "#id")
    @Override
    public Product selectProductById(Integer id) {
        System.out.println("启用Service...selectProductById");
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectProductListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectProductListByLimit");
        return productMapper.selectProductByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return productMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "product",key = "#product.id"),
                      @CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "productCounts",key = "#KeyList")})
    @Override
    public int updateProductByStates(Product product, String KeyList) {
        System.out.println("启用Service...updateProductByStates");
        return productMapper.updateProductByStates(product);
    }

    @Override
    public List<Product> searchProduct(String productName) {
        System.out.println("启用Service...searchProduct");
        return productMapper.searchProduct(productName);
    }

    @Override
    public List<Product> selectProductListByProductPopularitySortDesc(Map<String, Object> map) {
        System.out.println("启用Service...selectProductListByProductPopularitySortDesc");
        return productMapper.selectProductListByProductPopularitySortDesc(map);
    }

    @Override
    public List<Product> selectProductListByProductType(Map<String, Object> map) {
        System.out.println("启用Service...selectProductListByProductType");
        return productMapper.selectProductListByProductType(map);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "productCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list,Integer states,String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return productMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "productCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return productMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "productCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
