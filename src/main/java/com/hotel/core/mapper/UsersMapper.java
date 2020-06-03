package com.hotel.core.mapper;

import com.hotel.core.entity.Users;

import java.util.List;
import java.util.Map;

public interface UsersMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Users users);

    int insertSelective(Users users);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users users);

    int updateByPrimaryKey(Users users);

    //根据用户名查询用户
    Users selectUserByUsername(String username);

    //获取用户分页列表
    List<Users> selectUserListByLimit(Map<String,Object> map);

    //统计数据条数
    int selectCounts(Integer states);

    //修改用户状态
    int updateUserByStates(Users users);

    //搜索用户（模糊查询）
    List<Users> searchUser(String username,String phone,String email);

    //根据会员等级降序排序
    List<Users> selectUserListByMembershipLevelSortDesc(Map<String,Object> map);

    //根据会员等级升序排序
    List<Users> selectUserListByMembershipLevelSortAsc(Map<String,Object> map);

    //根据性别筛选
    List<Users> selectUserListByGender(Map<String,Object> map);

    //根据角色筛选
    List<Users> selectUserListByRole(Map<String,Object> map);

    //批量修改状态
    int updateStatesBatch(List list,Integer states);

    //批量删除
    int deleteBatch(List list);

}