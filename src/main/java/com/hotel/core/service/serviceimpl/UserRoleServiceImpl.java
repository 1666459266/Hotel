package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Role;
import com.hotel.core.entity.UserRole;
import com.hotel.core.entity.Users;
import com.hotel.core.mapper.RoleMapper;
import com.hotel.core.mapper.UserRoleMapper;
import com.hotel.core.mapper.UsersMapper;
import com.hotel.core.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "userRole",key = "#userRole.id"),
                      @CacheEvict(cacheNames = "userRoleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoleCounts",key = "#KeyList")})
    @Override
    public int insertUserRole(UserRole userRole, String KeyList) {
        System.out.println("启用Service...insertUserRole");
        //根据传入的username和roleId查询用户和角色
        Users users = usersMapper.selectUserByUsername(userRole.getUserName());
        Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
        //存入用户角色
        userRole.setUserId(users.getId());
        userRole.setRoleId(role.getId());
        userRole.setUserName(users.getUsername());
        userRole.setRoleName(role.getRoleName());
        userRole.setRoleDescription(role.getRoleDescription());
        return userRoleMapper.insertSelective(userRole);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRole",key = "#userRole.id"),
                      @CacheEvict(cacheNames = "userRoleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoleCounts",key = "#KeyList")})
    @Override
    public int updateUserRole(UserRole userRole, String KeyList) {
        System.out.println("启用Service...updateUserRole");
        //根据传入的username和roleId查询用户和角色
        Users users = usersMapper.selectUserByUsername(userRole.getUserName());
        Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
        //存入用户角色
        userRole.setUserId(users.getId());
        userRole.setRoleId(role.getId());
        userRole.setUserName(users.getUsername());
        userRole.setRoleName(role.getRoleName());
        userRole.setRoleDescription(role.getRoleDescription());
        return userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRole",key = "#id"),
                      @CacheEvict(cacheNames = "userRoleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoleCounts",key = "#KeyList")})
    @Override
    public int deleteUserRoleById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteUserRoleById");
        return userRoleMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "userRole",key = "#id")
    @Override
    public UserRole selectUserRoleById(Integer id) {
        System.out.println("启用Service...selectUserRoleById");
        return userRoleMapper.selectByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "userRoleList",key = "#KeyList")
    @Override
    public List<UserRole> selectUserRoleList(String KeyList) {
        System.out.println("启用Service...selectUserRoleList");
        return userRoleMapper.selectUserRoleList();
    }

    @Cacheable(cacheNames = "userRoleCounts",key = "#KeyList")
    @Override
    public int selectCounts(String KeyList) {
        System.out.println("启用Service...selectCounts");
        return userRoleMapper.selectCounts();
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRoleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoleCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return userRoleMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userRoleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userRoleCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
