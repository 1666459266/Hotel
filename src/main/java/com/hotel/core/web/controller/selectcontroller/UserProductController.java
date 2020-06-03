package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.UserProduct;
import com.hotel.core.service.UserProductService;
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

@RestController("selectUserProductController")
@RequestMapping("/select")
public class UserProductController extends BaseController {

    @Autowired
    private UserProductService userProductService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取用户商品订单
    @RequestMapping("/getUserProductById")
    public JsonResult getUserProductById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            UserProduct userProduct = userProductService.selectUserProductById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProduct);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取用户商品订单列表
    @RequestMapping("/getUserProductList")
    public JsonResult getUserProductList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductList");
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
            List<UserProduct> userProductList = userProductService.selectUserProductListByLimit(map, KEYLIST);
            int counts = userProductService.selectCounts(KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索订单（模糊查询）
    @RequestMapping("/searchUserProduct")
    public JsonResult searchUserProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchUserProduct");
        try {
            String orderNumber = ToolUtil.str("orderNumber",request);
            String username = ToolUtil.str("username",request);
            if ((ToolUtil.equalBool(orderNumber)||ToolUtil.equalBool(username)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"条件不能为空");
                return jsonResult;
            }
            List<UserProduct> userProductList = userProductService.searchUserProduct(orderNumber, username);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询已付款的订单
    @RequestMapping("/getPaidUserProduct")
    public JsonResult getPaidUserProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getPaidUserProduct");
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
            map.put("orderStates",1);
            List<UserProduct> userProductList = userProductService.selectPaidUserProduct(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询未付款的订单
    @RequestMapping("/getUnPaidUserProduct")
    public JsonResult getUnPaidUserProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUnPaidUserProduct");
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
            map.put("orderStates",2);
            List<UserProduct> userProductList = userProductService.selectUnPaidUserProduct(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据下单时间降序排序
    @RequestMapping("/getUserProductListByOrderTimeSortDesc")
    public JsonResult getUserProductListByOrderTimeSortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductListByOrderTimeSortDesc");
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
            List<UserProduct> userProductList = userProductService.selectUserProductListByOrderTimeSortDesc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据下单时间升序排序
    @RequestMapping("/getUserProductListByOrderTimeSortAsc")
    public JsonResult getUserProductListByOrderTimeSortAsc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductListByOrderTimeSortAsc");
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
            List<UserProduct> userProductList = userProductService.selectUserProductListByOrderTimeSortAsc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户的所有订单
    @RequestMapping("/getUserProductListByUserId")
    public JsonResult getUserProductListByUserId(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductListByUserId");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            if (ToolUtil.equalBool(userId) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserProduct> userProductList = userProductService.selectUserProductListByUserId(userId);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户任意时间段的订单
    @RequestMapping("/getUserProductListByUserIdAndTime")
    public JsonResult getUserProductListByUserIdAndTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductListByUserIdAndTime");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserProduct> userProductList = userProductService.selectUserProductListByUserIdAndTime(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询任意时间段的所有订单
    @RequestMapping("/getUserProductListByTime")
    public JsonResult getUserProductListByTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductListByTime");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserProduct> userProductList = userProductService.selectUserProductListByTime(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userProductList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以天分组）
    @RequestMapping("/getUserProductPriceByUserIdDay")
    public JsonResult getUserProductPriceByUserIdDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductPriceByUserIdDay");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userProductService.selectUserPriceByDay(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以月分组）
    @RequestMapping("/getUserProductPriceByUserIdMonth")
    public JsonResult getUserProductPriceByUserIdMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductPriceByUserIdMonth");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userProductService.selectUserPriceByMonth(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以年分组）
    @RequestMapping("/getUserProductPriceByUserIdYear")
    public JsonResult getUserProductPriceByUserIdYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductPriceByUserIdYear");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userProductService.selectUserPriceByYear(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以天分组）
    @RequestMapping("/getUserProductCountsByDay")
    public JsonResult getUserProductCountsByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductCountsByDay");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> counts = userProductService.selectCountsByDay(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以天分组）
    @RequestMapping("/getUserProductPriceByDay")
    public JsonResult getUserProductPriceByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductPriceByDay");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userProductService.selectPriceByDay(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以月分组）
    @RequestMapping("/getUserProductCountsByMonth")
    public JsonResult getUserProductCountsByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductCountsByMonth");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> counts = userProductService.selectCountsByMonth(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以月分组）
    @RequestMapping("/getUserProductPriceByMonth")
    public JsonResult getUserProductPriceByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductPriceByMonth");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userProductService.selectPriceByMonth(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以年分组）
    @RequestMapping("/getUserProductCountsByYear")
    public JsonResult getUserProductCountsByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductCountsByYear");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> counts = userProductService.selectCountsByYear(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以年分组）
    @RequestMapping("/getUserProductPriceByYear")
    public JsonResult getUserProductPriceByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserProductPriceByYear");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object, Object>> price = userProductService.selectPriceByYear(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
