package com.tao.tools.algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FutuerTest {

    public static void main(String[] args) {

        List<String> list=new ArrayList<>();

        for (int i=0;i<10;i++){
            list.add("aaa"+i);
        }



        //转化成大写
        List<String> upList=list.stream().map(String::toUpperCase).collect(Collectors.toList());

        //遍历
        upList.stream().forEach(System.out::println);


        Integer[] num=new Integer[]{23,2,4,52,235};

        List<Integer> nums=Arrays.asList(num);

        List<Integer> splist=nums.stream().map(n->n*n).collect(Collectors.toList());

        //遍历
        splist.stream().forEach(System.out::println);


        Objects.requireNonNull(list,"测试");

    }
}
