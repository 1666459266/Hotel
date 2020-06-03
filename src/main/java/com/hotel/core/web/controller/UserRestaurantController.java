package com.hotel.core.web.controller;

import com.hotel.core.entity.Expense;
import com.hotel.core.entity.UserRestaurant;
import com.hotel.core.mq.MQSend;
import com.hotel.core.service.UserRestaurantService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
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
@RequestMapping("userRestaurant")
public class UserRestaurantController extends BaseController {

    @Autowired
    private UserRestaurantService userRestaurantService;
    @Autowired
    private Expense expense;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MQSend mqSend;

    //添加用户用餐订单
    @RequiresRoles(value = "user")
    @RequestMapping("/insertUserRestaurant")
    public JsonResult insertUserRestaurant(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertUserRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Integer restaurantId = ToolUtil.integer("restaurantId",request);
            Integer foodNum = ToolUtil.integer("foodNum",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(restaurantId)&&ToolUtil.equalBool(foodNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRestaurant userRestaurant = new UserRestaurant();
            userRestaurant.setUserId(userId);
            userRestaurant.setRestaurantId(restaurantId);
            userRestaurant.setFoodNum(foodNum);
            userRestaurant.setOrderTime(new Date());
            userRestaurant.setOrderOverdueStates(1);
            userRestaurant.setOrderStates(2);
            //入队
            mqSend.sendRestaurant(userRestaurant);
            jsonResult = JsonResult.build(FLAG_SUCCESS,"订单已生成");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }
    //修改用户商品订单
    @RequiresRoles(value = "user")
    @RequestMapping("/updateUserRestaurant")
    public JsonResult updateUserRestaurant(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateUserRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            Integer userId = ToolUtil.integer("userId",request);
            Integer restaurantIdBefore = ToolUtil.integer("restaurantIdBefore",request);
            Integer foodNumBefore = ToolUtil.integer("foodNumBefore",request);
            Integer restaurantId = ToolUtil.integer("restaurantId",request);
            Integer foodNum = ToolUtil.integer("foodNum",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(restaurantIdBefore)&&ToolUtil.equalBool(foodNumBefore)&&ToolUtil.equalBool(restaurantId)&&ToolUtil.equalBool(foodNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRestaurant userRestaurant = userRestaurantService.selectUserRestaurantById(id);
            if (userRestaurant == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户食品订单不存在");
                return jsonResult;
            }
            //将下单的食品数量恢复到下单以前
            userRestaurant.setFoodNum(foodNumBefore);
            userRestaurantService.updateRestaurantByUserRestaurant(userRestaurant,KEYLIST);
            //再传入更新后的userId restaurantId foodNum
            userRestaurant.setUserId(userId);
            userRestaurant.setRestaurantId(restaurantId);
            userRestaurant.setFoodNum(foodNum);
            int result = userRestaurantService.updateUserRestaurant(userRestaurant, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED,"该食品暂时无货");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //交易完成
    @RequiresRoles(value = {"user"})
    @RequestMapping("/cancel")
    public JsonResult cancel(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...cancel");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String consumptionType = ToolUtil.str("consumptionType",request);
            String tradingManner = ToolUtil.str("tradingManner",request);
            String transactionAmount = ToolUtil.str("transactionAmount",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(consumptionType)&&ToolUtil.equalBool(tradingManner)&&ToolUtil.equalBool(transactionAmount)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            expense.setConsumptionType(consumptionType);
            expense.setTradingManner(tradingManner);
            expense.setTransactionAmount(transactionAmount);
            UserRestaurant userRestaurant = userRestaurantService.selectUserRestaurantById(id);
            if (userRestaurant == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户食品订单不存在");
                return jsonResult;
            }
            userRestaurant.setOrderOverdueStates(2);
            int result = userRestaurantService.cancel(userRestaurant, KEYLIST);
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

    //取消用户用餐订单
    @RequiresRoles(value = {"user"})
    @RequestMapping("/deleteUserRestaurant")
    public JsonResult deleteUserRestaurant(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            Integer restaurantId = ToolUtil.integer("restaurantId",request);
            Integer foodNum = ToolUtil.integer("foodNum",request);
            if ((ToolUtil.equalBool(id)&&ToolUtil.equalBool(restaurantId)&&ToolUtil.equalBool(foodNum)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRestaurant userRestaurant = userRestaurantService.selectUserRestaurantById(id);
            if (userRestaurant == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户用餐订单不存在");
                return jsonResult;
            }
            int result = userRestaurantService.deleteUserRestaurantById(id,userRestaurant.getUserId(),restaurantId,foodNum,KEYLIST);
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

    //批量删除用户商品订单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteUserRestaurantBatch")
    public JsonResult deleteUserRestaurantBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserRestaurantBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            int result = userRestaurantService.deleteBatch(ids,KEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("userRestaurant:" + id);
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
