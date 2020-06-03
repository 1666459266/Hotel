package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Restaurant;
import com.hotel.core.service.RestaurantService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectRestaurantController")
@RequestMapping("/select")
public class RestaurantController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id查询菜单
    @RequestMapping("/getRestaurantById")
    public JsonResult getRestaurantById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRestaurantById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Restaurant restaurant = restaurantService.selectRestaurantById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,restaurant);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的菜单列表
    @RequestMapping("/getRestaurantList")
    public JsonResult getRestaurantList(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRestaurantList");
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
            map.put("foodStates",1);
            String msg = "success";
            int states = 1;
            List<Restaurant> restaurantList = restaurantService.selectRestaurantListByLimit(map, KEYLIST);
            int counts = restaurantService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,restaurantList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收状态的菜单列表
    @RequestMapping("/getRecycleRestaurantList")
    public JsonResult getRecycleRestaurantList(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleRestaurantList");
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
            map.put("foodStates",2);
            String msg = "success";
            int states = 2;
            List<Restaurant> restaurantList = restaurantService.selectRestaurantListByLimit(map, RECYCLEKEYLIST);
            int counts = restaurantService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,restaurantList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索菜单（模糊查询）
    @RequestMapping("/searchRestaurant")
    public JsonResult searchRestaurant(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchRestaurant");
        try {
            String restaurantIntro = ToolUtil.str("restaurantIntro",request);
            if (ToolUtil.equalBool(restaurantIntro) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Restaurant> restaurantList = restaurantService.searchRestaurant(restaurantIntro);
            jsonResult = JsonResult.build(FLAG_SUCCESS,restaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据商品热度降序排序
    @RequestMapping("/getRestaurantListByFoodPopularitySortDesc")
    public JsonResult getRestaurantListByFoodPopularitySortDesc(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRestaurantListByFoodPopularitySortDesc");
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
            map.put("foodStates",1);
            List<Restaurant> restaurantList = restaurantService.selectRestaurantListByFoodPopularitySortDesc(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,restaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据菜单分类筛选
    @RequestMapping("/getRestaurantListByRestaurantType")
    public JsonResult getRestaurantListByRestaurantType(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRestaurantListByRestaurantType");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            String restaurantType = ToolUtil.str("restaurantType",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)&&ToolUtil.equalBool(restaurantType)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("restaurantType",restaurantType);
            map.put("foodStates",1);
            List<Restaurant> restaurantList = restaurantService.selectRestaurantListByRestaurantType(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,restaurantList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
