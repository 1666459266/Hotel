package com.hotel.core.service;

import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import com.hotel.core.entity.FaceUserInfo;
import com.hotel.core.entity.ProcessInfo;
import com.hotel.core.service.serviceimpl.FaceEngineServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FaceEngineService {

    public final static Logger logger = LoggerFactory.getLogger(FaceEngineServiceImpl.class);

    //调用人脸检测
    List<FaceInfo> detectFaces(ImageInfo imageInfo);

    //检测RGB活体、年龄、性别、三维角度检测
    //可以调用getLiveness(List) ，getAge(List)，getGender(List)，getFace3DAngle(List)
    List<ProcessInfo> process(ImageInfo imageInfo);

    //提取人脸特征
    byte[] extractFaceFeature(ImageInfo imageInfo) throws InterruptedException;

    //人脸比对
    List<FaceUserInfo> compareFaceFeature(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException;

    //人脸识别登陆
    List<FaceUserInfo> compareFaceLogin(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException;
}
