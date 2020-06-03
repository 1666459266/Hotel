package com.hotel.core.web.controller;

import com.hotel.core.entity.Coupon;
import com.hotel.core.entity.Users;
import com.hotel.core.service.CouponService;
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
@RequestMapping("coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;


    //添加优惠券
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertCoupon")
    public JsonResult insertCoupon(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                    HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            String couponDetails = ToolUtil.str("couponDetails",request);
            String couponPrice = ToolUtil.str("couponPrice",request);
            String usableRange = ToolUtil.str("usableRange",request);
            String serviceConditions = ToolUtil.str("serviceConditions",request);
            Date expirationDate = ToolUtil.date2("expirationDate",request);
            if ((ToolUtil.equalBool(couponPrice)&&ToolUtil.equalBool(usableRange)&&ToolUtil.equalBool(serviceConditions)&&ToolUtil.equalBool(expirationDate) == false)){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Coupon coupon = new Coupon();
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String couponPicture = fileUploadTool.createFile(multipartFile,request);
                if (couponPicture != null){
                    msg = "success";
                } else {
                    msg = "fail";
                }
                coupon.setCouponPicture(couponPicture);
            }
            coupon.setCouponPrice(couponPrice);
            coupon.setCouponDetails(couponDetails);
            coupon.setUsableRange(usableRange);
            coupon.setServiceConditions(serviceConditions);
            coupon.setGetDate(new Date());
            coupon.setExpirationDate(expirationDate);
            coupon.setCouponStates(1);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            coupon.setUserId(users.getId());
            coupon.setUsername(users.getUsername());
            coupon.setUsageStates(1);
            //执行添加并清除正常列表的缓存
            int result = couponService.insertCoupon(coupon, KEYLIST);
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
    //修改优惠券
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateCoupon")
    public JsonResult updateCoupon(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                       HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateCoupon");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String couponDetails = ToolUtil.str("couponDetails",request);
            String couponPrice = ToolUtil.str("couponPrice",request);
            String usableRange = ToolUtil.str("usableRange",request);
            String serviceConditions = ToolUtil.str("serviceConditions",request);
            Date expirationDate = ToolUtil.date2("expirationDate",request);
            Integer usageStates = ToolUtil.integer("usageStates",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(couponPrice)&&ToolUtil.equalBool(usableRange)&&ToolUtil.equalBool(serviceConditions)&&ToolUtil.equalBool(expirationDate) == false)){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Coupon coupon = couponService.selectCouponById(id);
            if (coupon == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该优惠券不存在");
                return jsonResult;
            }
            coupon.setCouponDetails(couponDetails);
            coupon.setCouponPrice(couponPrice);
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String couponPicture = fileUploadTool.createFile(multipartFile,request);
                if (couponPicture != null){
                    msg = "success";
                } else {
                    msg = "fail";
                }
                coupon.setCouponPicture(couponPicture);
            }
            coupon.setUsableRange(usableRange);
            coupon.setServiceConditions(serviceConditions);
            coupon.setExpirationDate(expirationDate);
            coupon.setUsageStates(usageStates);
            //修改菜单并清除正常列表缓存
            int result = couponService.updateCoupon(coupon, KEYLIST);
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

    //回收优惠券
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleCoupon")
    public JsonResult recycleCoupon(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleCoupon");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Coupon coupon = couponService.selectCouponById(id);
            if (coupon == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该优惠券不存在");
                return jsonResult;
            }
            coupon.setCouponStates(2);
            //先清除正常列表缓存
            couponService.updateCouponByStates(coupon,KEYLIST);
            //执行回收并清除回收列表缓存
            int result = couponService.updateCouponByStates(coupon, RECYCLEKEYLIST);
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

    //将回收的优惠券恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleCoupon")
    public JsonResult recoveryRecycleCoupon(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleCoupon");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Coupon coupon = couponService.selectCouponById(id);
            if (coupon == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该优惠券不存在");
                return jsonResult;
            }
            coupon.setCouponStates(1);
            //先清除正常列表缓存
            couponService.updateCouponByStates(coupon,KEYLIST);
            //执行恢复并清除回收列表缓存
            int result = couponService.updateCouponByStates(coupon, RECYCLEKEYLIST);
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

    //删除优惠券
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteCoupon")
    public JsonResult deleteCoupon(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteCoupon");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Coupon coupon = couponService.selectCouponById(id);
            if (coupon == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该优惠券不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表缓存
            int result = couponService.deleteCouponById(id,RECYCLEKEYLIST);
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

    //批量回收优惠券
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleCouponBatch")
    public JsonResult recycleCouponBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleCouponBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //回收优惠券并清除回收列表缓存
            int result = couponService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("coupon:" + id);
            }
            //清除正常列表的缓存
            couponService.clearCache(KEYLIST);
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

    //将回收的优惠券批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleCouponBatch")
    public JsonResult recoveryRecycleCouponBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleCouponBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //恢复优惠券并清除回收列表缓存
            int result = couponService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("coupon:" + id);
            }
            //清除正常列表的缓存
            couponService.clearCache(KEYLIST);
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

    //批量删除优惠券
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteCouponBatch")
    public JsonResult deleteCouponBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteCouponBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //删除优惠券并清除回收列表缓存
            int result = couponService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("coupon:" + id);
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
