package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Opinion;
import com.hotel.core.service.OpinionService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectOpinionController")
@RequestMapping("select")
public class OpinionController extends BaseController {

    @Autowired
    private OpinionService opinionService;
    @Autowired
    private RedisUtils redisUtils;
    //根据id获取意见
    @RequestMapping("/getOpinionById")
    public JsonResult getOpinionById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getOpinionById");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Opinion opinion = opinionService.selectOpinionById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,opinion);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的信息
    @RequestMapping("/getOpinionList")
    public JsonResult getOpinionList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getOpinionList");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
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
            map.put("disposeStates",1);
            String msg = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Opinion> opinionList = opinionService.selectOpinionListByLimit(map, KEYLIST);
            int counts = opinionService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,opinionList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的信息
    @RequestMapping("/getRecycleOpinionList")
    public JsonResult getRecycleOpinionList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleOpinionList");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
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
            map.put("disposeStates",2);
            String msg = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Opinion> opinionList = opinionService.selectOpinionListByLimit(map, RECYCLEKEYLIST);
            int counts = opinionService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,opinionList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
