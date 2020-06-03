package com.hotel.core.config;

import com.hotel.core.web.shiro.FilterChainDefinitionMapBuilder;
import com.hotel.core.web.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Configuration
public class ShiroConfig {

    @Autowired
    private FilterChainDefinitionMapBuilder filterChainDefinitionMapBuilder;

    //配置securityManager
//    @Bean
//    public DefaultWebSecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //注入realm
//        securityManager.setRealm(realm());
//        //注入rememberMeCookie
//        securityManager.setRememberMeManager(rememberMeManager());
//        return securityManager;
//    }

    //自定义realm
//    @Bean
//    public ShiroRealm realm(){
//        ShiroRealm realm = new ShiroRealm();
//        realm.setCredentialsMatcher(credentialsMatcher());
//        return realm;
//    }

    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("myRetryLimitCredentialsMatcher") MyRetryLimitCredentialsMatcher matcher,RememberMeManager manager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(manager);//记住Cookie
        securityManager.setRealm(myShiroRealm(matcher));
        return securityManager;
    }
    @Bean
    public ShiroRealm myShiroRealm(MyRetryLimitCredentialsMatcher matcher){
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }
    @Bean(name = "myRetryLimitCredentialsMatcher")
    public MyRetryLimitCredentialsMatcher hashedCredentialsMatcher() {
        MyRetryLimitCredentialsMatcher hashedCredentialsMatcher = new MyRetryLimitCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }
    //加密算法
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    //配置ShiroFilter
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        shiroFilterChainDefinition.addPathDefinitions(filterChainDefinitionMapBuilder.builder());
        return shiroFilterChainDefinition;
    }

    //cookie
    private SimpleCookie cookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //cookie生效时间 单位秒
        cookie.setMaxAge(2592000);
        return cookie;
    }

    //设置记住我管理器
    private CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        //设置cookie
        rememberMeManager.setCookie(cookie());
        //加密
        String encryptKey = "febs_shiro_key";
        byte[] encryptKeyBytes = encryptKey.getBytes(StandardCharsets.UTF_8);
        String rememberKey = Base64Utils.encodeToString(Arrays.copyOf(encryptKeyBytes,16));
        rememberMeManager.setCipherKey(Base64.decode(rememberKey));
        return rememberMeManager;
    }

    //开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
    //配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    //开启aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
