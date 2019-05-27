package com.provider5552.utils;

public class ArrayUtils {

    public static int[] createArray(int min, int max, int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = min + (int) (Math.random() * (max - min + 1));
            System.out.println("随机数 ：array[" + i + "] = " + array[i]);
        }
        return array;
    }
}
