package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Coupon;
import com.hotel.core.mapper.CouponMapper;
import com.hotel.core.service.CouponService;
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

@RestController("selectCouponController")
@RequestMapping("/select")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private RedisUtils redisUtils;
    //根据id查询优惠券
    @RequestMapping("/getCouponById")
    public JsonResult getCouponById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getCouponById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Coupon coupon = couponService.selectCouponById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,coupon);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的优惠券列表
    @RequestMapping("/getCouponList")
    public JsonResult getCouponList(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getCouponList");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            Integer usageStates = ToolUtil.integer("usageStates",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("usageStates",usageStates);
            map.put("couponStates",1);
            String msg = "success";
            int states = 1;
            List<Coupon> couponList = couponService.selectCouponListByLimit(map, KEYLIST);
            int counts = couponService.selectCounts(states, KEYLIST);


            Date day = new Date();
            for(Coupon ax : couponList){
                    Date a = ax.getExpirationDate();
                    Integer id = ax.getId();
                    System.out.println(a);
                if(day.compareTo(a) > 0){
                    Coupon coupon = couponService.selectCouponById(id);
                    coupon.setCouponStates(2);
                    //先清除正常列表缓存
                    couponService.updateCoupon(coupon,KEYLIST);
                    //执行回收并清除回收列表缓存
                    int result = couponService.updateCoupon(coupon, RECYCLEKEYLIST);
                    if (result > 0) {
                        jsonResult = JsonResult.build(FLAG_SUCCESS,couponList,msg,counts);
                    } else {
                        jsonResult = JsonResult.build(FLAG_FAILED);
                    }
                }
            }
            jsonResult = JsonResult.build(FLAG_SUCCESS,couponList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收状态的优惠券列表
    @RequestMapping("/getRecycleCouponList")
    public JsonResult getRecycleCouponList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleCouponList");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            Integer usageStates = ToolUtil.integer("usageStates",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("usageStates",usageStates);
            map.put("couponStates",2);
            String msg = "success";
            int states = 2;
            List<Coupon> couponList = couponService.selectCouponListByLimit(map, RECYCLEKEYLIST);
            int counts = couponService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,couponList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
