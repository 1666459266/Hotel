package com.hotel.core.web.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.druid.util.StringUtils;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.hotel.core.config.CustomToken;
import com.hotel.core.entity.*;
import com.hotel.core.mapper.UserFaceInfoMapper;
import com.hotel.core.mapper.UserRoomMapper;
import com.hotel.core.mapper.UsersMapper;
import com.hotel.core.service.FaceEngineService;
import com.hotel.core.service.UserFaceInfoService;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.ErrorCodeEnum;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.ToolUtil;
import com.hotel.core.utils.uploadfile.FileUploadTool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("face")
public class FaceEngineController extends BaseController {

    @Autowired
    private FaceEngineService faceEngineService;

    @Autowired
    private UserFaceInfoService userFaceInfoService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRoomMapper userRoomMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserFaceInfoMapper userFaceInfoMapper;

    //人脸添加
//  @RequiresRoles(value = {"user"})
    @RequestMapping("/faceAdd")
    @ResponseBody
    public JsonResult faceAdd(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                              HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...faceAdd");
        String username1 = ToolUtil.str("username",request);
        try {

            String msg = "";
            FileUploadTool fileUploadTool = new FileUploadTool();
            String faceFile = fileUploadTool.createFile(multipartFile, request);
            System.out.println("URL:" + faceFile);
            if (faceFile != null) {
                msg = "success";
            } else {
                msg = "fail";
            }
            byte[] decode = Base64.decode(base64Process(faceFile));
            ImageInfo imageInfo = ImageFactory.getRGBData(decode);

            //人脸特征获取
            byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
            if (bytes == null) {
                return JsonResult.build(ErrorCodeEnum.NO_FACE_DETECTED);
            }
            if(ToolUtil.equalBool(username1) == false){
                //获取当前登录的用户名
                String username = (String) SecurityUtils.getSubject().getPrincipal();
                Users users = usersService.selectUserByUsername(username);
                Integer userId = users.getId();
                Map<String,Object> map = new HashMap();
                map.put("userId",userId);
                List<UserFaceInfo> userFaceInfoList = userFaceInfoMapper.getUserFaceInfoByUserId(map);
                System.out.println(userFaceInfoList);
                if(userFaceInfoList.size()==0){
                    UserFaceInfo userFaceInfo = new UserFaceInfo();
                    userFaceInfo.setFaceFeature(bytes);
                    userFaceInfo.setFacePath(faceFile);
                    userFaceInfo.setFaceId(RandomUtil.randomString(10));
                    userFaceInfo.setGroupId(100);

                    userFaceInfo.setUserId(users.getId());
                    System.out.println(users.getId());
                    List<UserRoom> userRoomList = userRoomMapper.selectUserRoomListByUserId(users.getId());

                    for (UserRoom x : userRoomList) {
                        if (x.getOrderOverdueStates() == 1 && x.getOrderStates() == 1) {
                            userFaceInfo.setRoomNumber(x.getRoomNumber());
                        }
                    }
                    System.out.println(username);
                    userFaceInfo.setName(users.getTruename());
                    userFaceInfo.setUsername(username);

                    //人脸特征插入到数据库
                    userFaceInfoService.insertUserFaceSelective(userFaceInfo, KEYLIST);

                    return JsonResult.build(FLAG_SUCCESS, "success");
                }else{
                    return JsonResult.build(FLAG_FAILED, "人脸已存在");
                }
            }else{
                Users users = usersService.selectUserByUsername(username1);
                Integer userId = users.getId();
                Map<String,Object> map = new HashMap();
                map.put("userId",userId);
                List<UserFaceInfo> userFaceInfoList = userFaceInfoMapper.getUserFaceInfoByUserId(map);
                System.out.println(userFaceInfoList);
                if(userFaceInfoList.size()==0){
                    UserFaceInfo userFaceInfo = new UserFaceInfo();
                    userFaceInfo.setFaceFeature(bytes);
                    userFaceInfo.setFacePath(faceFile);
                    userFaceInfo.setFaceId(RandomUtil.randomString(10));
                    userFaceInfo.setGroupId(100);

                    userFaceInfo.setUserId(users.getId());
                    System.out.println(users.getId());
                    List<UserRoom> userRoomList = userRoomMapper.selectUserRoomListByUserId(users.getId());

                    for (UserRoom x : userRoomList) {
                        if (x.getOrderOverdueStates() == 1 && x.getOrderStates() == 1) {
                            userFaceInfo.setRoomNumber(x.getRoomNumber());
                        }
                    }
                    System.out.println(username1);
                    userFaceInfo.setName(users.getTruename());
                    userFaceInfo.setUsername(username1);

                    //人脸特征插入到数据库
                    userFaceInfoService.insertUserFaceSelective(userFaceInfo, KEYLIST);

                    return JsonResult.build(FLAG_SUCCESS, "success");
                }else{
                    return JsonResult.build(FLAG_FAILED, "人脸已存在");
                }
            }

        }catch (NullPointerException npe) {
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED, "文件不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED, e.getMessage());
        }
        return JsonResult.build(ErrorCodeEnum.UNKNOWN);
    }

    //人脸识别
//    @RequiresRoles(value = {"user"})
    @RequestMapping("/faceSearch")
    @ResponseBody
    public JsonResult faceSearch(String file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...faceSearch");
//        Integer roomNumber = ToolUtil.integer("roomNumber", request);
//        System.out.println(file);
        if(file == null){
            return JsonResult.build(FLAG_FAILED, "没有图片");
        }
//        if ((ToolUtil.equalBool(roomNumber)) == false) {
//            jsonResult = JsonResult.build(FLAG_FAILED, "数据缺失");
//            return jsonResult;
//        }

//        File file = File.createTempFile("temp", null);
//        multipartFile.transferTo(file);   //sourceFile为传入的MultipartFile
//        InputStream inputStream = new FileInputStream(file);
//        file.deleteOnExit();

        byte[] decode = Base64.decode(base64Process1(file));
        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);


        //人脸特征获取
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return JsonResult.build(ErrorCodeEnum.NO_FACE_DETECTED);
        }
        //人脸比对，获取比对结果
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, 100);

        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            FaceSearchResInfo faceSearchResInfo = new FaceSearchResInfo();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResInfo);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //人脸检测
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;
                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//红色
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();

                faceSearchResInfo.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResInfo.setAge(processInfoList.get(0).getAge());
                faceSearchResInfo.setGender(processInfoList.get(0).getGender().equals(1) ? "女" : "男");
                faceSearchResInfo.setName(faceUserInfo.getName());
            }

            return JsonResult.build(FLAG_SUCCESS, faceSearchResInfo);
        }
        return JsonResult.build(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }

    @RequestMapping("/faceLogin")
    @ResponseBody
    public JsonResult faceLogin(String file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...faceLogin");
//        Integer groupId = ToolUtil.integer("groupId",request);
//        System.out.println(file);

//        File file = File.createTempFile("temp", null);
//        multipartFile.transferTo(file);   //sourceFile为传入的MultipartFile
//        InputStream inputStream = new FileInputStream(file);
//        file.deleteOnExit();

        byte[] decode = Base64.decode(base64Process1(file));
        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
//        BufferedImage bufImage = ImageIO.read(inputStream);
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);


        //人脸特征获取
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return JsonResult.build(ErrorCodeEnum.NO_FACE_DETECTED);
        }
        //人脸比对，获取比对结果
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceLogin(bytes, 100);

        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            FaceSearchResInfo faceSearchResInfo = new FaceSearchResInfo();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResInfo);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //人脸检测
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;
                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//红色
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResInfo.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                if (faceSearchResInfo.getSimilarValue() >= 90) {
                    System.out.println("执行登录");
                    Users users = usersService.selectUsersById(faceUserInfo.getUserId());
//                    System.out.println(faceUserInfo.getUserId());
                    String username = users.getUsername();
                    System.out.println(username);
                    //获取当前用户
                    Subject currentUser = SecurityUtils.getSubject();
                    //判断当前用户是否认证
                    if (!currentUser.isAuthenticated()) {
                        CustomToken usernamePasswordToken = new CustomToken(username);
                        try {
                            currentUser.login(usernamePasswordToken);
                        } catch (UnknownAccountException uae) {
                            jsonResult = JsonResult.build(FLAG_FAILED, "账户不存在");
                            return jsonResult;
                        } catch (IncorrectCredentialsException ice) {
                            jsonResult = JsonResult.build(FLAG_FAILED, "密码错误");
                            return jsonResult;
                        } catch (LockedAccountException lae) {
                            jsonResult = JsonResult.build(FLAG_FAILED, "账户被锁定");
                            return jsonResult;
                        } catch (AuthenticationException ae) {

                        }
                    }
                    jsonResult = JsonResult.build(FLAG_SUCCESS, "登录成功", username);
                    System.out.println("登录成功");
                    return jsonResult;
                }
            }
        }
        return JsonResult.build(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }


    //检测人脸
    @RequestMapping("/detectFaces")
    @ResponseBody
    public List<FaceInfo> detectFaces(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                                      HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = File.createTempFile("temp", null);
        multipartFile.transferTo(file);   //sourceFile为传入的MultipartFile
        InputStream inputStream = new FileInputStream(file);
        file.deleteOnExit();

//        byte[] decode = Base64.decode(image);
//        InputStream inputStream = new ByteArrayInputStream(inputStream);a
//        BufferedImage bufImage = ImageIO.read(inputStream);
//        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        ImageInfo imageInfo = ImageFactory.getRGBData(inputStream);

        if (inputStream != null) {
            inputStream.close();
        }
        List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
//        List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
//        ProcessInfo processInfo = new ProcessInfo();
//        if (CollectionUtil.isNotEmpty(processInfoList)) {
//            //人脸检测
//            List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
//            processInfo.setAge(processInfoList.get(0).getAge());
//            processInfo.setGender(processInfoList.get(0).getGender());
//            processInfo.setLiveness(processInfoList.get(0).getLiveness());
//            processInfo.setFace3d(processInfoList.get(0).getFace3d());
//        }
        return faceInfoList;
    }

    //删除人脸
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.AND)
    @RequestMapping("/deleteFace")
    @ResponseBody
    public JsonResult deleteFace(HttpServletRequest request, HttpServletResponse response) {

        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteFace");
        try {
            Integer id = ToolUtil.integer("id", request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED, "id不能为空");
                return jsonResult;
            }
            UserFaceInfo userFaceInfo = userFaceInfoService.selectUserFaceInfoById(id);
            if (userFaceInfo == null) {
                jsonResult = JsonResult.build(FLAG_FAILED, "该人脸信息不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = userFaceInfoService.deleteUserFaceInfoById(id, RECYCLEKEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED, e.getMessage());
        }
        return jsonResult;
    }

    //图片转换
    private String base64Process1(String base64Str) {
        if (!StringUtils.isEmpty(base64Str)) {
            String photoBase64 = base64Str.substring(0, 30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if (indexOf > 0) {
                base64Str = base64Str.substring(indexOf + 7);
            }

            return base64Str;
        } else {
            return "";
        }
    }

    public static String base64Process(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

}