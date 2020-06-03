package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.UserFaceInfo;
import com.hotel.core.service.UserFaceInfoService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.PageVO;
import com.hotel.core.utils.ToolUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("UserFaceListController")
@RequestMapping("/select")
public class UserFaceController extends BaseController {

    @Autowired
    private UserFaceInfoService userFaceInfoService;

    //根据id获取
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/getUserFaceInfoById")
    public JsonResult getUserFaceInfoById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserFaceInfoById");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            UserFaceInfo userFaceInfo = userFaceInfoService.selectUserFaceInfoById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userFaceInfo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取人脸信息列表
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/getUserFaceInfoList")
    public JsonResult getUserRoomList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserFaceInfoList");
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
            List<UserFaceInfo> userFaceInfoList = userFaceInfoService.selectUserFaceInfoListByLimit(map, KEYLIST);
            int counts = userFaceInfoService.selectCounts();
            jsonResult = JsonResult.build(FLAG_SUCCESS,userFaceInfoList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
