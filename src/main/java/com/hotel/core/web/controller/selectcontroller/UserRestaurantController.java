package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.UserRestaurant;
import com.hotel.core.service.UserRestaurantService;
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

@RestController("selectUserRestaurantController")
@RequestMapping("/select")
public class UserRestaurantController extends BaseController {

    @Autowired
    private UserRestaurantService userRestaurantService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取用户食品订单
    @RequestMapping("/getUserRestaurantById")
    public JsonResult getUserRestaurantById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantById");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            UserRestaurant userRestaurant = userRestaurantService.selectUserRestaurantById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurant);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取用户商品订单列表
    @RequestMapping("/getUserRestaurantList")
    public JsonResult getUserRestaurantList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantList");
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
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectUserRestaurantListByLimit(map, KEYLIST);
            int counts = userRestaurantService.selectCounts(KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索订单（模糊查询）
    @RequestMapping("/searchUserRestaurant")
    public JsonResult searchUserRestaurant(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchUserRestaurant");
        try {
            String orderNumber = ToolUtil.str("orderNumber",request);
            String username = ToolUtil.str("username",request);
            if ((ToolUtil.equalBool(orderNumber)||ToolUtil.equalBool(username)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"条件不能为空");
                return jsonResult;
            }
            List<UserRestaurant> userRestaurantList = userRestaurantService.searchUserRestaurant(orderNumber, username);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询已付款的订单
    @RequestMapping("/getPaidUserRestaurant")
    public JsonResult getPaidUserRestaurant(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getPaidUserRestaurant");
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
            map.put("orderStates",1);
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectPaidUserRestaurant(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询未付款的订单
    @RequestMapping("/getUnPaidUserRestaurant")
    public JsonResult getUnPaidUserRestaurant(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUnPaidUserRestaurant");
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
            map.put("orderStates",2);
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectUnPaidUserRestaurant(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据下单时间降序排序
    @RequestMapping("/getUserRestaurantListByOrderTimeSortDesc")
    public JsonResult getUserRestaurantListByOrderTimeSortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantListByOrderTimeSortDesc");
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
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectUserRestaurantListByOrderTimeSortDesc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据下单时间升序排序
    @RequestMapping("/getUserRestaurantListByOrderTimeSortAsc")
    public JsonResult getUserRestaurantListByOrderTimeSortAsc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantListByOrderTimeSortAsc");
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
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectUserRestaurantListByOrderTimeSortAsc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户的所有订单
    @RequestMapping("/getUserRestaurantListByUserId")
    public JsonResult getUserRestaurantListByUserId(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantListByUserId");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            if (ToolUtil.equalBool(userId) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectUserRestaurantListByUserId(userId);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户任意时间段的订单
    @RequestMapping("/getUserRestaurantListByUserIdAndTime")
    public JsonResult getUserRestaurantListByUserIdAndTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantListByUserIdAndTime");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectUserRestaurantListByUserIdAndTime(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询任意时间段的所有订单
    @RequestMapping("/getUserRestaurantListByTime")
    public JsonResult getUserRestaurantListByTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantListByTime");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserRestaurant> userRestaurantList = userRestaurantService.selectUserRestaurantListByTime(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRestaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以天分组）
    @RequestMapping("/getUserRestaurantPriceByUserIdDay")
    public JsonResult getUserRestaurantPriceByUserIdDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantPriceByUserIdDay");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userRestaurantService.selectUserPriceByDay(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以月分组）
    @RequestMapping("/getUserRestaurantPriceByUserIdMonth")
    public JsonResult getUserRestaurantPriceByUserIdMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantPriceByUserIdMonth");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userRestaurantService.selectUserPriceByMonth(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以年分组）
    @RequestMapping("/getUserRestaurantPriceByUserIdYear")
    public JsonResult getUserRestaurantPriceByUserIdYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantPriceByUserIdYear");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userRestaurantService.selectUserPriceByYear(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以天分组）
    @RequestMapping("/getUserRestaurantCountsByDay")
    public JsonResult getUserRestaurantCountsByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantCountsByDay");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> counts = userRestaurantService.selectCountsByDay(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以天分组）
    @RequestMapping("/getUserRestaurantPriceByDay")
    public JsonResult getUserRestaurantPriceByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantPriceByDay");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userRestaurantService.selectPriceByDay(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以月分组）
    @RequestMapping("/getUserRestaurantCountsByMonth")
    public JsonResult getUserRestaurantCountsByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantCountsByMonth");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> counts = userRestaurantService.selectCountsByMonth(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以月分组）
    @RequestMapping("/getUserRestaurantPriceByMonth")
    public JsonResult getUserRestaurantPriceByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantPriceByMonth");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userRestaurantService.selectPriceByMonth(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以年分组）
    @RequestMapping("/getUserRestaurantCountsByYear")
    public JsonResult getUserRestaurantCountsByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantCountsByYear");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> counts = userRestaurantService.selectCountsByYear(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以年分组）
    @RequestMapping("/getUserRestaurantPriceByYear")
    public JsonResult getUserRestaurantPriceByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRestaurantPriceByYear");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userRestaurantService.selectPriceByYear(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
