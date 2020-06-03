package com.hotel.core.service;

import com.hotel.core.entity.Expense;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExpenseService {

    //添加消费记录
    public int insertExpense(Expense expense, String KeyList);

    //修改消费记录
    public int updateExpense(Expense expense, String KeyList);

    //根据id删除消费记录
    public int deleteExpenseById(Integer id, String KeyList);

    //根据id查询消费记录
    public Expense selectExpenseById(Integer id);

    //获取消费记录分页列表
    public List<Expense> selectExpenseListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts();

    //搜索消费记录
    public List<Expense> searchExpense(String username);

    //根据消费时间降序排序
    public List<Expense> selectExpenseListByConsumptionDateSortDesc(Map<String, Object> map, String KeyList);

    //根据消费时间升序排序
    public List<Expense> selectExpenseListByConsumptionDateSortAsc(Map<String, Object> map, String KeyList);

    //查询某个用户的所有消费记录
    public List<Expense> selectExpenseListByUserId(Integer userId);

    //查询某个用户任意时间段的消费记录
    public List<Expense> selectExpenseListByUserIdAndTime(Integer userId, Date start, Date end);

    //查询任意时间段的所有消费记录
    public List<Expense> selectExpenseListByTime(Date start, Date end);

    //统计某个物品任意时间段的消费数量（以天分组）
    public List<Map<Object,Object>> selectExpenseQuantityByDay(String productName, Date start, Date end);

    //统计某个物品任意时间段的消费数量（以月分组）
    public List<Map<Object,Object>> selectExpenseQuantityByMonth(String productName, Date start, Date end);

    //统计某个物品任意时间段的消费数量（以年分组）
    public List<Map<Object,Object>> selectExpenseQuantityByYear(String productName, Date start, Date end);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
