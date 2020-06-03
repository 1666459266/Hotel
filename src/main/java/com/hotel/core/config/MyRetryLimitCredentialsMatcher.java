package com.hotel.core.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Configuration;

/**
 * @author shanjianfei
 * @create 2020-05-25 18:51
 */

public class MyRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        CustomToken tk = (CustomToken) authcToken;
        if(tk.getType().equals(LoginType.NOPASSWD)){
            return true;
        }
        boolean matches = super.doCredentialsMatch(authcToken, info);
        return matches;
    }
}
