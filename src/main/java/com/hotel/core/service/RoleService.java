package com.hotel.core.service;

import com.hotel.core.entity.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleService {

    //添加角色
    public int insertRole(Role role,String KeyList);

    //修改角色
    public int updateRole(Role role,String KeyList);

    //根据id删除角色
    public int deleteRoleById(Integer id,String KeyList);

    //根据id查询角色
    public Role selectRoleById(Integer id);

    //根据用户名查询用户角色
    public Set<String> selectRoleNameByUsername(String username);

    //获取角色分页列表
    public List<Role> selectRoleListByLimit(Map<String,Object> map,String KeyList);

    //统计条数
    public int selectCounts(Integer states,String KeyList);

    //修改角色状态
    public int updateRoleByStates(Role role,String KeyList);

    //搜索角色（模糊查询）
    public List<Role> searchRole(String roleName,String roleDescription);

    //批量修改状态
    public int updateStatesBatch(List list,Integer states,String KeyList);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
