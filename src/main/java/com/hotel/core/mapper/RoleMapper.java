package com.hotel.core.mapper;

import com.hotel.core.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Role role);

    int insertSelective(Role role);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role role);

    int updateByPrimaryKey(Role role);

    //根据用户名查询用户角色
    @Select("SELECT r.role_name FROM users u" +
            " LEFT JOIN user_role ur ON u.id = ur.user_id" +
            " LEFT JOIN role r ON r.id = ur.role_id" +
            " WHERE u.username = #{username} and role_states = 1")
    Set<String> selectRoleNameByUsername(String username);

    //获取角色分页列表
    List<Role> selectRoleListByLimit(Map<String,Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改角色状态
    int updateRoleByStates(Role role);

    //搜索角色（模糊查询）
    List<Role> searchRole(String roleName,String roleDescription);

    //批量修改状态
    int updateStatesBatch(List list,Integer states);

    //批量删除
    int deleteBatch(List list);

}