package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Expense;
import com.hotel.core.entity.Product;
import com.hotel.core.entity.UserProduct;
import com.hotel.core.entity.Users;
import com.hotel.core.mapper.ExpenseMapper;
import com.hotel.core.mapper.UserProductMapper;
import com.hotel.core.service.ProductService;
import com.hotel.core.service.UserProductService;
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
public class UserProductServiceImpl implements UserProductService {

    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserProductMapper userProductMapper;
    @Autowired
    private Expense expense;
    @Autowired
    private ExpenseMapper expenseMapper;

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userProduct",key = "#userProduct.id"),
                      @CacheEvict(cacheNames = "product",key = "#userProduct.productId"),
                      @CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByUserId",key = "#userProduct.userId")})
    @Override
    public int insertUserProduct(UserProduct userProduct, String KeyList) {
        System.out.println("启用Service...insertUserProduct");
        return userProductMapper.insertSelective(userProduct);
    }

    @Override
    public int updateProductByUserProduct(UserProduct userProduct, String KeyList) {
        Product product = productService.selectProductById(userProduct.getProductId());
        //将商品数量恢复到下单以前
        product.setProductNum(product.getProductNum()+userProduct.getProductNum());
        return productService.updateProduct(product, BaseController.KEYLIST);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userProduct",key = "#userProduct.id"),
                      @CacheEvict(cacheNames = "product",key = "#userProduct.productId"),
                      @CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByUserId",key = "#userProduct.userId")})
    @Override
    public int updateUserProduct(UserProduct userProduct, String KeyList) {
        System.out.println("启用Service...updateUserProduct");
        int i = 0;
        int j = 0;
        try {
            //根据传入的用户userId和productId查询用户和商品
            Users users = usersService.selectUsersById(userProduct.getUserId());
            Product product = productService.selectProductById(userProduct.getProductId());
            if (product.getProductNum() == 0){
                return 0;
            }
            //减去用户购买的商品数量
            product.setProductNum(product.getProductNum()-userProduct.getProductNum());
            i = productService.updateProduct(product, BaseController.KEYLIST);
            //存入用户商品订单
            userProduct.setUserId(users.getId());
            userProduct.setProductId(product.getId());
            userProduct.setUsername(users.getUsername());
            userProduct.setProductName(product.getProductName());
            userProduct.setProductPrice(product.getProductUnitPrice());
            j = userProductMapper.updateByPrimaryKeySelective(userProduct);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userProduct",key = "#id"),
                      @CacheEvict(cacheNames = "product",key = "#productId"),
                      @CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByUserId",key = "#userId")})
    @Override
    public int deleteUserProductById(Integer id,Integer userId,Integer productId,Integer productNum,String KeyList) {
        System.out.println("启用Service...deleteUserProductById");
        int i = 0;
        int j = 0;
        try {
            Product product = productService.selectProductById(productId);
            //将商品数量恢复到下单以前
            product.setProductNum(product.getProductNum()+productNum);
            i = productService.updateProduct(product, BaseController.KEYLIST);
            j = userProductMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Cacheable(cacheNames = "userProduct",key = "#id")
    @Override
    public UserProduct selectUserProductById(Integer id) {
        System.out.println("启用Service...selectUserProductById");
        return userProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserProduct> selectUserProductListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserProductListByLimit");
        return userProductMapper.selectUserProductListByLimit(map);
    }

    @Override
    public int selectCounts(String KeyList) {
        System.out.println("启用Service...selectCounts");
        return userProductMapper.selectCounts();
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userProduct",key = "#userProduct.id"),
                      @CacheEvict(cacheNames = "userProductList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByUserId",key = "#userProduct.userId")})
    @Override
    public int cancel(UserProduct userProduct, String KeyList) {
        System.out.println("启用Service...cancel");
        int i = 0;
        int j = 0;
        try {
            Users users = usersService.selectUsersById(userProduct.getUserId());
            //交易完成后增加用户积分 提升会员等级
            Integer MembershipScore = Integer.parseInt(users.getMembershipScore());
            Integer ProductPrice = Integer.parseInt(userProduct.getProductPrice());
            String MembershipScoreX = Integer.toString(MembershipScore+ProductPrice);
            users.setMembershipScore(MembershipScoreX);
            Integer score = MembershipScore + ProductPrice;
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
            expense.setUserId(userProduct.getUserId());
            expense.setUsername(userProduct.getUsername());
            expense.setOrderNumber(userProduct.getOrderNumber());
            expense.setProductName(userProduct.getProductName());
            expense.setQuantity(userProduct.getProductNum());
            expense.setConsumptionDate(new Date());
            expenseMapper.insert(expense);
            //更新会员等级和用户积分
            i = usersService.updateUser(users, BaseController.KEYLIST);
            //将订单设置为过期
            j = userProductMapper.cancel(userProduct);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Override
    public List<UserProduct> searchUserProduct(String orderNumber, String username) {
        System.out.println("启用Service...searchUserProduct");
        return userProductMapper.searchUserProduct(orderNumber,username);
    }

    @Override
    public List<UserProduct> selectPaidUserProduct(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserProduct");
        return userProductMapper.selectPaidUserProduct(map);
    }

    @Override
    public List<UserProduct> selectUnPaidUserProduct(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserProduct");
        return userProductMapper.selectUnPaidUserProduct(map);
    }

    @Override
    public List<UserProduct> selectUserProductListByOrderTimeSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserProduct");
        return userProductMapper.selectUserProductListByOrderTimeSortDesc(map);
    }

    @Override
    public List<UserProduct> selectUserProductListByOrderTimeSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...searchUserProduct");
        return userProductMapper.selectUserProductListByOrderTimeSortAsc(map);
    }

    @Cacheable(cacheNames = "userProductListByUserId",key = "#userId")
    @Override
    public List<UserProduct> selectUserProductListByUserId(Integer userId) {
        System.out.println("启用Service...selectUserProductListByUserId");
        return userProductMapper.selectUserProductListByUserId(userId);
    }

    @Override
    public List<UserProduct> selectUserProductListByUserIdAndTime(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectUserProductListByUserIdAndTime");
        return userProductMapper.selectUserProductListByUserIdAndTime(userId,start,end);
    }

    @Override
    public List<UserProduct> selectUserProductListByTime(Date start, Date end) {
        System.out.println("启用Service...selectUserProductListByTime");
        return userProductMapper.selectUserProductListByTime(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectUserPriceByDay(Integer userId,Date start, Date end) {
        System.out.println("启用Service...selectUserPriceByDay");
        return userProductMapper.selectUserPriceByDay(userId,start,end);
    }

    @Override
    public List<Map<Object, Object>> selectUserPriceByMonth(Integer userId,Date start, Date end) {
        System.out.println("启用Service...selectUserPriceByMonth");
        return userProductMapper.selectUserPriceByMonth(userId,start,end);
    }

    @Override
    public List<Map<Object, Object>> selectUserPriceByYear(Integer userId,Date start, Date end) {
        System.out.println("启用Service...selectUserPriceByYear");
        return userProductMapper.selectUserPriceByYear(userId,start,end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByDay(Date start, Date end) {
        System.out.println("启用Service...selectCountsByDay");
        return userProductMapper.selectCountsByDay(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectPriceByDay(Date start, Date end) {
        System.out.println("启用Service...selectPriceByDay");
        return userProductMapper.selectPriceByDay(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByMonth(Date start, Date end) {
        System.out.println("启用Service...selectCountsByMonth");
        return userProductMapper.selectCountsByMonth(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectPriceByMonth(Date start, Date end) {
        System.out.println("启用Service...selectPriceByMonth");
        return userProductMapper.selectPriceByMonth(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectCountsByYear(Date start, Date end) {
        System.out.println("启用Service...selectCountsByYear");
        return userProductMapper.selectCountsByYear(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectPriceByYear(Date start, Date end) {
        System.out.println("启用Service...selectPriceByYear");
        return userProductMapper.selectPriceByYear(start,end);
    }

    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return userProductMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "productList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "paidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "unPaidUserProduct",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userProductListByOrderTimeSortAsc",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
