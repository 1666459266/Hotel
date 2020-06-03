package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Room;
import com.hotel.core.service.RoomService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectRoomController")
@RequestMapping("/select")
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id查询房间信息
    @RequestMapping("/getRoomById")
    public JsonResult getRoomById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoomById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Room room = roomService.selectRoomById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,room);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的房间列表
    @RequestMapping("/getRoomList")
    public JsonResult getRoomList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoomList");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("existStates",1);
            String msg = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Room> roomList = roomService.selectRoomListByLimit(map, KEYLIST);
            int counts = roomService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roomList,msg,states);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的房间列表
    @RequestMapping("/getRecycleRoomList")
    public JsonResult getRecycleRoomList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleRoomList");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("existStates",2);
            String msg = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Room> roomList = roomService.selectRoomListByLimit(map, RECYCLEKEYLIST);
            int counts = roomService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roomList,msg,states);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索房间
    @RequestMapping("/searchRoom")
    public JsonResult searchRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchRoom");
        try {
            Integer roomNumber = ToolUtil.integer("roomNumber",request);
            if (ToolUtil.equalBool(roomNumber) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Room> roomList = roomService.searchRoom(roomNumber);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据楼层筛选
    @RequestMapping("/getRoomListByRoomFloor")
    public JsonResult getRoomListByRoomFloor(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoomListByRoomFloor");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            String roomFloor = ToolUtil.str("roomFloor",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)&&ToolUtil.equalBool(roomFloor)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("roomFloor",roomFloor);
            map.put("existStates",1);
            List<Room> roomList = roomService.selectRoomListByRoomFloor(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据房间类型筛选
    @RequestMapping("/getRoomListByRoomType")
    public JsonResult getRoomListByRoomType(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoomListByRoomType");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            String roomType = ToolUtil.str("roomType",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)&&ToolUtil.equalBool(roomType)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("roomType",roomType);
            map.put("existStates",1);
            List<Room> roomList = roomService.selectRoomListByRoomType(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据床型筛选
    @RequestMapping("/getRoomListByRoomBedType")
    public JsonResult getRoomListByRoomBedType(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoomListByRoomBedType");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            String roomBedType = ToolUtil.str("roomBedType",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)&&ToolUtil.equalBool(roomBedType)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("roomBedType",roomBedType);
            map.put("existStates",1);
            List<Room> roomList = roomService.selectRoomListByRoomBedType(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
