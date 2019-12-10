package com.test;

import java.util.Arrays;

/**
 * 插入算法排序
 * **/
public class InsertionSoft {

   public static void sort(int[] args){
      for(int i=0;i<args.length;i++){
         int insertValue = args[i];
         int j = i-1;
         //向右比较元素的同时进行元素复制
         for(;j>=0&&insertValue<args[j];j--){
            args[j+1] = args[j];

         }
         //insertValue 的值插入到适当的位置
         args[j+1]=insertValue;

      }
   }

   public static void main(String[] args){
       int array[] = {12,2,4,5,2,-1,45,23};
       sort(array);
      System.out.println(Arrays.toString(array));
   }
}
