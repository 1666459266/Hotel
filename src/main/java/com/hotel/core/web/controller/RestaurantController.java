package com.hotel.core.web.controller;

import com.hotel.core.entity.Restaurant;
import com.hotel.core.entity.Users;
import com.hotel.core.service.RestaurantService;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
import com.hotel.core.utils.uploadfile.FileUploadTool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加菜单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertRestaurant")
    public JsonResult insertRestaurant(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                 HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            String restaurantType = ToolUtil.str("restaurantType",request);
            String restaurantIntro = ToolUtil.str("restaurantIntro",request);
            String foodName = ToolUtil.str("foodName",request);
            String foodUnitPrice = ToolUtil.str("foodUnitPrice",request);
            Integer foodInstock = ToolUtil.integer("foodInstock",request);
            if ((ToolUtil.equalBool(restaurantType)&&ToolUtil.equalBool(foodName)&&ToolUtil.equalBool(foodUnitPrice)&&ToolUtil.equalBool(foodInstock)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantType(restaurantType);
            if (multipartFile != null){
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String restaurantPicture = fileUploadTool.createFile(multipartFile,request);
                if (restaurantPicture != null){
                    msg = "success";
                } else {
                    msg = "fail";
                }
                restaurant.setRestaurantPicture(restaurantPicture);
            }
            restaurant.setRestaurantIntro(restaurantIntro);
            restaurant.setFoodName(foodName);
            restaurant.setFoodUnitPrice(foodUnitPrice);
            restaurant.setRegisterDate(new Date());
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            restaurant.setCreaterId(users.getId());
            restaurant.setCreaterUsername(users.getUsername());
            restaurant.setCreateTime(new Date());
            restaurant.setFoodInstock(foodInstock);
            restaurant.setFoodStates(1);
            //执行添加并清楚正常列表缓存
            int result = restaurantService.insertRestaurant(restaurant, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //修改菜单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateRestaurant")
    public JsonResult updateRestaurant(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                      HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String restaurantType = ToolUtil.str("restaurantType",request);
            String restaurantIntro = ToolUtil.str("restaurantIntro",request);
            String foodName = ToolUtil.str("foodName",request);
            String foodUnitPrice = ToolUtil.str("foodUnitPrice",request);
            Integer foodInstock = ToolUtil.integer("foodInstock",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(restaurantType)&&ToolUtil.equalBool(foodName)&&ToolUtil.equalBool(foodUnitPrice)&&ToolUtil.equalBool(foodInstock)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Restaurant restaurant = restaurantService.selectRestaurantById(id);
            if (restaurant == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该菜单不存在");
                return jsonResult;
            }
            restaurant.setRestaurantType(restaurantType);
            if (multipartFile != null){
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String restaurantPicture = fileUploadTool.createFile(multipartFile,request);
                if (restaurantPicture != null){
                    msg = "success";
                } else {
                    msg = "fail";
                }
                restaurant.setRestaurantPicture(restaurantPicture);
            }
            restaurant.setRestaurantIntro(restaurantIntro);
            restaurant.setFoodName(foodName);
            restaurant.setFoodUnitPrice(foodUnitPrice);
            restaurant.setRegisterDate(new Date());
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            restaurant.setModifierId(users.getId());
            restaurant.setModifierUsername(users.getUsername());
            restaurant.setModifyTime(new Date());
            restaurant.setFoodInstock(foodInstock);
            //修改菜单并清除正常列表缓存
            int result = restaurantService.updateRestaurant(restaurant, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //回收菜单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRestaurant")
    public JsonResult recycleRestaurant(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Restaurant restaurant = restaurantService.selectRestaurantById(id);
            if (restaurant == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该菜单不存在");
                return jsonResult;
            }
            restaurant.setFoodStates(2);
            //执行回收并清除回收列表缓存
            int result = restaurantService.updateRestaurantByStates(restaurant, RECYCLEKEYLIST);
            //清除正常列表缓存
            restaurantService.clearCache(KEYLIST);
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

    //将回收的菜单恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleRestaurant")
    public JsonResult recoveryRecycleRestaurant(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Restaurant restaurant = restaurantService.selectRestaurantById(id);
            if (restaurant == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该菜单不存在");
                return jsonResult;
            }
            restaurant.setFoodStates(1);
            //执行恢复并清除回收列表缓存
            int result = restaurantService.updateRestaurantByStates(restaurant, RECYCLEKEYLIST);
            //清除正常列表缓存
            restaurantService.clearCache(KEYLIST);
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

    //删除菜单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRestaurant")
    public JsonResult deleteRestaurant(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRestaurant");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Restaurant restaurant = restaurantService.selectRestaurantById(id);
            if (restaurant == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该菜单不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表缓存
            int result = restaurantService.deleteRestaurantById(id,RECYCLEKEYLIST);
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

    //批量回收菜单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRestaurantBatch")
    public JsonResult recycleRestaurantBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRestaurantBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //回收菜单并清除回收列表缓存
            int result = restaurantService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("restaurant::" + id);
            }
            //清除正常列表缓存
            restaurantService.clearCache(KEYLIST);
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

    //将回收的菜单批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleRestaurantBatch")
    public JsonResult recoveryRecycleRestaurantBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleRestaurantBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //恢复菜单并清除回收列表缓存
            int result = restaurantService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("restaurant::" + id);
            }
            //清除正常列表缓存
            restaurantService.clearCache(KEYLIST);
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

    //批量删除菜单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRestaurantBatch")
    public JsonResult deleteRestaurantBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRestaurantBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //删除菜单并清除回收列表缓存
            int result = restaurantService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("restaurant::" + id);
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
