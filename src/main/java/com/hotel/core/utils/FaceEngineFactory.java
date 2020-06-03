package com.hotel.core.utils;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class FaceEngineFactory extends BasePooledObjectFactory<FaceEngine> {

    private String appId;
    private String sdkKey;
    private String sdkLibPath;
    //引擎配置
    private EngineConfiguration engineConfiguration;
    //最多能检测出的人脸数
    private Integer detectFaceMaxNum = 10;
    //人脸相对于所在图片的长边的占比,图像模式(ASF_DETECT_MODE_IMAGE)下有效值范围[2，32]
    private Integer detectFaceScaleVal = 16;
    //图片模式
    private DetectMode detectMode = DetectMode.ASF_DETECT_MODE_IMAGE;
    //人脸检测角度，逆时针0度
    private DetectOrient detectFaceOrientPriority = DetectOrient.ASF_OP_0_ONLY;


    public FaceEngineFactory(String sdkLibPath, String appId, String sdkKey, EngineConfiguration engineConfiguration) {
        this.sdkLibPath = sdkLibPath;
        this.appId = appId;
        this.sdkKey = sdkKey;
        this.engineConfiguration = engineConfiguration;

    }


    @Override
    public FaceEngine create() throws Exception {

        FaceEngine faceEngine = new FaceEngine(sdkLibPath);
        //-=======================
        //激活sdk
        int activeCode = faceEngine.activeOnline(appId, sdkKey);
        System.out.println("faceEngineActiveCode:" + activeCode + "==========================");
        //初始化人脸引擎
        int initCode = faceEngine.init(engineConfiguration);
        System.out.println("faceEngineInitCode:" + initCode + "==========================");
        return faceEngine;
    }

    @Override
    public PooledObject<FaceEngine> wrap(FaceEngine faceEngine) {
        return new DefaultPooledObject<>(faceEngine);
    }

    //销毁
    @Override
    public void destroyObject(PooledObject<FaceEngine> p) throws Exception {
        FaceEngine faceEngine = p.getObject();
        int unInitCode = faceEngine.unInit();
        System.out.println("faceEngineUnInitCode:" + unInitCode + "==========================");
        super.destroyObject(p);
    }
}
