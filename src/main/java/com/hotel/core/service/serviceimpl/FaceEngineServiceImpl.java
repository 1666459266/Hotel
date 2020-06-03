package com.hotel.core.service.serviceimpl;

import cn.hutool.core.collection.CollectionUtil;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.toolkit.ImageInfo;
import com.google.common.collect.Lists;
import com.hotel.core.entity.FaceUserInfo;
import com.hotel.core.entity.ProcessInfo;
import com.hotel.core.entity.UserFaceInfo;
import com.hotel.core.mapper.UserFaceInfoMapper;
import com.hotel.core.service.FaceEngineService;
import com.hotel.core.utils.FaceEngineFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class FaceEngineServiceImpl implements FaceEngineService {

    @Value("${config.arcface-sdk.sdk-lib-path}")
    public String sdkLibPath;

    @Value("${config.arcface-sdk.app-id}")
    public String appId;

    @Value("${config.arcface-sdk.sdk-key}")
    public String sdkKey;

    @Value("${config.arcface-sdk.thread-pool-size}")
    public Integer threadPoolSize;

    //阈值
    private Integer passRate = 80;

    //线程池
    private ExecutorService executorService;

    //对象池
    private GenericObjectPool<FaceEngine> faceEngineObjectPool;

    @Autowired
    private UserFaceInfoMapper userFaceInfoMapper;

    @PostConstruct
    public void init() {
        //创建固定大小的线程池
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        //创建对象池
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        //pool中最多能保留多少个空闲对象
        poolConfig.setMaxIdle(threadPoolSize);
        //pool中对象最多能有多少
        poolConfig.setMaxTotal(threadPoolSize);
        //pool中最少有多少个空闲对象
        poolConfig.setMinIdle(threadPoolSize);
        //后进先出,默认为true，即当池中有空闲可用的对象时，调用borrowObject方法会返回最近（后进）的实例
        poolConfig.setLifo(false);

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        faceEngineObjectPool = new GenericObjectPool(new FaceEngineFactory(sdkLibPath, appId, sdkKey, engineConfiguration), poolConfig);//底层库算法对象池
    }

    //扩大100倍
    private int plusHundred(Float value) {
        BigDecimal target = new BigDecimal(value);
        BigDecimal hundred = new BigDecimal(100f);
        //乘法
        return target.multiply(hundred).intValue();
    }

    //人脸检测
    @Override
    public List<FaceInfo> detectFaces(ImageInfo imageInfo) {
        FaceEngine faceEngine = null;
        try {
            //获取引擎对象
            faceEngine = faceEngineObjectPool.borrowObject();

            //人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();

            //人脸检测
            faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

            return faceInfoList;
        }catch (Exception e) {
            logger.error("", e);
        }finally {
            if (faceEngine != null) {
                //释放引擎对象
                faceEngineObjectPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    //获取信息
    @Override
    public List<ProcessInfo> process(ImageInfo imageInfo) {
        FaceEngine faceEngine = null;
        UserFaceInfo userFaceInfo = new UserFaceInfo();
        try {
            //获取引擎对象
            faceEngine = faceEngineObjectPool.borrowObject();
            //人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
            //人脸检测
            faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
            int processResult = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, FunctionConfiguration.builder().supportAge(true).supportGender(true).supportFace3dAngle(true).supportLiveness(true).build());
            //创建集合
            List<ProcessInfo> processInfoList = Lists.newLinkedList();

            //性别提取
            List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
            int genderCode = faceEngine.getGender(genderInfoList);

            //年龄提取
            List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
            int ageCode = faceEngine.getAge(ageInfoList);

            List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
            int face3dCode = faceEngine.getFace3DAngle(face3DAngleList);

            //活体检测
            List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
            int livenessCode = faceEngine.getLiveness(livenessInfoList);

            //IR活体检测
            List<IrLivenessInfo> irLivenessInfo = new ArrayList<>();
            int livenessIr = faceEngine.getLivenessIr(irLivenessInfo);

            for (int i = 0; i < genderInfoList.size(); i++) {
                ProcessInfo processInfo = new ProcessInfo();
                processInfo.setGender(genderInfoList.get(i).getGender());
                processInfo.setAge(ageInfoList.get(i).getAge());
//                processInfo.setFace3d(face3DAngleList.get(i).getRoll()+ "" + face3DAngleList.get(0).getYaw());
//                processInfo.setLiveness(livenessInfoList.get(i).getLiveness());
                processInfoList.add(processInfo);
            }
            return processInfoList;
        }catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                faceEngineObjectPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    //人脸特征
    @Override
    public byte[] extractFaceFeature(ImageInfo imageInfo) throws InterruptedException {
        FaceEngine faceEngine = null;
        try {
            //获取引擎对象
            faceEngine = faceEngineObjectPool.borrowObject();

            //人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();

            //人脸检测
            int i = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

            if (CollectionUtil.isNotEmpty(faceInfoList)) {
                FaceFeature faceFeature = new FaceFeature();
                //提取人脸特征
                faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
                return faceFeature.getFeatureData();
            }

        }catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                faceEngineObjectPool.returnObject(faceEngine);
            }
        }
        return null;
    }
    static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    //人脸对比
    @Override
    public List<FaceUserInfo> compareFaceFeature(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException {
        List<FaceUserInfo> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表

        //获取目标人脸特征
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature);

        //根据房间号从数据库中取出人脸库
        List<FaceUserInfo> faceInfoList = userFaceInfoMapper.getUserFaceInfoByGroupId(groupId);
        //分成1000一组，多线程处理
        List<List<FaceUserInfo>> faceUserInfoPartList = Lists.partition(faceInfoList, 1000);
        CompletionService<List<FaceUserInfo>> completionService = new ExecutorCompletionService(executorService);
        for (List<FaceUserInfo> part : faceUserInfoPartList) {
            completionService.submit(new CompareFaceTask(part, targetFaceFeature));
        }
        for (int i = 0; i < faceUserInfoPartList.size(); i++) {
            List<FaceUserInfo> faceUserInfoList = completionService.take().get();
            if (CollectionUtil.isNotEmpty(faceInfoList)) {
                resultFaceInfoList.addAll(faceUserInfoList);
            }
        }
        return resultFaceInfoList;
    }

    public List<FaceUserInfo> compareFaceLogin(byte[] faceFeature,Integer groupId) throws InterruptedException, ExecutionException {
        List<FaceUserInfo> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表

        //获取目标人脸特征
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature);

        //根据房间号从数据库中取出人脸库
        List<FaceUserInfo> faceInfoList = userFaceInfoMapper.getUserFaceInfoByGroupId(groupId);

        //分成1000一组，多线程处理
        List<List<FaceUserInfo>> faceUserInfoPartList = Lists.partition(faceInfoList, 1000);
        CompletionService<List<FaceUserInfo>> completionService = new ExecutorCompletionService(executorService);
        for (List<FaceUserInfo> part : faceUserInfoPartList) {
            completionService.submit(new CompareFaceTask(part, targetFaceFeature));
        }
        for (int i = 0; i < faceUserInfoPartList.size(); i++) {
            List<FaceUserInfo> faceUserInfoList = completionService.take().get();
            if (CollectionUtil.isNotEmpty(faceInfoList)) {
                resultFaceInfoList.addAll(faceUserInfoList);
            }
        }
        return resultFaceInfoList;
    }

    //比较
    private class CompareFaceTask implements Callable<List<FaceUserInfo>> {

        private List<FaceUserInfo> faceUserInfoList;
        private FaceFeature targetFaceFeature;


        public CompareFaceTask(List<FaceUserInfo> faceUserInfoList, FaceFeature targetFaceFeature) {
            this.faceUserInfoList = faceUserInfoList;
            this.targetFaceFeature = targetFaceFeature;
        }

        @Override
        public List<FaceUserInfo> call() throws Exception {
            FaceEngine faceEngine = null;
            //识别到的人脸列表
            List<FaceUserInfo> resultFaceInfoList = Lists.newLinkedList();
            try {
                faceEngine = faceEngineObjectPool.borrowObject();
                for (FaceUserInfo faceUserInfo : faceUserInfoList) {
                    FaceFeature sourceFaceFeature = new FaceFeature();
                    sourceFaceFeature.setFeatureData(faceUserInfo.getFaceFeature());
                    FaceSimilar faceSimilar = new FaceSimilar();
                    faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
                    //获取相似值
                    Integer similarValue = plusHundred(faceSimilar.getScore());

                    //相似值大于配置预期，加入到识别到人脸的列表
                    if (similarValue > passRate) {
                        FaceUserInfo info = new FaceUserInfo();
                        info.setName(faceUserInfo.getName());
                        info.setFaceId(faceUserInfo.getFaceId());
                        info.setSimilarValue(similarValue);
                        info.setUserId(faceUserInfo.getUserId());
                        info.setUsername(faceUserInfo.getUsername());
                        resultFaceInfoList.add(info);
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            } finally {
                if (faceEngine != null) {
                    faceEngineObjectPool.returnObject(faceEngine);
                }
            }
            return resultFaceInfoList;
        }

    }

}
