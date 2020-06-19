package com.sort.bubble;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {7, 4, 2, 9, 1, 8, 12, -1, -44, 5};
        print(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
            }
        }


        System.out.println("");
        print(arr);
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
}
