package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.*;
import com.hotel.core.service.*;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectExpenseController")
@RequestMapping("/select")
public class ExpenseController extends BaseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取消费记录
    @RequestMapping("/getExpenseById")
    public JsonResult getExpenseById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Expense expense = expenseService.selectExpenseById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,expense);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }
    //获取消费记录列表
    @RequestMapping("/getExpenseList")
    public JsonResult getExpenseList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseList");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            String msg = "success";
            //执行查询并存入缓存的key
            List<Expense> expenseList = expenseService.selectExpenseListByLimit(map, KEYLIST);
            int counts = expenseService.selectCounts();
            jsonResult = JsonResult.build(FLAG_SUCCESS,expenseList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索消费记录
    @RequestMapping("/searchExpense")
    public JsonResult searchExpense(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchExpense");
        try {
            String username = ToolUtil.str("username",request);
            if ((ToolUtil.equalBool(username)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"条件不能为空");
                return jsonResult;
            }
            List<Expense> expenseList = expenseService.searchExpense(username);
            jsonResult = JsonResult.build(FLAG_SUCCESS,expenseList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据消费时间降序排序
    @RequestMapping("/getExpenseListByConsumptionDateSortDesc")
    public JsonResult getExpenseListByConsumptionDateSortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseListByConsumptionDateSortDesc");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO();
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            List<Expense> expenseList = expenseService.selectExpenseListByConsumptionDateSortDesc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,expenseList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据消费时间升序排序
    @RequestMapping("/getExpenseListByConsumptionDateSortAsc")
    public JsonResult getExpenseListByConsumptionDateSortAsc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseListByConsumptionDateSortAsc");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO();
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            List<Expense> expenseList = expenseService.selectExpenseListByConsumptionDateSortAsc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,expenseList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户的所有消费记录
    @RequestMapping("/getExpenseListByUserId")
    public JsonResult getExpenseListByUserId(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseListByUserId");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            if (ToolUtil.equalBool(userId) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Expense> expenseList = expenseService.selectExpenseListByUserId(userId);
            jsonResult = JsonResult.build(FLAG_SUCCESS,expenseList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户任意时间段的消费记录
    @RequestMapping("/getExpenseListByUserIdAndTime")
    public JsonResult getExpenseListByUserIdAndTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseListByUserIdAndTime");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Expense> expenseList = expenseService.selectExpenseListByUserIdAndTime(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,expenseList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询任意时间段的所有消费记录
    @RequestMapping("/getExpenseListByTime")
    public JsonResult getExpenseListByTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseListByTime");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Expense> expenseList = expenseService.selectExpenseListByTime(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,expenseList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个某个物品时间段的消费数量（以天分组）
    @RequestMapping("/getExpenseQuantityByDay")
    public JsonResult getExpenseQuantityByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseQuantityByDay");
        try {
            String productName = ToolUtil.str("productName",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(productName)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> quantity = expenseService.selectExpenseQuantityByDay(productName, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,quantity);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个某个物品时间段的消费数量（以月分组）
    @RequestMapping("/getExpenseQuantityByMonth")
    public JsonResult getExpenseQuantityByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseQuantityByMonth");
        try {
            String productName = ToolUtil.str("productName",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(productName)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> quantity = expenseService.selectExpenseQuantityByMonth(productName, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,quantity);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个某个物品时间段的消费数量（以年分组）
    @RequestMapping("/getExpenseQuantityByYear")
    public JsonResult getExpenseQuantityByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getExpenseQuantityByYear");
        try {
            String productName = ToolUtil.str("productName",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(productName)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> quantity = expenseService.selectExpenseQuantityByYear(productName, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,quantity);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
