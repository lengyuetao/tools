package com.tao.tools.algorithm;

/**
 * 冒泡排序
 */
public class Algorithm {
    public static void main(String[] args) {
        int[] arr=new int[]{23,3,1,21,20,4,2,234};
        int temp;//临时变量
        boolean flag;//是否交换的标志
        for(int i=0; i<arr.length-1; i++){   //表示趟数，一共arr.length-1次。

            flag = false;
            for(int j=arr.length-1; j>i; j--){

                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    flag = true;
                }
            }
            if(!flag) break;
        }

        for(int a:arr){
            System.out.println(a);
        }
    }
}
