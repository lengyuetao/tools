package com.tao.tools.security;

import java.util.UUID;

public class RandomUtils {

    /**
     * 生成32位随机字符串
     * @return
     */
    public static String getRandom(){
        String str= UUID.randomUUID().toString().replace("-","");
        return str;
    }

    public static void main(String[] args) {
        System.out.println(getRandom());

    }
}
