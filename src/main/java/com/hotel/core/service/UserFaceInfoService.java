package com.hotel.core.service;

import com.hotel.core.entity.UserFaceInfo;

import java.util.List;
import java.util.Map;

public interface UserFaceInfoService {
    //添加数据
    public int insertUserFaceSelective(UserFaceInfo userFaceInfo, String KeyList);

    //修改人脸数据
    public int updateUserFaceInfo(UserFaceInfo userFaceInfo, String KeyList);

    //根据id删除人脸数据
    public int deleteUserFaceInfoById(Integer id, String KeyList);

    //根据id查询人脸信息
    public UserFaceInfo selectUserFaceInfoById(Integer id);

    //获取人脸信息分页列表
    public List<UserFaceInfo> selectUserFaceInfoListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts();

}
