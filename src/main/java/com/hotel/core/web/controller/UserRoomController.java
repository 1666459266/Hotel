package com.hotel.core.web.controller;

import com.hotel.core.entity.UserRoom;
import com.hotel.core.mq.MQSend;
import com.hotel.core.service.UserRoomService;
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
@RequestMapping("/userRoom")
public class UserRoomController extends BaseController {

    @Autowired
    private UserRoomService userRoomService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MQSend mqSend;

    //添加用户房间订单
    @RequiresRoles(value = {"user"})
    @RequestMapping("/insertUserRoom")
    public JsonResult insertUserRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertUserRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Integer roomId = ToolUtil.integer("roomId",request);
            Date orderTime = ToolUtil.date2("orderTime",request);
            Date leaveTime = ToolUtil.date2("leaveTime",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(roomId)&&ToolUtil.equalBool(orderTime)&&ToolUtil.equalBool(leaveTime)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRoom userRoom = new UserRoom();
            userRoom.setUserId(userId);
            userRoom.setRoomId(roomId);
            userRoom.setOrderTime(orderTime);
            userRoom.setLeaveTime(leaveTime);
            userRoom.setOrderOverdueStates(1);
            userRoom.setOrderStates(2);
            //入队
            mqSend.sendRoom(userRoom);
            jsonResult = JsonResult.build(FLAG_SUCCESS,"订单已生成");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //修改用户房间订单
    @RequiresRoles(value = {"user"})
    @RequestMapping("/updateUserRoom")
    public JsonResult updateUserRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertUserRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            Integer userId = ToolUtil.integer("userId",request);
            Integer roomIdBefore = ToolUtil.integer("roomIdBefore",request);
            Integer roomId = ToolUtil.integer("roomId",request);
            Date orderTime = ToolUtil.date2("orderTime",request);
            Date leaveTime = ToolUtil.date2("leaveTime",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(roomId)&&ToolUtil.equalBool(orderTime)&&ToolUtil.equalBool(leaveTime)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRoom userRoom = userRoomService.selectUserRoomById(id);
            if (userRoom == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户房间订单不存在");
                return jsonResult;
            }
            //判断是否修改房间号
            if (ToolUtil.equalBool(roomIdBefore) == false) {
                //未修改房间 只修改入住时间和离店时间
                userRoom.setOrderTime(orderTime);
                userRoom.setLeaveTime(leaveTime);
                //传入修改之前的roomId 用于判断是否需要返回房间被预订
                int result = userRoomService.updateUserRoom(userRoom, KEYLIST,roomIdBefore);
                if (result > 0){
                    jsonResult = JsonResult.build(FLAG_SUCCESS);
                } else {
                    jsonResult = JsonResult.build(FLAG_FAILED,"该房间已被预订");
                }
            }else {
                //已修改房间 修改用户房间订单时修改先修改之前预订的房间
                userRoomService.updateRoomByUserRoom(userRoom,KEYLIST);
                //再传入修改后的roomId
                userRoom.setRoomId(roomId);
                userRoom.setOrderTime(orderTime);
                userRoom.setLeaveTime(leaveTime);
                //传入修改之前的roomId 用于判断是否需要返回房间被预订并执行修改并清除缓存
                int result = userRoomService.updateUserRoom(userRoom, KEYLIST,roomIdBefore);
                if (result > 0){
                    jsonResult = JsonResult.build(FLAG_SUCCESS);
                } else {
                    jsonResult = JsonResult.build(FLAG_FAILED,"该房间已被预订");
                }
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
    public JsonResult cancel(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...cancel");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if ((ToolUtil.equalBool(id)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRoom userRoom = userRoomService.selectUserRoomById(id);
            if (userRoom == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户房间订单不存在");
                return jsonResult;
            }
            userRoom.setOrderOverdueStates(2);
            //交易完成后清除缓存
            int result = userRoomService.cancel(userRoom,KEYLIST);
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

    //取消用户房间订单
    @RequiresRoles(value = {"user"})
    @RequestMapping("/deleteUserRoom")
    public JsonResult deleteUserRoom(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            Integer roomId = ToolUtil.integer("roomId",request);
            if ((ToolUtil.equalBool(id)&&ToolUtil.equalBool(roomId)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRoom userRoom = userRoomService.selectUserRoomById(id);
            if(userRoom == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户房间订单不存在");
                return jsonResult;
            }
            //执行取消订单并清除缓存
            int result = userRoomService.deleteUserRoomById(id,userRoom.getUserId(),roomId, KEYLIST);
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

    //批量删除用户房间订单
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteUserRoomBatch")
    public JsonResult deleteUserRoomBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserRoomBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //删除用户房间订单并清除缓存
            int result = userRoomService.deleteBatch(ids,KEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("userRoom::" + id);
                UserRoom userRoom = userRoomService.selectUserRoomById((Integer) id);
                redisUtils.delete("userRoomListByUserId::" + userRoom.getUserId());
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
