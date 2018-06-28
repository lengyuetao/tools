package com.tao.tools.algorithm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tao.tools.http.HttpUtil;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 抽奖算法
 */
public class Lottery {

    public static void main(String[] args) {

        Set<BigInteger> set=new LinkedHashSet<>();

        int height=687500;
        BigInteger h=null;
        while(set.size()<66){
            String res= HttpUtil.get("http://47.75.45.95:25884/api/v1/block/details/height/"+height);
            JSONObject json= JSON.parseObject(res);
            if(json.get("Desc").equals("SUCCESS")&&json.getInteger("Error")==0){
                String hash=json.getJSONObject("Result").getString("Hash");

                BigInteger k=math(hash);

                if(!set.contains(k)){
                    set.add(k);
                    System.out.println("高度："+height+"\t序号："+k);
                }
            }

            height=height+1;
        }

        System.out.println(set);
    }


    public static BigInteger math(String hash){
        BigInteger b = new BigInteger(hash,16);
        BigInteger a=new BigInteger("138");

        return b.mod(a).add(new BigInteger("1"));

    }
}
