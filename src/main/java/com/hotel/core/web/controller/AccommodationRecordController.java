package com.hotel.core.web.controller;

import com.hotel.core.entity.AccommodationRecord;
import com.hotel.core.service.AccommodationRecordService;
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
import java.util.List;

@RestController
@RequestMapping("accommodationRecord")
public class AccommodationRecordController extends BaseController {

    @Autowired
    private AccommodationRecordService accommodationRecordService;
    @Autowired
    private RedisUtils redisUtils;

    //修改用户记录
    @RequiresRoles(value = {"user"})
    @RequestMapping("/updateAccommodationRecord")
    public JsonResult updateAccommodationRecord(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateAccommodationRecord");
        redisUtils.setDataBase(2);
        System.out.println("redis切换到2库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String totalPrice = ToolUtil.str("totalPrice",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if (ToolUtil.equalBool(totalPrice) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            AccommodationRecord accommodationRecord = accommodationRecordService.selectAccommodationRecordById(id);
            if (accommodationRecord == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"没有该用户记录");
                return jsonResult;
            }
            accommodationRecord.setTotalPrice(totalPrice);
            int result = accommodationRecordService.updateAccommodationRecord(accommodationRecord, KEYLIST);
            if (result > 0){
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED,"修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //删除记录
    @RequiresRoles(value = {"admin"},logical = Logical.AND)
    @RequestMapping("/deleteAccommodationRecord")
    public JsonResult deleteProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteAccommodationRecord");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            AccommodationRecord accommodationRecord = accommodationRecordService.selectAccommodationRecordById(id);
            if (accommodationRecord == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"没有记录");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = accommodationRecordService.deleteAccommodationRecordById(id,RECYCLEKEYLIST);
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
    @RequestMapping("/deleteAccommodationRecordBatch")
    public JsonResult deleteUserRoomBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteAccommodationRecordBatch");
        try {
            int result = accommodationRecordService.deleteBatch(ids,KEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("accommodationRecord:" + id);
            }
            if (result > 0){
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
