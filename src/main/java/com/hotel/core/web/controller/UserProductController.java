package com.hotel.core.web.controller;

import com.hotel.core.entity.UserProduct;
import com.hotel.core.mq.MQSend;
import com.hotel.core.service.UserProductService;
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
@RequestMapping("/userProduct")
public class UserProductController extends BaseController {

    @Autowired
    private UserProductService userProductService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MQSend mqSend;

    //添加用户商品订单
    @RequiresRoles(value = "user")
    @RequestMapping("/insertUserProduct")
    public JsonResult insertUserProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertUserProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Integer productId = ToolUtil.integer("productId",request);
            Integer productNum = ToolUtil.integer("productNum",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(productId)&&ToolUtil.equalBool(productNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserProduct userProduct = new UserProduct();
            userProduct.setUserId(userId);
            userProduct.setProductId(productId);
            userProduct.setProductNum(productNum);
            userProduct.setOrderTime(new Date());
            userProduct.setOrderOverdueStates(1);
            userProduct.setOrderStates(2);
            //入队
            mqSend.sendProduct(userProduct);
            jsonResult = JsonResult.build(FLAG_SUCCESS,"订单已生成");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //修改用户商品订单
    @RequiresRoles(value = "user")
    @RequestMapping("/updateUserProduct")
    public JsonResult updateUserProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateUserProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            Integer userId = ToolUtil.integer("userId",request);
            Integer productIdBefore = ToolUtil.integer("productIdBefore",request);
            Integer productNumBefore = ToolUtil.integer("productNumBefore",request);
            Integer productId = ToolUtil.integer("productId",request);
            Integer productNum = ToolUtil.integer("productNum",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(productIdBefore)&&ToolUtil.equalBool(productNumBefore)&&ToolUtil.equalBool(productId)&&ToolUtil.equalBool(productNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserProduct userProduct = userProductService.selectUserProductById(id);
            if (userProduct == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户商品订单不存在");
                return jsonResult;
            }
            //将下单的商品数量恢复到下单以前
            userProduct.setProductNum(productNumBefore);
            userProductService.updateProductByUserProduct(userProduct,KEYLIST);
            //再传入更新后的userId productId productNUm
            userProduct.setUserId(userId);
            userProduct.setProductId(productId);
            userProduct.setProductNum(productNum);
            //执行修改并清除缓存
            int result = userProductService.updateUserProduct(userProduct, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED,"该商品暂时无货");
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
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            UserProduct userProduct = userProductService.selectUserProductById(id);
            if (userProduct == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户商品订单不存在");
                return jsonResult;
            }
            userProduct.setOrderOverdueStates(2);
            //交易完成后清除缓存
            int result = userProductService.cancel(userProduct, KEYLIST);
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

    //取消用户商品订单
    @RequiresRoles(value = {"user"})
    @RequestMapping("/deleteUserProduct")
    public JsonResult deleteUserProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            Integer productId = ToolUtil.integer("productId",request);
            Integer productNum = ToolUtil.integer("productNum",request);
            if ((ToolUtil.equalBool(id)&&ToolUtil.equalBool(productId)&&ToolUtil.equalBool(productNum)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserProduct userProduct = userProductService.selectUserProductById(id);
            if (userProduct == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户商品订单不存在");
                return jsonResult;
            }
            //执行取消订单并清除缓存
            int result = userProductService.deleteUserProductById(id,userProduct.getUserId(),productId,productNum,KEYLIST);
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
    @RequestMapping("/deleteUserProductBatch")
    public JsonResult deleteUserProductBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserProductBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //删除用户商品订单并清除缓存
            int result = userProductService.deleteBatch(ids,KEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("userProduct::" + id);
                UserProduct userProduct = userProductService.selectUserProductById((Integer) id);
                redisUtils.delete("userProductListByUserId::" + userProduct.getUserId());
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
