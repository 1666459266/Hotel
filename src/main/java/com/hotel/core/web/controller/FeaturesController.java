package com.hotel.core.web.controller;

import com.hotel.core.entity.Features;
import com.hotel.core.entity.Users;
import com.hotel.core.service.FeaturesService;
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
@RequestMapping("features")
public class FeaturesController extends BaseController {

    @Autowired
    private FeaturesService featuresService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加特色
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertFeatures")
    public JsonResult insertFeatures(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                     HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertFeatures");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            String featureIntro = ToolUtil.str("featureIntro",request);
            Features features = new Features();
            features.setFeatureIntro(featureIntro);
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String featurePicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + featurePicture);
                if (featurePicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                features.setFeaturePicture(featurePicture);
            }
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            features.setCreaterId(users.getId());
            features.setCreaterUsername(users.getUsername());
            features.setCreateTime(new Date());
            features.setExistStates(1);
            //执行添加并清除正常列表的缓存
            int result = featuresService.insertFeatures(features,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            }else{
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        }catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }


    //修改特色
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateFeatures")
    public JsonResult updateFeatures(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                     HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateFeatures");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String featureIntro = ToolUtil.str("featureIntro",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Features features = featuresService.selectFeaturesById(id);
            if(features == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"酒店特色不存在");
                return jsonResult;
            }
            features.setFeatureIntro(featureIntro);
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String featurePicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + featurePicture);
                if (featurePicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                features.setFeaturePicture(featurePicture);
            }
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            features.setModifierId(users.getId());
            features.setModifierUsername(users.getUsername());
            features.setModifyTime(new Date());
            //执行修改并清除正常列表的缓存
            int result = featuresService.updateFeatures(features,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            }else{
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        }catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //回收特色
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleFeatures")
    public JsonResult recycleFeatures(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleFeatures");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Features features = featuresService.selectFeaturesById(id);
            if(features == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"酒店特色不存在");
                return jsonResult;
            }
            features.setExistStates(2);
            featuresService.updateFeaturesByStates(features,KEYLIST);
            //执行回收并清除正常列表的缓存
            int result = featuresService.updateFeaturesByStates(features,RECYCLEKEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            }else{
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //将回收的特色恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleFeatures")
    public JsonResult recoveryRecycleFeatures(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleFeatures");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Features features = featuresService.selectFeaturesById(id);
            if(features == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"酒店特色不存在");
                return jsonResult;
            }
            features.setExistStates(1);
            featuresService.updateFeaturesByStates(features,KEYLIST);
            //执行恢复并清除正常列表的缓存
            int result = featuresService.updateFeaturesByStates(features,RECYCLEKEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            }else{
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //删除特色
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteFeatures")
    public JsonResult deleteFeatures(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteFeatures");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Features features = featuresService.selectFeaturesById(id);
            if(features == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"酒店特色不存在");
                return jsonResult;
            }
            //执行删除并清除正常列表的缓存
            int result = featuresService.deleteFeaturesById(id,RECYCLEKEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            }else{
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //批量回收酒店特色
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleFeaturesBatch")
    public JsonResult recycleFeaturesBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleFeaturesBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //回收特色并清除回收列表缓存
            int result = featuresService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("features:" + id);
            }
            //清除正常列表的缓存
            featuresService.clearCache(KEYLIST);
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

    //将回收的酒店特色批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleFeaturesBatch")
    public JsonResult recoveryRecycleFeaturesBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleFeaturesBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //恢复特色并清除回收列表缓存
            int result = featuresService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("features:" + id);
            }
            //清除正常列表的缓存
            featuresService.clearCache(KEYLIST);
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

    //批量删除酒店特色
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteFeaturesBatch")
    public JsonResult deleteFeaturesBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteFeaturesBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //删除特色并清除回收列表缓存
            int result = featuresService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("features:" + id);
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
