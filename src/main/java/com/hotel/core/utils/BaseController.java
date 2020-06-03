package com.hotel.core.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	
	public static final Integer FLAG_SUCCESS = 0;// 请求响应成功
	public static final Integer FLAG_FAILED = 500;// 请求响应失败
	public static final Integer FLAG_NO_PERMISSION = 1000;// 请求无权限

	public static final String KEYLIST = "KeyList";//正常列表的key
	public static final String RECYCLEKEYLIST = "RecycleKeyList";//回收列表的key
	public static final String LOCKINGKEYLIST = "LockingKeyList";//锁定列表的key

	public Map<String, Object> getMap(){
		return new HashMap<String, Object>();
	}
	
	public String getParam(HttpServletRequest request, String name){
		return request.getParameter(name);
	}
	
	public void setSession(HttpServletRequest request, String name, Object sessionObj) {
		request.getSession().setAttribute(name, sessionObj);
	}

}
