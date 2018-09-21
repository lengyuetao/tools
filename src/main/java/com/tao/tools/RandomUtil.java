package com.tao.tools;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机工具类
 *
 * @author cloud cloud
 * @create 2017/10/11
 **/
public class RandomUtil {
    public final static  String CHARS = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
    private final static String NUMBERS = "0123456789";

    public static String getSerialNo(){
        return getSerialNo("");
    }
    public static String getSerialNo(String prefix){
        return getSerialNo(prefix,25+prefix.length());
    }

    public static String getSerialNo(String prefix,int length){
        int restLength = length;
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotEmpty(prefix)){
            sb.append(prefix);
            restLength = length - prefix.length();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        sb.append(sdf.format(new Date()));
        restLength -= 15;
        if(restLength < 0){
            restLength = 10;
        }
        Random random = new Random();
        for(int i=0;i<restLength;i++){
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
    
	/**
	 * 根据length获取随机数
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(int length){
		StringBuilder sb=new StringBuilder();
		Random rand=new Random();
		for(int i=0;i<length;i++){
			sb.append(NUMBERS.charAt(rand.nextInt(NUMBERS.length())));
		}
		return sb.toString();
	}

	/**
	 * 随机产生指定位数的字符（大小写字母和数字）
	 * @param length
	 * @return
	 */
	public static String getRandomChars(int length){
		StringBuilder sb=new StringBuilder();
		Random rand=new Random();
		for(int i=0;i<length;i++){
			sb.append(CHARS.charAt(rand.nextInt(CHARS.length())));
		}
		return sb.toString();
	}

    public static void main(String[] args) {
        byte a = 5;
        System.out.println(a);
    }
}
