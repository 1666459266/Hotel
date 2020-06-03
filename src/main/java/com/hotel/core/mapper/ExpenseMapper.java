package com.hotel.core.mapper;

import com.hotel.core.entity.Expense;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExpenseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Expense expense);

    int insertSelective(Expense expense);

    Expense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Expense expense);

    int updateByPrimaryKey(Expense expense);

    //获取商品分页列表
    List<Expense> selectExpenseByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts();

    //搜索消费记录
    List<Expense> searchExpense(String username);

    //根据消费时间降序排序
    List<Expense> selectExpenseListByConsumptionDateSortDesc(Map<String, Object> map);

    //根据消费时间升序排序
    List<Expense> selectExpenseListByConsumptionDateSortAsc(Map<String, Object> map);

    //查询某个用户的所有消费记录
    List<Expense> selectExpenseListByUserId(Integer userId);

    //查询某个用户任意时间段的消费记录
    List<Expense> selectExpenseListByUserIdAndTime(Integer userId, Date start, Date end);

    //查询任意时间段的所有消费记录
    List<Expense> selectExpenseListByTime(Date start, Date end);

    //统计某个用户任意时间段某个物品的消费数量（以天分组）
    List<Map<Object,Object>> selectExpenseQuantityByDay(String productName, Date start, Date end);

    //统计某个用户任意时间段某个物品的消费数量（以月分组）
    List<Map<Object,Object>> selectExpenseQuantityByMonth(String productName, Date start, Date end);

    //统计某个用户任意时间段某个物品的消费数量（以年分组）
    List<Map<Object,Object>> selectExpenseQuantityByYear(String productName, Date start, Date end);

    //批量删除
    int deleteBatch(List list);

}