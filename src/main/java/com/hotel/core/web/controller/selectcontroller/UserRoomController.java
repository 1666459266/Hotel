package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.UserRoom;
import com.hotel.core.service.UserRoomService;
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

@RestController("selectUserRoomController")
@RequestMapping("/select")
public class UserRoomController extends BaseController {

    @Autowired
    private UserRoomService userRoomService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id查询用户房间订单
    @RequestMapping("/getUserRoomById")
    public JsonResult getUserRoomById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            UserRoom userRoom = userRoomService.selectUserRoomById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoom);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取用户房间订单列表
    @RequestMapping("/getUserRoomList")
    public JsonResult getUserRoomList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomList");
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
            //执行查询并存入缓存的key
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByLimit(map, KEYLIST);
            int counts = userRoomService.selectCounts(KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索订单（模糊查询）
    @RequestMapping("/searchUserRoom")
    public JsonResult searchUserRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchUserRoom");
        try {
            String orderNumber = ToolUtil.str("orderNumber",request);
            String username = ToolUtil.str("username",request);
            if ((ToolUtil.equalBool(orderNumber)||ToolUtil.equalBool(username)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"条件不能为空");
                return jsonResult;
            }
            List<UserRoom> userRoomList = userRoomService.searchUserRoom(orderNumber, username);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询已付款的订单
    @RequestMapping("/getPaidUserRoom")
    public JsonResult getPaidUserRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getPaidUserRoom");
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
            map.put("orderStates",1);
            List<UserRoom> userRoomList = userRoomService.selectPaidUserRoom(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询未付款的订单
    @RequestMapping("/getUnPaidUserRoom")
    public JsonResult getUnPaidUserRoom(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUnPaidUserRoom");
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
            map.put("orderStates",2);
            List<UserRoom> userRoomList = userRoomService.selectUnPaidUserRoom(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据下单时间降序排序
    @RequestMapping("/getUserRoomListByOrderTimeSortDesc")
    public JsonResult getUserRoomListByOrderTimeSortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByOrderTimeSortDesc");
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
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByOrderTimeSortDesc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据下单时间升序排序
    @RequestMapping("/getUserRoomListByOrderTimeSortAsc")
    public JsonResult getUserRoomListByOrderTimeSortAsc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByOrderTimeSortAsc");
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
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByOrderTimeSortAsc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据离店时间降序排序
    @RequestMapping("/getUserRoomListByLeaveTimeSortDesc")
    public JsonResult getUserRoomListByLeaveTimeSortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByLeaveTimeSortDesc");
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
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByLeaveTimeSortDesc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据离店时间升序排序
    @RequestMapping("/getUserRoomListByLeaveTimeSortAsc")
    public JsonResult getUserRoomListByLeaveTimeSortAsc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByLeaveTimeSortAsc");
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
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByLeaveTimeSortAsc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户的所有订单
    @RequestMapping("/getUserRoomListByUserId")
    public JsonResult getUserRoomListByUserId(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByUserId");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            if (ToolUtil.equalBool(userId) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByUserId(userId);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户任意时间段的订单
    @RequestMapping("/getUserRoomListByUserIdAndTime")
    public JsonResult getUserRoomListByUserIdAndTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByUserIdAndTime");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByUserIdAndTime(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询任意时间段的所有订单
    @RequestMapping("/getUserRoomListByTime")
    public JsonResult getUserRoomListByTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByTime");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<UserRoom> userRoomList = userRoomService.selectUserRoomListByTime(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoomList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以天分组）
    @RequestMapping("/getUserRoomPriceByUserIdDay")
    public JsonResult getUserRoomPriceByUserIdDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomPriceByUserIdDay");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectUserPriceByDay(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以月分组）
    @RequestMapping("/getUserRoomPriceByUserIdMonth")
    public JsonResult getUserRoomPriceByUserIdMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomPriceByUserIdMonth");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectUserPriceByMonth(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计某个用户任意时间段的消费（以年分组）
    @RequestMapping("/getUserRoomPriceByUserIdYear")
    public JsonResult getUserRoomPriceByUserIdYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomPriceByUserIdYear");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectUserPriceByYear(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以天分组）
    @RequestMapping("/getUserRoomCountsByDay")
    public JsonResult getUserRoomCountsByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomCountsByDay");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> counts = userRoomService.selectCountsByDay(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以天分组）
    @RequestMapping("/getUserRoomPriceByDay")
    public JsonResult getUserRoomPriceByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomPriceByDay");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectPriceByDay(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以月分组）
    @RequestMapping("/getUserRoomCountsByMonth")
    public JsonResult getUserRoomCountsByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomCountsByMonth");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectCountsByMonth(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以月分组）
    @RequestMapping("/getUserRoomPriceByMonth")
    public JsonResult getUserRoomPriceByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomPriceByMonth");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectPriceByMonth(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以年分组）
    @RequestMapping("/getUserRoomCountsByYear")
    public JsonResult getUserRoomCountsByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomCountsByYear");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectCountsByYear(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总金额（以年分组）
    @RequestMapping("/getUserRoomPriceByYear")
    public JsonResult getUserRoomPriceByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomPriceByYear");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> price = userRoomService.selectPriceByYear(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,price);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
