package com.hotel.core.utils;

import cn.hutool.core.codec.Base64;
import com.alibaba.druid.util.StringUtils;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FaceView {
    static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

    public static void main(String[] args) throws IOException {

        String appId = "GN617eujnnKNADov66rtFz3fPBR3LMGiaXpG3X4fhQzE";
        String sdkKey = "5jLasHRHqkGGrj4jKgzxiMjGBEtLsnw22c6dcr9tLGe2";

        FaceEngine faceEngine = new FaceEngine("D:\\arcsoft-lib");
        //激活引擎
        int activeCode = faceEngine.activeOnline(appId, sdkKey);

        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }

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


        //初始化引擎
        int initCode = faceEngine.init(engineConfiguration);

        if (initCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
        String file = "D:\\JAVA\\face";
        camera();
        byte[] decode = Base64.decode(base64Process(file));
        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        //进行人脸检测，detectedFaces为输出项
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        int left = faceInfoList.get(0).getRect().getLeft();
        int top = faceInfoList.get(0).getRect().getTop();
        int width = faceInfoList.get(0).getRect().getRight() - left;
        int height = faceInfoList.get(0).getRect().getBottom() - top;
        Graphics2D graphics2D = bufImage.createGraphics();
        graphics2D.setColor(Color.RED);//红色
        BasicStroke stroke = new BasicStroke(5f);
        graphics2D.setStroke(stroke);
        graphics2D.drawRect(left, top, width, height);
    }

    public static String base64Process(String base64Str) {
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

    public static void camera() {
        try {
            OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(1);
            grabber.start();   //开始获取摄像头数据
            CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
            canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            canvas.setAlwaysOnTop(true);
            int ex = 0;
            while (true) {
                if (!canvas.isDisplayable()) {//窗口是否关闭
                    grabber.stop();//停止抓取
                    System.exit(2);//退出
                    break;
                }
                canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
                ex++;
                opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
                opencv_imgcodecs.imwrite("d:\\img\\" + ex + ".png", mat);
                Thread.sleep(200);//50毫秒刷新一次图像

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
}

