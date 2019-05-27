package com.provider5552.suanfa;

import com.provider5552.utils.ArrayUtils;

public class Bubble {
    public static void main(String[] args) {
        int[] nums =  ArrayUtils.createArray(0,0,10);
        bubble(nums);

    }

    //冒泡排序 内外两层循环 互换位置
    public static void bubble(int[] nums) {
        int temp = 0;
        for (int j = 0; j < nums.length - 1; j++) {
            for (int i = 0; i < nums.length - j - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    temp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = temp;
                }
            }
            System.out.print("第"+(j+1)+"轮排序结果:");
            for (int num : nums) {
                System.out.print(num + "-");
            }
            System.out.println();
        }
    }
}
