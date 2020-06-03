package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.*;
import com.hotel.core.mapper.*;
import com.hotel.core.service.*;
import com.hotel.core.utils.BaseController;
import org.apache.catalina.User;
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
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseMapper expenseMapper;

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "expense",key = "#expense.id"),
                      @CacheEvict(cacheNames = "expenseList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByConsumptionDateSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByConsumptionDateSortAsc",key = "#KeyList")})
    @Override
    public int insertExpense(Expense expense, String KeyList) {
        System.out.println("启用Service...insertExpense");
        return expenseMapper.insertSelective(expense);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "expense",key = "#expense.id"),
                      @CacheEvict(cacheNames = "expenseList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByConsumptionDateSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByConsumptionDateSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByUserId",key = "#expense.userId")})
    @Override
    public int updateExpense(Expense expense, String KeyList) {
        System.out.println("启用Service...updateExpense");
        return expenseMapper.updateByPrimaryKeySelective(expense);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "expense",key = "#id"),
                      @CacheEvict(cacheNames = "expenseList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByConsumptionDateSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByConsumptionDateSortAsc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "expenseListByUserId",key = "#id")})
    @Override
    public int deleteExpenseById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteExpenseById");
        return expenseMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "expense",key="#id")
    @Override
    public Expense selectExpenseById(Integer id) {
        System.out.println("启用Service...selectExpenseById");
        return expenseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Expense> selectExpenseListByLimit(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectExpenseListByLimit");
        return expenseMapper.selectExpenseByLimit(map);
    }

    @Override
    public int selectCounts() {
        System.out.println("启用Service...selectCounts");
        return expenseMapper.selectCounts();
    }

    @Override
    public List<Expense> searchExpense(String username) {
        System.out.println("启用Service...searchExpense");
        return expenseMapper.searchExpense(username);
    }

    @Override
    public List<Expense> selectExpenseListByConsumptionDateSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectExpenseListByConsumptionDateSortDesc");
        return expenseMapper.selectExpenseListByConsumptionDateSortDesc(map);
    }

    @Override
    public List<Expense> selectExpenseListByConsumptionDateSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectExpenseListByConsumptionDateSortAsc");
        return expenseMapper.selectExpenseListByConsumptionDateSortAsc(map);
    }

    @Cacheable(cacheNames = "expenseListByUserId",key = "#userId")
    @Override
    public List<Expense> selectExpenseListByUserId(Integer userId) {
        System.out.println("启用Service...selectAccommodationRecordListByUserId");
        return expenseMapper.selectExpenseListByUserId(userId);
    }

    @Override
    public List<Expense> selectExpenseListByUserIdAndTime(Integer userId, Date start, Date end) {
        System.out.println("启用Service...selectExpenseListByUserIdAndTime");
        return expenseMapper.selectExpenseListByUserIdAndTime(userId,start,end);
    }

    @Override
    public List<Expense> selectExpenseListByTime(Date start, Date end) {
        System.out.println("启用Service...selectExpenseListByTime");
        return expenseMapper.selectExpenseListByTime(start,end);
    }

    @Override
    public List<Map<Object, Object>> selectExpenseQuantityByDay(String productName, Date start, Date end) {
        System.out.println("启用Service...selectExpenseQuantityByDay");
        return expenseMapper.selectExpenseQuantityByDay(productName, start, end);
    }

    @Override
    public List<Map<Object, Object>> selectExpenseQuantityByMonth(String productName, Date start, Date end) {
        System.out.println("启用Service...selectExpenseQuantityByMonth");
        return expenseMapper.selectExpenseQuantityByMonth(productName, start, end);
    }

    @Override
    public List<Map<Object, Object>> selectExpenseQuantityByYear(String productName, Date start, Date end) {
        System.out.println("启用Service...selectExpenseQuantityByYear");
        return expenseMapper.selectExpenseQuantityByYear(productName, start, end);
    }

    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return expenseMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "expenseList",key = "#KeyList"),
            @CacheEvict(cacheNames = "expenseCounts",key = "#KeyList"),
            @CacheEvict(cacheNames = "expenseListByConsumptionDateSortDesc",key = "#KeyList"),
            @CacheEvict(cacheNames = "expenseListByConsumptionDateSortAsc",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
