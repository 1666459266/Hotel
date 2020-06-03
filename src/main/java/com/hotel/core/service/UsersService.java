package com.hotel.core.service;

import com.hotel.core.entity.Users;

import java.util.List;
import java.util.Map;

public interface UsersService {

    //添加用户
    public int insertUser(Users users,String KeyList);

    //修改用户
    public int updateUser(Users users,String KeyList);

    //根据id删除用户
    public int deleteUserById(Integer id,String username,String KeyList);

    //根据id查询用户
    public Users selectUsersById(Integer id);

    //根据用户名查询用户
    public Users selectUserByUsername(String username);

    //获取用户分页列表
    public List<Users> selectUserListByLimit(Map<String,Object> map,String KeyList);

    //统计数据条数
    public int selectCounts(Integer states,String KeyList);

    //修改用户状态
    public int updateUserByStates(Users users,String KeyList);

    //搜索用户（模糊查询）
    public List<Users> searchUser(String username,String phone,String email);

    //根据会员等级降序排序
    public List<Users> selectUserListByMembershipLevelSortDesc(Map<String,Object> map,String KeyList);

    //根据会员等级升序排序
    public List<Users> selectUserListByMembershipLevelSortAsc(Map<String,Object> map,String KeyList);

    //根据性别筛选
    public List<Users> selectUserListByGender(Map<String,Object> map);

    //根据角色筛选
    public List<Users> selectUserListByRole(Map<String,Object> map);

    //批量修改状态
    public int updateStatesBatch(List list,Integer states,String KeyList);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
