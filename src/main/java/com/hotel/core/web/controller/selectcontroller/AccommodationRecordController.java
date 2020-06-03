package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.AccommodationRecord;
import com.hotel.core.service.AccommodationRecordService;
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

@RestController("selectAccommodationRecordController")
@RequestMapping("/select")
public class AccommodationRecordController extends BaseController {

    @Autowired
    private AccommodationRecordService accommodationRecordService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取信息记录
    @RequestMapping("/getAccommodationRecordById")
    public JsonResult getPermissionById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            AccommodationRecord accommodationRecord = accommodationRecordService.selectAccommodationRecordById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,accommodationRecord);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据用户名获取记录
    @RequestMapping("/getAccommodationRecordListByUsername")
    public JsonResult getPermissionListByUsername(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordListByUsername");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            String username = ToolUtil.str("username",request);
            if (ToolUtil.equalBool(username) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"用户名不能为空");
                return jsonResult;
            }
            List<AccommodationRecord> accommodationRecordList = accommodationRecordService.selectAccommodationRecordByUsername(username);
            jsonResult = JsonResult.build(FLAG_SUCCESS,accommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取所有记录
    @RequestMapping("/getAccommodationRecordList")
    public JsonResult getPermissionList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordList");
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
            String msg = "success";
            //执行查询并传入缓存的key
            List<AccommodationRecord> accommodationRecordList = accommodationRecordService.selectAccommodationRecordListByLimit(map, KEYLIST);
            int counts = accommodationRecordService.selectCounts(KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,accommodationRecordList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据入住时间降序排序
    @RequestMapping("/getAccommodationRecordListByCheckinDateSortDesc")
    public JsonResult getAccommodationRecordListByCheckinDateSortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoomListByCheckinDateSortDesc");
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
            List<AccommodationRecord> accommodationRecordList = accommodationRecordService.selectAccommodationRecordListByCheckinDateSortDesc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,accommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据入住时间时间升序排序
    @RequestMapping("/getAccommodationRecordListByCheckinDateSortAsc")
    public JsonResult getAccommodationRecordListByCheckinDateSortAsc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordListByCheckinDateSortAsc");
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
            List<AccommodationRecord> AccommodationRecordList = accommodationRecordService.selectAccommodationRecordListByCheckinDateSortAsc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,AccommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据离开时间降序排序
    @RequestMapping("/getAccommodationRecordListByDepartureDateSortDesc")
    public JsonResult getAccommodationRecordListByDepartureDateSortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordListByDepartureDateSortDesc");
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
            List<AccommodationRecord> AccommodationRecordList = accommodationRecordService.selectAccommodationRecordListByDepartureDateSortDesc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,AccommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }
    //根据离开时间升序排序
    @RequestMapping("/getAccommodationRecordListByDepartureDateSortAsc")
    public JsonResult getAccommodationRecordListByDepartureDateSortAsc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordListByDepartureDateSortAsc");
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
            List<AccommodationRecord> AccommodationRecordList = accommodationRecordService.selectAccommodationRecordListByDepartureDateSortAsc(map, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,AccommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户的所有住房记录
    @RequestMapping("/getAccommodationRecordListByUserId")
    public JsonResult getAccommodationRecordListByUserId(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordListByUserId");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            if (ToolUtil.equalBool(userId) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<AccommodationRecord> accommodationRecordList = accommodationRecordService.selectAccommodationRecordListByUserId(userId);
            jsonResult = JsonResult.build(FLAG_SUCCESS,accommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询某个用户任意时间段的住房记录
    @RequestMapping("/getAccommodationRecordListByUserIdAndTime")
    public JsonResult getAccommodationRecordListByUserIdAndTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordListByUserIdAndTime");
        try {
            Integer userId = ToolUtil.integer("userId",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(userId)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<AccommodationRecord> accommodationRecordList = accommodationRecordService.selectAccommodationRecordListByUserIdAndTime(userId, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,accommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //查询任意时间段的所有住房记录
    @RequestMapping("/getAccommodationRecordListByTime")
    public JsonResult getAccommodationRecordListByTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordListByTime");
        try {
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<AccommodationRecord> accommodationRecordList = accommodationRecordService.selectAccommodationRecordListByTime(start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,accommodationRecordList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以天分组）
    @RequestMapping("/getAccommodationRecordCountsByDay")
    public JsonResult getAccommodationRecordCountsByDay(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordCountsByDay");
        try {
            String roomType = ToolUtil.str("roomType",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(roomType)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> counts = accommodationRecordService.selectCountsByDay(roomType, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以月分组）
    @RequestMapping("/getAccommodationRecordCountsByMonth")
    public JsonResult getAccommodationRecordCountsByMonth(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordCountsByMonth");
        try {
            String roomType = ToolUtil.str("roomType",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(roomType)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> counts = accommodationRecordService.selectCountsByMonth(roomType, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //统计任意时间段的总数量（以年分组）
    @RequestMapping("/getAccommodationRecordCountsByYear")
    public JsonResult getAccommodationRecordCountsByYear(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getAccommodationRecordCountsByYear");
        try {
            String roomType = ToolUtil.str("roomType",request);
            Date start = ToolUtil.date2("start",request);
            Date end = ToolUtil.date2("end",request);
            if ((ToolUtil.equalBool(roomType)&&ToolUtil.equalBool(start)&&ToolUtil.equalBool(end)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Map<Object,Object>> counts = accommodationRecordService.selectCountsByYear(roomType, start, end);
            jsonResult = JsonResult.build(FLAG_SUCCESS,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
