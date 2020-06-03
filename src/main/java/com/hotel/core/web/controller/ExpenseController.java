package com.hotel.core.web.controller;

import com.hotel.core.entity.Expense;
import com.hotel.core.entity.Users;
import com.hotel.core.service.*;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("expense")
public class ExpenseController extends BaseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //修改消费记录
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateExpense")
    public JsonResult updateExpense(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateExpense");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String consumptionType = ToolUtil.str("consumptionType",request);
            String productName = ToolUtil.str("productName",request);
            Integer quantity = ToolUtil.integer("quantity",request);
            String tradingManner = ToolUtil.str("tradingManner",request);
            String transactionAmount = ToolUtil.str("transactionAmount",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Expense expense = expenseService.selectExpenseById(id);
            if (expense == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"无消费记录");
                return jsonResult;
            }
            expense.setConsumptionType(consumptionType);
            expense.setProductName(productName);
            expense.setQuantity(quantity);
            expense.setTradingManner(tradingManner);
            expense.setTransactionAmount(transactionAmount);
            expense.setConsumptionDate(new Date());
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            expense.setUserId(users.getId());
            expense.setUsername(users.getUsername());
            //执行修改并清除正常列表的缓存
            int result = expenseService.updateExpense(expense, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //删除消费记录
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteExpense")
    public JsonResult deleteExpense(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteExpense");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Expense expense = expenseService.selectExpenseById(id);
            if (expense == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"没有消费记录");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = expenseService.deleteExpenseById(id,RECYCLEKEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //批量删除用户消费记录
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteExpenseBatch")
    public JsonResult deleteExpenseBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteExpenseBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            int result = expenseService.deleteBatch(ids,KEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("expense:" + id);
            }
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
