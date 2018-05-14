package com.tao.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtils {

    private static String hexStr =  "0123456789ABCDEF";  //全局

    public static String BinaryToHexString(byte[] bytes){

        String result = "";
        String hex = "";
        for(int i=0;i<bytes.length;i++){
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));
            result +=hex;
        }
        return result;
    }
    /**
     *
     * @param hexString
     * @return 将十六进制转换为字节数组
     */
    public static byte[] HexStringToBinary(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位

        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }

    /**
     * 替换手机号4-7位为星*号
     * @param phone
     * @return
     */
    public static String formatPhone(String phone){
        return  phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }


    /**
     * 转码
     * @param str
     * @return
     */
    public static String transCode(String str){
        return transCode(str,"UTF-8");
    }

    public static String transCode(String str,String charset){
        try {
            if(org.apache.commons.lang.StringUtils.isEmpty(str)){
                return null;
            }
            return new String(str.getBytes("iso8859-1"),charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static void main(String[] args) {
        DecimalFormat b=new DecimalFormat("00000");
        System.out.println(b.format(31));

        String content = "NIUBCoin系统用户账号注册:{}";

        System.out.println(String.format(content,1));
    }
}
