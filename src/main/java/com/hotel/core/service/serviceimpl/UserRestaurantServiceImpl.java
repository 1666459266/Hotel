package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Expense;
import com.hotel.core.entity.Restaurant;
import com.hotel.core.entity.UserRestaurant;
import com.hotel.core.entity.Users;
import com.hotel.core.mapper.ExpenseMapper;
import com.hotel.core.mapper.UserRestaurantMapper;
import com.hotel.core.service.RestaurantService;
import com.hotel.core.service.UserRestaurantService;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserRestaurantServiceImpl implements UserRestaurantService {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserRestaurantMapper userRestaurantMapper;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ExpenseMapper expenseMapper;
    @Autowired
    private Expense expense;

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userRestaurant",key = "#userRestaurant.id"),
            @CacheEvict(cacheNames = "restaurant",key = "#userRestaurant.restaurantId"),
            @CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
            @CacheEvict(cacheNames = "userRestaurantList",key = "#KeyList"),
            @CacheEvict(cacheNames = "userRestaurantCounts",key = "#KeyList")})
    @Override
    public int insertUserRestaurant(UserRestaurant userRestaurant, String KeyList) {
        System.out.println("启用Service...insertUserRestaurant");
        return userRestaurantMapper.insertSelective(userRestaurant);
    }


    @Override
    public int updateRestaurantByUserRestaurant(UserRestaurant userRestaurant, String KeyList) {
        Restaurant restaurant = restaurantService.selectRestaurantById(userRestaurant.getRestaurantId());
        restaurant.setFoodNum(restaurant.getFoodNum()+userRestaurant.getFoodNum());
        return userRestaurantMapper.updateByPrimaryKeySelective(userRestaurant);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRestaurant",key = "#userRestaurant.id"),
            @CacheEvict(cacheNames = "restaurant",key = "#userRestaurant.restaurantId"),
            @CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
            @CacheEvict(cacheNames = "userRestaurantList",key = "#KeyList"),
            @CacheEvict(cacheNames = "userRestaurantCounts",key = "#KeyList"),
            @CacheEvict(cacheNames = "paidUserRestaurant",key = "#KeyList"),
            @CacheEvict(cacheNames = "unPaidUserRestaurant",key = "#KeyList"),
            @CacheEvict(cacheNames = "userRestaurantListByOrderTimeSortDesc",key = "#KeyList"),
            @CacheEvict(cacheNames = "userRestaurantListByOrderTimeSortAsc",key = "#KeyList"),
            @CacheEvict(cacheNames = "userRestaurantListByUserId",key = "#userRestaurant.userId")})
    @Override
    public int updateUserRestaurant(UserRestaurant userRestaurant, String KeyList) {
        System.out.println("启用Service...updateUserRestaurant");
        int i = 0;
        int j = 0;
        int k = 0;
        try {
            //根据传入的用户userId和productId查询用户和商品
            Users users = usersService.selectUsersById(userRestaurant.getUserId());
            Restaurant restaurant = restaurantService.selectRestaurantById(userRestaurant.getRestaurantId());
            Expense expense = new Expense();
            if (restaurant.getFoodNum() == 0){
                return 0;
            }
            //减去用户购买的商品数量
            restaurant.setFoodNum(restaurant.getFoodNum()-userRestaurant.getFoodNum());
            i = restaurantService.updateRestaurant(restaurant,BaseController.KEYLIST);
            //存入用户商品订单
            userRestaurant.setUserId(users.getId());
            userRestaurant.setRestaurantId(restaurant.getId());
            userRestaurant.setUsername(users.getUsername());
            userRestaurant.setFoodName(restaurant.getFoodName());
            userRestaurant.setFoodUnitPrice(restaurant.getFoodUnitPrice());
            j = userRestaurantMapper.updateByPrimaryKeySelective(userRestaurant);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j+k;
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userRestaurant",key = "#id"),
                      @CacheEvict(cacheNames = "restaurant",key = "#restaurantId"),
                      @CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserRestaurant",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserRestaurant",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantListByUserId",key = "#userId")})
    @Override
    public int deleteUserRestaurantById(Integer id, Integer userId,Integer restaurantId,Integer foodNum, String KeyList) {
        System.out.println("启用Service...deleteUserRestaurantById");
        int i = 0;
        int j = 0;
        try {
            Restaurant restaurant = restaurantService.selectRestaurantById(id);
            //将商品数量恢复到下单以前
            restaurant.setFoodNum(restaurant.getFoodNum()+foodNum);
            i = restaurantService.updateRestaurant(restaurant,BaseController.KEYLIST);
            j = userRestaurantMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Cacheable(cacheNames = "userRestaurant",key = "#id")
    @Override
    public UserRestaurant selectUserRestaurantById(Integer id) {
        System.out.println("启用Service...selectUserRestaurantById");
        return userRestaurantMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserRestaurant> selectUserRestaurantListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserRestaurantListByLimit");
        return userRestaurantMapper.selectUserRestaurantListByLimit(map);
    }

    @Override
    public int selectCounts(String KeyList) {
        System.out.println("启用Service...selectCounts");
        return userRestaurantMapper.selectCounts();
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRestaurant",key = "#userRestaurant.id"),
                      @CacheEvict(cacheNames = "userRestaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserRestaurant",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserRestaurant",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantListByOrderTimeSortAsc",key = "#KeyList")})
    @Override
    public int cancel(UserRestaurant userRestaurant, String KeyList) {
        int i = 0;
        int j = 0;
        try {
            Users users = usersService.selectUsersById(userRestaurant.getUserId());
            //交易完成后增加用户积分 提升会员等级
            Integer MembershipScore = Integer.parseInt(users.getMembershipScore());
            Integer FoodUnitPrice = Integer.parseInt(userRestaurant.getFoodUnitPrice());
            String MembershipScoreX = Integer.toString(MembershipScore+FoodUnitPrice);
            users.setMembershipScore(MembershipScoreX);
            Integer score = MembershipScore + FoodUnitPrice;
            if (score >= 0 && score < 600){
                users.setMembershipLevel(1);
            }else if (score >= 600 && score < 1800){
                users.setMembershipLevel(2);
            }else if (score >= 1800 && score < 3600){
                users.setMembershipLevel(3);
            }else if (score >= 3600 && score < 6000){
                users.setMembershipLevel(4);
            }else if (score >= 6000 && score < 10800){
                users.setMembershipLevel(5);
            }else if (score >= 10800 && score < 32400){
                users.setMembershipLevel(6);
            }else if (score >= 32400 && score < 46800){
                users.setMembershipLevel(7);
            }else {
                users.setMembershipLevel(8);
            }

            //更新会员等级和用户积分
            i = usersService.updateUser(users, BaseController.KEYLIST);
            //将订单设置为过期
            j = userRestaurantMapper.cancel(userRestaurant);

            expense.setUserId(userRestaurant.getUserId());
            expense.setUsername(userRestaurant.getUsername());
            expense.setOrderNumber(userRestaurant.getOrderNumber());
            expense.setProductName(userRestaurant.getFoodName());
            expense.setQuantity(userRestaurant.getFoodNum());
            expense.setConsumptionDate(new Date());
            expenseMapper.insert(expense);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Override
    public List<UserRestaurant> searchUserRestaurant(String orderNumber, String username) {
        System.out.println("启用Service...searchUserRestaurant");
        return userRestaurantMapper.searchUserRestaurant(orderNumber,username);
    }

    @Override
    public List<UserRestaurant> selectPaidUserRestaurant(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectPaidUserRestaurant");
        return userRestaurantMapper.selectPaidUserRestaurant(map);
    }

    @Override
    public List<UserRestaurant> selectUnPaidUserRestaurant(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUnPaidUserRestaurant");
        return userRestaurantMapper.selectUnPaidUserRestaurant(map);
    }

    @Override
    public List<UserRestaurant> selectUserRestaurantListByOrderTimeSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserRestaurantListByOrderTimeSortDesc");
        return userRestaurantMapper.selectUserRestaurantListByOrderTimeSortDesc(map);
    }

    @Override
    public List<UserRestaurant> selectUserRestaurantListByOrderTimeSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserRestaurantListByOrderTimeSortAsc");
        return userRestaurantMapper.selectUserRestaurantListByOrderTimeSortAsc(map);
    }

    @Cacheable(cacheNames = "userRestaurantListByUserId",key = "#userId")
    @Override
    public List<UserRestaurant> selectUserRestaurantListByUserId(Integer userId) {
        System.out.println("启用Service...selectUserRestaurantListByUserId");
        return userRestaurantMapper.selectUserRestaurantListByUserId(userId);
    }

    @Override
    public List<UserRestaurant> selectUserRestaurantListByUserIdAndTime(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectUserRestaurantListByUserIdAndTime");
        return userRestaurantMapper.selectUserRestaurantListByUserIdAndTime(userId,start,end);
    }

    @Override
    public List<UserRestaurant> selectUserRestaurantListByTime(Date start, Date end) {
        System.out.println("启用Service...selectUserRestaurantListByTime");
        return userRestaurantMapper.selectUserRestaurantListByTime(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectUserPriceByDay(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectUserPriceByDay");
        return userRestaurantMapper.selectUserPriceByDay(userId,start,end);
    }

    @Override
    public List<Map<Object, Object>> selectUserPriceByMonth(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectUserPriceByMonth");
        return userRestaurantMapper.selectUserPriceByMonth(userId,start,end);
    }

    @Override
    public List<Map<Object, Object>> selectUserPriceByYear(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectUserPriceByYear");
        return userRestaurantMapper.selectUserPriceByYear(userId,start,end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByDay(Date start, Date end) {
        System.out.println("启用Service...selectCountsByDay");
        return userRestaurantMapper.selectCountsByDay(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectPriceByDay(Date start, Date end) {
        System.out.println("启用Service...selectPriceByDay");
        return userRestaurantMapper.selectPriceByDay(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByMonth(Date start, Date end) {
        System.out.println("启用Service...selectCountsByMonth");
        return userRestaurantMapper.selectCountsByMonth(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectPriceByMonth(Date start, Date end) {
        System.out.println("启用Service...selectPriceByMonth");
        return userRestaurantMapper.selectPriceByMonth(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByYear(Date start, Date end) {
        System.out.println("启用Service...selectCountsByYear");
        return userRestaurantMapper.selectCountsByYear(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectPriceByYear(Date start, Date end) {
        System.out.println("启用Service...selectPriceByYear");
        return userRestaurantMapper.selectPriceByYear(start,end);
    }

    @Override
    public int deleteBatch(List list, String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return userRestaurantMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "restaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRestaurantCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

    @Override
    public List<UserRestaurant> selectAll() {
        System.out.println("启用Service...selectAll");
        return userRestaurantMapper.selectAll();
    }

}
