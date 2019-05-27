package com.provider5552.suanfa;

import com.provider5552.utils.ArrayUtils;

public class QuickStore {

    public static void main(String[] args) {
        int[] array = ArrayUtils.createArray(12, 50, 6);
        int[] newArray = quickStore(array, 0, array.length - 1);
        showArray(newArray);
    }

    /**
     * 30      * 快速排序
     * 31      * @param array 源数组
     * 32      * @param l
     * 33      * @param r
     * 34      * @return
     * 35
     */
    public static int[] quickStore(int[] array, int l, int r) {
        if (l < r) {
            int i = l, j = r, x = array[l];
            while (i < j) {
                while (i < j && array[j] >= x)
                    // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    array[i++] = array[j];

                while (i < j && array[i] < x)
                    // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    array[j--] = array[i];
            }
            array[i] = x;
            quickStore(array, l, i - 1); // 递归调用
            quickStore(array, i + 1, r);
        }
        return array;
    }

    /**
     * 60      * 显示数组信息
     * 61      * @param array
     * 62
     */
    public static void showArray(int[] array) {
        System.out.println("排序后....");
        for (int i = 0; i < array.length; i++) {
            System.out.println("array[" + i + "] = " + array[i]);
        }
    }

    /**
     * 71      * test
     * 72      * @param args
     * 73
     */
}
