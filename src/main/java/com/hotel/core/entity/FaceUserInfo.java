package com.hotel.core.entity;

public class FaceUserInfo {

    //人脸比对
    private String faceId;
    private String name;
    //相似度
    private Integer similarValue;
    //人脸特征
    private byte[] faceFeature;
    //人脸userId
    private Integer userId;
    private String username;

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSimilarValue() {
        return similarValue;
    }

    public void setSimilarValue(Integer similarValue) {
        this.similarValue = similarValue;
    }

    public byte[] getFaceFeature() {
        return faceFeature;
    }

    public void setFaceFeature(byte[] faceFeature) {
        this.faceFeature = faceFeature;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
