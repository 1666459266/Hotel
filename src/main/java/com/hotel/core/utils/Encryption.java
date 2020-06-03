package com.hotel.core.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Encryption {

    /**
     * @param username 用户名
     * @param credentials 加密内容
     * @return 加密结果
     */
    public static Object build(String username,String credentials){
        //加密算法
        String hashAlgorithmName = "SHA-256";
        //盐值
        Object salt = ByteSource.Util.bytes(username);
        //加密次数
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
        return result;
    }

}
