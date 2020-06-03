package com.hotel.core.web.shiro;

import com.hotel.core.entity.Users;
import com.hotel.core.service.RoleService;
import com.hotel.core.service.UsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    /**
     * Spring Boot 整合Shiro和Redis关于@Cacheble注解无效的解决方法
     * @Lazy 注入到Shiro框架的Bean延时加载
     */
    @Autowired
    @Lazy
    private UsersService usersService;
    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * 登录认证
     * 执行currentUser.login(token)方法时调用
     * @param authenticationToken 传入的参数是UsernamePasswordToken
     * @return authenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //得到输入的用户名
        String username = usernamePasswordToken.getUsername();
        //根据用户名查询用户
        Users users = usersService.selectUserByUsername(username);
        //如果用户不存在 抛出UnknownAccountException异常
        if (users == null) {
            throw new UnknownAccountException("用户不存在");
        }
        //如果用户被锁定 抛出LockedAccountException异常
        if (users.getStates() == 2){
            throw new LockedAccountException("用户被回收");
        }
        if (users.getStates() == 3){
            throw new LockedAccountException("用户被锁定");
        }
        Object principal = username;//数据库中的用户名
        Object credential = users.getPassword();//数据库中的密码
        ByteSource byteSource = ByteSource.Util.bytes(username);//加盐的主体
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,credential,byteSource,super.getName());//super.getName() realmName名称
        return authenticationInfo;
    }

    /**
     * 登录授权
     * 当访问需要角色权限时调用 如果已对某个用户授权就不会调用二次调用
     * @param principalCollection 传入的参数是用户的主体信息（用户名）
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //得到用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询拥有那些角色
        Set<String> roles = roleService.selectRoleNameByUsername(username);
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
    }

}
