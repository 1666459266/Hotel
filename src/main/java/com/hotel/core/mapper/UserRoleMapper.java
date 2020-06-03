package com.hotel.core.mapper;

import com.hotel.core.entity.UserRole;

import java.util.List;

public interface UserRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRole userRole);

    int insertSelective(UserRole userRole);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole userRole);

    int updateByPrimaryKey(UserRole userRole);

    //查询所有用户角色
    List<UserRole> selectUserRoleList();

    //统计条数
    int selectCounts();

    //根据userId删除用户角色(删除用户的同时删除用户角色)
    int deleteUserRoleByUserId(Integer userId);

    //批量删除
    int deleteBatch(List list);

}