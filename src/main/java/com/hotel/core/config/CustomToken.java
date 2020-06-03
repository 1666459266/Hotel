package com.hotel.core.config;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * 重写UsernamePasswordToken
 * @ClassName: UsernamePasswordToken
 * @author zlxls
 * @date 2020年04月11日
 */
public class CustomToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -2564928913725078138L;
    private LoginType type;
    public CustomToken() {
        super();
    }
    public CustomToken(String username, String password, LoginType type, boolean rememberMe, String host) {
        super(username, password, rememberMe,  host);
        this.type = type;
    }
    public LoginType getType() {
        return type;
    }
    public void setType(LoginType type) {
        this.type = type;
    }
    /**
     * 免密登录
     * @param username
     */
    public CustomToken(String username) {
        super(username, "", false, null);
        this.type = LoginType.NOPASSWD;
    }

    /**
     * 账号密码登录
     * @param username
     * @param pwd
     */
    public CustomToken(String username, String pwd) {
        super(username, pwd, false, null);
        this.type = LoginType.PASSWORD;
    }
}