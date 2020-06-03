package com.hotel.core.service;

import com.hotel.core.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    //新增用户角色
    public int insertUserRole(UserRole userRole,String KeyList);

    //修改用户角色
    public int updateUserRole(UserRole userRole,String KeyList);

    //根据id删除用户角色
    public int deleteUserRoleById(Integer id,String KeyList);

    //根据id查询用户角色
    public UserRole selectUserRoleById(Integer id);

    //查询所有用户角色列表
    public List<UserRole> selectUserRoleList(String KeyList);

    //统计条数
    public int selectCounts(String KeyList);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
