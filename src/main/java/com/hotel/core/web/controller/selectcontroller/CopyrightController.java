package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Copyright;
import com.hotel.core.service.CopyrightService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectCopyrightController")
@RequestMapping("/select")
public class CopyrightController extends BaseController {

    @Autowired
    private CopyrightService copyrightService;
    @Autowired
    private RedisUtils redisUtils;
    //根据id查询信息
    @RequestMapping("/getCopyrightById")
    public JsonResult getCopyrightById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getCopyrightById");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Copyright copyright = copyrightService.selectCopyrightById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,copyright);
        }catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }
    //获取正常状态信息列表
    @RequestMapping("/getCopyrightList")
    public JsonResult getCopyrightList(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getCopyrightList");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
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
            map.put("existStates",1);
            String msg = "success";
            int states = 1;
            List<Copyright> copyrightList = copyrightService.selectCopyrightListByLimit(map,KEYLIST);
            int counts = copyrightService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,copyrightList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }
    //获取回收状态的菜单列表
    @RequestMapping("/getRecycleCopyrightList")
    public JsonResult getRecycleCopyrightList(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleCopyrightList");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
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
            map.put("existStates",2);
            String msg = "success";
            int states = 2;
            List<Copyright> copyrightList = copyrightService.selectCopyrightListByLimit(map,RECYCLEKEYLIST);
            int counts = copyrightService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,copyrightList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
