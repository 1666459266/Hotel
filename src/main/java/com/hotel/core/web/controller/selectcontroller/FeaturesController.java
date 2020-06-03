package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Features;
import com.hotel.core.service.FeaturesService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectFeaturesController")
@RequestMapping("/select")
public class FeaturesController extends BaseController {

    @Autowired
    private FeaturesService featuresService;
    @Autowired
    private RedisUtils redisUtils;
    //根据id获取
    @RequestMapping("/getFeaturesById")
    public JsonResult getFeaturesById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getFeaturesById");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Features features = featuresService.selectFeaturesById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,features);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的商品信息
    @RequestMapping("/getFeaturesList")
    public JsonResult getFeaturesList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getFeaturesList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
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
            List<Features> featuresList = featuresService.selectFeaturesListByLimit(map, KEYLIST);
            int counts = featuresService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,featuresList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的商品信息
    @RequestMapping("/getRecycleFeaturesList")
    public JsonResult getRecycleFeaturesList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleFeaturesList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
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
            List<Features> featuresList = featuresService.selectFeaturesListByLimit(map, RECYCLEKEYLIST);
            int counts = featuresService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,featuresList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
