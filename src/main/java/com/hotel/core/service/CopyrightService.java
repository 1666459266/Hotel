package com.hotel.core.service;

import com.hotel.core.entity.Copyright;

import java.util.List;
import java.util.Map;

public interface CopyrightService {

    //添加版权信息
    public int insertCopyright(Copyright copyright, String KeyList);

    //修改版权信息
    public int updateCopyright(Copyright copyright, String KeyList);

    //根据id删除版权信息
    public int deleteCopyrightById(Integer id, String KeyList);

    //根据id查询版权信息
    public Copyright selectCopyrightById(Integer id);

    //获取菜单分页列表
    public List<Copyright> selectCopyrightListByLimit(Map<String, Object> map, String KeyList);

    //统计条数
    public int selectCounts(Integer states, String KeyList);

    //修改版权信息状态
    public int updateCopyrightByStates(Copyright copyright, String KeyList);

    //批量修改状态
    public int updateStatesBatch(List list, Integer states, String KeyList);

    //批量删除
    public int deleteBatch(List list, String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
