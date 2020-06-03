package com.hotel.core.mapper;

import com.hotel.core.entity.FaceUserInfo;
import com.hotel.core.entity.UserFaceInfo;

import java.util.List;
import java.util.Map;

public interface UserFaceInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserFaceInfo userFaceInfo);

    int insertSelective(UserFaceInfo userFaceInfo);

    UserFaceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFaceInfo userFaceInfo);

    int updateByPrimaryKey(UserFaceInfo userFaceInfo);

    //人脸识别列表
    List<UserFaceInfo> findUserFaceInfoList();

    List<UserFaceInfo> getUserFaceInfoByUserId(Map<String,Object> map);

    //通过房间id查询人
    List<FaceUserInfo> getUserFaceInfoByRoomNumber(Integer roomNumber);

    //通过分组查询特征
    List<FaceUserInfo> getUserFaceInfoByGroupId(Integer groupId);

    //获取商品分页列表
    List<UserFaceInfo> selectUserFaceInfoByLimit(Map<String, Object> map);

    //统计条数
    int selectCounts();

}