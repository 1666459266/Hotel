package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Role;
import com.hotel.core.entity.UserRole;
import com.hotel.core.entity.Users;
import com.hotel.core.mapper.RoleMapper;
import com.hotel.core.mapper.UserRoleMapper;
import com.hotel.core.mapper.UsersMapper;
import com.hotel.core.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    //添加用户的同时赋予角色和权限
    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "users",key = "#users.id"),
                      @CacheEvict(cacheNames = "users",key = "#users.username"),
                      @CacheEvict(cacheNames = "roleNameByUsername",key = "#users.username"),
                      @CacheEvict(cacheNames = "permissionListByUsername",key = "#users.username"),
                      @CacheEvict(cacheNames = "userList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortAsc",key = "#KeyList")})
    @Override
    public int insertUser(Users users,String KeyList) {
        System.out.println("启用Service...insertUser");
        int i = 0;
        int j = 0;
        try {
            i = usersMapper.insertSelective(users);
            //默认赋予普通用户角色
            Role role = roleMapper.selectByPrimaryKey(3);
            UserRole userRole = new UserRole();
            userRole.setUserId(users.getId());
            userRole.setRoleId(role.getId());
            userRole.setUserName(users.getUsername());
            userRole.setRoleName(role.getRoleName());
            userRole.setRoleDescription(role.getRoleDescription());
            //添加用户角色
            j = userRoleMapper.insertSelective(userRole);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Caching(evict = {@CacheEvict(cacheNames = "users",key = "#users.id"),
                      @CacheEvict(cacheNames = "users",key = "#users.username"),
                      @CacheEvict(cacheNames = "roleNameByUsername",key = "#users.username"),
                      @CacheEvict(cacheNames = "permissionListByUsername",key = "#users.username"),
                      @CacheEvict(cacheNames = "userList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortAsc",key = "#KeyList")})
    @Override
    public int updateUser(Users users,String KeyList) {
        System.out.println("启用Service...updateUser");
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "users",key = "#id"),
                      @CacheEvict(cacheNames = "roleNameByUsername",key = "#username"),
                      @CacheEvict(cacheNames = "permissionListByUsername",key = "#username"),
                      @CacheEvict(cacheNames = "userList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userCounts",key = "#KeyList")})
    @Override
    public int deleteUserById(Integer id,String username,String KeyList) {
        System.out.println("启用Service...deleteUserById");
        int i = 0;
        int j = 0;
        try {
            //删除用户的同时删除角色信息
            i = usersMapper.deleteByPrimaryKey(id);
            j = userRoleMapper.deleteUserRoleByUserId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Cacheable(cacheNames = "users",key = "#id")
    @Override
    public Users selectUsersById(Integer id) {
        System.out.println("启用Service...selectUsersById");
        return usersMapper.selectByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "users",key = "#username")
    @Override
    public Users selectUserByUsername(String username) {
        System.out.println("启用Service...selectUserByUsername");
        return usersMapper.selectUserByUsername(username);
    }

    @Override
    public List<Users> selectUserListByLimit(Map<String, Object> map,String KeyList) {
        System.out.println("启用Service...selectUserListByLimit");
        return usersMapper.selectUserListByLimit(map);
    }

    @Override
    public int selectCounts(Integer states,String KeyList) {
        return usersMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "users",key = "#users.id"),
                      @CacheEvict(cacheNames = "users",key = "#users.username"),
                      @CacheEvict(cacheNames = "roleNameByUsername",key = "#users.username"),
                      @CacheEvict(cacheNames = "permissionListByUsername",key = "#users.username"),
                      @CacheEvict(cacheNames = "userList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortAsc",key = "#KeyList")})
    @Override
    public int updateUserByStates(Users users,String KeyList) {
        System.out.println("启用Service...updateUserByStates");
        return usersMapper.updateUserByStates(users);
    }

    @Override
    public List<Users> searchUser(String username,String phone,String email) {
        System.out.println("启用Service...searchUser");
        return usersMapper.searchUser(username,phone,email);
    }

    @Override
    public List<Users> selectUserListByMembershipLevelSortDesc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserListByMembershipLevelSortDesc");
        return usersMapper.selectUserListByMembershipLevelSortDesc(map);
    }

    @Override
    public List<Users> selectUserListByMembershipLevelSortAsc(Map<String, Object> map, String KeyList) {
        System.out.println("启用Service...selectUserListByMembershipLevelSortAsc");
        return usersMapper.selectUserListByMembershipLevelSortAsc(map);
    }

    @Override
    public List<Users> selectUserListByGender(Map<String, Object> map) {
        System.out.println("启用Service...selectUserListByGender");
        return usersMapper.selectUserListByGender(map);
    }

    @Override
    public List<Users> selectUserListByRole(Map<String, Object> map) {
        System.out.println("启用Service...selectUserListByRole");
        return usersMapper.selectUserListByRole(map);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortAsc",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list,Integer states,String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return usersMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortAsc",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return usersMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "userCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortDesc",key = "#KeyList"),
                      @CacheEvict(cacheNames = "UserListByMembershipLevelSortAsc",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
