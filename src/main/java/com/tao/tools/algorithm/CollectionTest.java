package com.tao.tools.algorithm;

import java.util.*;

public class CollectionTest {
    public static void main(String[] args) {
        HashSet<String> set=new HashSet<>();
        set.add("ss");

        List<String> list=new ArrayList<>();
        list.add("bb");

        int a=12>>1;
        int b=12>>2;
        int c=12>>3;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        TreeMap<String,String> t=new TreeMap<>();

        t.put("aaa","酷酷酷");

        int bb=12&5;
        System.out.println(bb);
    }
}
