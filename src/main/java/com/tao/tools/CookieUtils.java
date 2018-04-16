package com.tao.tools;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 */
public class CookieUtils {
	/**
	 * 设置cookie值
	 * @param response
	 */
	public static void setCookie(HttpServletResponse response,HashMap<String, String> map){
		for(Entry<String, String> entry:map.entrySet()){
			Cookie cookie=new Cookie(entry.getKey().trim(), entry.getValue().trim());
			cookie.setMaxAge(18000);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	
	}
	 /**
	  * 清除cookie
	  * @param response
	  * @param keyName
	  */
	public static void clearCookie(HttpServletRequest request,HttpServletResponse response,String[] keyName){
		Cookie[] cookies= request.getCookies();
		if(cookies == null){
			return;
		}
		for(int i=0;i<keyName.length;i++){
			if (cookies.length>0) {
				for (Cookie cookie : cookies) {
					if(cookie != null && keyName[i].equals(cookie.getName())){
						cookie.setValue(null);
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			}
			
		}
	
	}

	/**
	 * 获取value
	 * @param request
	 * @param keyName
	 * @return
	 */
	public static String getValueByKey(HttpServletRequest request,String keyName){
		Cookie[] cookies= request.getCookies();
		String value=null;
		for(Cookie cookie:cookies){
			if(cookie != null && keyName.equals(cookie.getName())){
				value=cookie.getValue();
				break;
			}
		}
		return value;
	}
}
