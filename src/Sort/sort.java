package Sort;

import java.util.Arrays;

public class sort {

    public static void main(String[] args) {
        int[] arr = { 2,3,1,32,4,31,23,56, 100 };

        // 插入排序
//        InsertSort.sort(arr);

        // 归并排序
//        MergeSort.sort(arr);

        // 快速排序1
//        QuickSort.sort(arr);

        // 堆排序
//        HeapSort.sort(arr);

        // 基数排序
        RadixSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
