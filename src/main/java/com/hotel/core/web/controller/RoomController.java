package com.hotel.core.web.controller;

import com.hotel.core.entity.Room;
import com.hotel.core.entity.Users;
import com.hotel.core.service.RoomService;
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
@RequestMapping("/room")
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加房间
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertRoom")
    public JsonResult insertRoom(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                 HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            String roomFloor = ToolUtil.str("roomFloor",request);
            Integer roomNumber = ToolUtil.integer("roomNumber",request);
            String roomIntro = ToolUtil.str("roomIntro",request);
            String roomType = ToolUtil.str("roomType",request);
            String roomMax = ToolUtil.str("roomMax",request);
            String roomArea = ToolUtil.str("roomArea",request);
            String roomBedType = ToolUtil.str("roomBedType",request);
            String roomPrice = ToolUtil.str("roomPrice",request);
            if((ToolUtil.equalBool(roomFloor)&&ToolUtil.equalBool(roomNumber)&&ToolUtil.equalBool(roomType)&&ToolUtil.equalBool(roomPrice)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Room room = new Room();
            room.setRoomFloor(roomFloor);
            room.setRoomNumber(roomNumber);
            if (multipartFile != null){
                //上传图片
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String roomPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + roomPicture);
                if (roomPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                room.setRoomPicture(roomPicture);
            }
            room.setRoomIntro(roomIntro);
            room.setRoomType(roomType);
            room.setRoomMax(roomMax);
            room.setRoomArea(roomArea);
            room.setRoomBedType(roomBedType);
            room.setRoomPrice(roomPrice);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            room.setCreaterId(users.getId());
            room.setCreaterUsername(users.getUsername());
            room.setCreateTime(new Date());
            room.setRoomStates(1);
            room.setExistStates(1);
            //执行添加房间并清除正常缓存列表
            int result = roomService.insertRoom(room, KEYLIST);
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

    //修改房间
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateRoom")
    public JsonResult updateRoom(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                 HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String roomFloor = ToolUtil.str("roomFloor",request);
            Integer roomNumber = ToolUtil.integer("roomNumber",request);
            String roomIntro = ToolUtil.str("roomIntro",request);
            String roomType = ToolUtil.str("roomType",request);
            String roomMax = ToolUtil.str("roomMax",request);
            String roomArea = ToolUtil.str("roomArea",request);
            String roomBedType = ToolUtil.str("roomBedType",request);
            String roomPrice = ToolUtil.str("roomPrice",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if((ToolUtil.equalBool(roomFloor)&&ToolUtil.equalBool(roomNumber)&&ToolUtil.equalBool(roomType)&&ToolUtil.equalBool(roomPrice)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Room room = roomService.selectRoomById(id);
            if (room == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该房间不存在");
                return jsonResult;
            }
            room.setRoomFloor(roomFloor);
            room.setRoomNumber(roomNumber);
            if (multipartFile != null){
                //上传图片
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String roomPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + roomPicture);
                if (roomPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                room.setRoomPicture(roomPicture);
            }
            room.setRoomIntro(roomIntro);
            room.setRoomType(roomType);
            room.setRoomMax(roomMax);
            room.setRoomArea(roomArea);
            room.setRoomBedType(roomBedType);
            room.setRoomPrice(roomPrice);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            room.setModifyId(users.getId());
            room.setModifyUsername(users.getUsername());
            room.setModifyTime(new Date());
            //执行修改房间并清除正常缓存列表
            int result = roomService.updateRoom(room,KEYLIST);
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

    //回收房间
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRoom")
    public JsonResult recycleRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Room room = roomService.selectRoomById(id);
            if (room == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该房间不存在");
                return jsonResult;
            }
            room.setExistStates(2);
            //执行回收并清除回收列表的缓存
            int result = roomService.updateRoomByStates(room, RECYCLEKEYLIST);
            //清空正常列表的缓存
            roomService.clearCache(KEYLIST);
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

    //将回收的房间恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleRoom")
    public JsonResult recoveryRecycleRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Room room = roomService.selectRoomById(id);
            if (room == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该房间不存在");
                return jsonResult;
            }
            room.setExistStates(1);
            //执行恢复并清除回收列表的缓存
            int result = roomService.updateRoomByStates(room, RECYCLEKEYLIST);
            //清空正常列表的缓存
            roomService.clearCache(KEYLIST);
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

    //删除房间
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRoom")
    public JsonResult deleteRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Room room = roomService.selectRoomById(id);
            if (room == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该房间不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = roomService.deleteRoomById(id,RECYCLEKEYLIST);
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

    //批量回收房间
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRoomBatch")
    public JsonResult recycleRoomBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRoom");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //回收房间并清除回收列表的缓存
            int result = roomService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("room::" + id);
            }
            //清空正常列表的缓存
            roomService.clearCache(KEYLIST);
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

    //将回收的房间批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleRoomBatch")
    public JsonResult recoveryRecycleRoomBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleRoomBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //恢复房间并清除回收列表的缓存
            int result = roomService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("room::" + id);
            }
            //清空正常列表的缓存
            roomService.clearCache(KEYLIST);
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

    //批量删除房间
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRoomBatch")
    public JsonResult deleteRoomBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRoomBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //删除房间并清除回收列表的缓存
            int result = roomService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("room::" + id);
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
