package com.sort.selection;

import org.junit.Test;

import java.util.Random;

public class Selection {

    public static void main(String[] args) {
        int[] arr = {7, 4, 2, 9, 1, 8, 12, -1, -44, 5};
        print(arr);

//        sortBySelection(arr);
//        optSelection1(arr);
//        optSelection2(arr);
        optSelection3(arr);

        print(arr);


    }

    @Test
    public void test(){
        int length = 10000;    // 上万级别
        int[] arr = new int[length];
        for (int index = 0; index < length; index++) {
            arr[index] = new Random().nextInt(length) + 1;
        }
        int[] arr2 = new int[length];
        System.arraycopy(arr, 0, arr2, 0, length);  // 数组复制的最优选择

        long start = System.currentTimeMillis();
        optSelection2(arr);
        long end = System.currentTimeMillis();
        System.out.println("sort()耗费时间：" + (end - start) + "ms");
        assert isSort(arr);

        start = System.currentTimeMillis();
        optSelection3(arr2);
        end = System.currentTimeMillis();
        System.out.println("sortPlus()耗费时间：" + (end - start) + "ms");
        assert isSort(arr);
    }

    /**
     * 选择排序：
     * 拿数组中的每个元素与后一个元素比，记录最小的，最后交换位置，直至比较到最后一个
     *
     * @param arr 需要排序的数组
     */
    private static void sortBySelection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;
            for (int k = i + 1; k < arr.length; k++) {
                minPos = arr[k] < arr[minPos] ? k : minPos;
            }
            swap(arr, i, minPos);
        }
    }

    /**
     * 选择排序-优化1：
     * 数组中最后一个没有下一个可以比较的，少循环一次
     *
     * @param arr 需要排序的数组
     */
    private static void optSelection1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int k = i + 1; k < arr.length; k++) {
                minPos = arr[k] < arr[minPos] ? k : minPos;
            }
            swap(arr, i, minPos);
        }
    }

    /**
     * 选择排序-优化2：
     * 如果需要交换的指针不相同才交换，相同则不交换
     *
     * @param arr 需要排序的数组
     */
    private static void optSelection2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int k = i + 1; k < arr.length; k++) {
                minPos = arr[k] < arr[minPos] ? k : minPos;
            }
            if (minPos != i)
                swap(arr, i, minPos);
        }
    }

    /**
     * 选择排序-优化3：发现效率没什么提升
     * 每一次比较，即找出最小值也找出最大值
     *
     * @param arr 需要排序的数组
     */
    private static void optSelection3(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int minPos = i;
            int maxPos = i;
            for (int k = i + 1; k < arr.length - i; k++) {
                minPos = arr[k] < arr[minPos] ? k : minPos;
                maxPos = arr[k] > arr[maxPos] ? k : maxPos;
            }
            if (minPos != i)
                swap(arr, i, minPos);
            if (maxPos != arr.length - i - 1) {
                if (maxPos == i)    //此处很重要,最大值可能在设置最小值的时候被交换,导致数据错误
                    maxPos = minPos;
                swap(arr, arr.length - i - 1, maxPos);
            }
        }
    }


    /**
     * 交换数组中的元素位置
     *
     * @param arr
     * @param i
     * @param minPos
     */
    private static void swap(int[] arr, int i, int minPos) {
        int temp = arr[i];
        arr[i] = arr[minPos];
        arr[minPos] = temp;
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 判断数组是否有序
     * @param arr 待判断数组
     * @return 若数组有序，返回 true，否则返回 false
     */
    public static boolean isSort(int[] arr) {
        for (int index = 1; index < arr.length; index++) {
            if (arr[index] < arr[index - 1]) {
                return false;
            }
        }
        return true;
    }


}
