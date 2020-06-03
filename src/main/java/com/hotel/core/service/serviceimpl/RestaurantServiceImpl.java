package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Restaurant;
import com.hotel.core.mapper.RestaurantMapper;
import com.hotel.core.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "restaurant",key = "#restaurant.id"),
                      @CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "restaurantCounts",key = "#KeyList")})
    @Override
    public int insertRestaurant(Restaurant restaurant, String KeyList) {
        System.out.println("启用Service...insertRestaurant");
        return restaurantMapper.insertSelective(restaurant);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "restaurant",key = "#restaurant.id"),
                      @CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "restaurantCounts",key = "#KeyList")})
    @Override
    public int updateRestaurant(Restaurant restaurant, String KeyList) {
        System.out.println("启用Service...updateRestaurant");
        return restaurantMapper.updateByPrimaryKeySelective(restaurant);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "restaurant",key = "#id"),
                      @CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "restaurantCounts",key = "#KeyList")})
    @Override
    public int deleteRestaurantById(Integer id,String KeyList) {
        System.out.println("启用Service...deleteRestaurantById");
        return restaurantMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "restaurant",key = "#id")
    @Override
    public Restaurant selectRestaurantById(Integer id) {
        System.out.println("启用Service...selectRestaurantById");
        return restaurantMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Restaurant> selectRestaurantListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectRestaurantListByLimit");
        return restaurantMapper.selectRestaurantByLimit(map);
    }

    @Override
    public int selectCounts(Integer states, String KeyList) {
        System.out.println("启用Service...selectCounts");
        return restaurantMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "restaurant",key = "#restaurant.id"),
                      @CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "restaurantCounts",key = "#KeyList")})
    @Override
    public int updateRestaurantByStates(Restaurant restaurant, String KeyList) {
        System.out.println("启用Service...updateRestaurantByStates");
        return restaurantMapper.updateRestaurantByStates(restaurant);
    }

    @Override
    public List<Restaurant> searchRestaurant(String restaurantIntro) {
        System.out.println("启用Service...searchRestaurant");
        return restaurantMapper.searchRestaurant(restaurantIntro);
    }

    @Override
    public List<Restaurant> selectRestaurantListByFoodPopularitySortDesc(Map<String, Object> map) {
        System.out.println("启用Service...selectRestaurantListByFoodPopularitySortDesc");
        return restaurantMapper.selectRestaurantListByFoodPopularitySortDesc(map);
    }

    @Override
    public List<Restaurant> selectRestaurantListByRestaurantType(Map<String, Object> map) {
        System.out.println("启用Service...selectRestaurantListByRestaurantType");
        return restaurantMapper.selectRestaurantListByRestaurantType(map);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "restaurantCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list,Integer states,String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return restaurantMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "restaurantCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return restaurantMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "restaurantCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
