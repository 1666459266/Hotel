package com.hotel.core.web.controller;

import com.hotel.core.entity.Background;
import com.hotel.core.entity.Users;
import com.hotel.core.service.BackgroundService;
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
@RequestMapping("background")
public class BackgroundController extends BaseController {

    @Autowired
    private BackgroundService backgroundService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加背景
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertBackground")
    public JsonResult insertBackground(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                       HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertBackground");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            String backgroundIntro = ToolUtil.str("backgroundIntro",request);
            Background background = new Background();
            background.setBackgroundIntro(backgroundIntro);
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String backgroundPicture = fileUploadTool.createFile(multipartFile,request);
                if (backgroundPicture != null){
                    msg = "success";
                } else {
                    msg = "fail";
                }
                background.setBackgroundPicture(backgroundPicture);
            }
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            background.setCreaterId(users.getId());
            background.setCreaterUsername(users.getUsername());
            background.setCreateTime(new Date());
            background.setExistStates(1);
            //执行并添加缓存列表
            int result = backgroundService.insertBackground(background, KEYLIST);
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

    //修改背景图片
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateBackground")
    public JsonResult updateBackground(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                       HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateBackground");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String backgroundIntro = ToolUtil.str("backgroundIntro",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Background background = backgroundService.selectBackgroundById(id);
            if (background == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该背景图片不存在");
                return jsonResult;
            }
            background.setBackgroundIntro(backgroundIntro);
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String backgroundPicture = fileUploadTool.createFile(multipartFile,request);
                if (backgroundPicture != null){
                    msg = "success";
                } else {
                    msg = "fail";
                }
                background.setBackgroundPicture(backgroundPicture);
            }
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            background.setModifierId(users.getId());
            background.setModifierUsername(users.getUsername());
            background.setModifyTime(new Date());
            //执行并添加缓存列表
            int result = backgroundService.updateBackground(background, KEYLIST);
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

    //回收图片
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleBackground")
    public JsonResult recycleBackground(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleBackground");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Background background = backgroundService.selectBackgroundById(id);
            if (background == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该图片不存在");
                return jsonResult;
            }
            background.setExistStates(2);
            //先清除正常列表缓存
            backgroundService.updateBackgroundByStates(background,KEYLIST);
            //执行回收并清除回收列表缓存
            int result = backgroundService.updateBackgroundByStates(background, RECYCLEKEYLIST);
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

    //将图片恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleBackground")
    public JsonResult recoveryRecycleRestaurant(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleBackground");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Background background = backgroundService.selectBackgroundById(id);
            if (background == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该图片不存在");
                return jsonResult;
            }
            background.setExistStates(1);
            //先清除正常列表缓存
            backgroundService.updateBackgroundByStates(background,KEYLIST);
            //执行恢复并清除回收列表缓存
            int result = backgroundService.updateBackgroundByStates(background, RECYCLEKEYLIST);
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

    //删除图片
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteBackground")
    public JsonResult deleteRestaurant(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteBackground");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Background background = backgroundService.selectBackgroundById(id);
            if (background == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该图片不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表缓存
            int result = backgroundService.deleteBackgroundById(id,RECYCLEKEYLIST);
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

    //批量回收背景图片
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleBackgroundBatch")
    public JsonResult recycleBackgroundBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleBackgroundBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //回收商品并清除回收列表缓存
            int result = backgroundService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("background:" + id);
            }
            //清除正常列表的缓存
            backgroundService.clearCache(KEYLIST);
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

    //将回收的背景图片批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleBackgroundBatch")
    public JsonResult recoveryRecycleBackgroundBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleBackgroundBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //恢复商品并清除回收列表缓存
            int result = backgroundService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("background:" + id);
            }
            //清除正常列表的缓存
            backgroundService.clearCache(KEYLIST);
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

    //批量删除背景图片
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteBackgroundBatch")
    public JsonResult deleteBackgroundBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteBackgroundBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //删除商品并清除回收列表缓存
            int result = backgroundService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("background:" + id);
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
