package com.tao.tools;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 网络工具类
 *
 *
 **/
public class WebUtil {

    public static String getClientIP(HttpServletRequest request){
        String ipString ="";
        try{
            if (request == null) {
                return ipString;
            }
            ipString = request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("WL-Proxy-Client-IP");
            }
            if(StringUtils.isBlank(ipString) || "X-Real-IP".equalsIgnoreCase(ipString)){
                ipString = request.getHeader("X-Real-IP");
            }
            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getRemoteAddr();
            }
            // 多个路由时，取第一个非unknown的ip
            final String[] arr = ipString.split(",");
            for (final String str : arr) {
                if (!"unknown".equalsIgnoreCase(str)) {
                    ipString = str;
                    break;
                }
            }
        }catch(Exception ex){

            ex.toString();
        }

        return ipString;
    }

}
