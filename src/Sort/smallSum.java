package Sort;

import java.util.Arrays;

/**
 * 小和问题
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫作这个数组的小和
 * 如 [1,3,4,2,5] 1左边比1小的数，没有，3左边比3小的数，1，4左边比4小的数，1，3...以些类推
 * 最后所有数都加起来的各
 * 利用归并排序求解 MergeSort
 */

public class smallSum {

    public static int sum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        // 遍历左右两边的数组
        while (p1 <= M && p2 <= R) {
            // 排序后，在左右合并时，可以快速知道右侧有多少个值比左侧的值大
            // 左侧的值比右侧的值小，则右侧有 R - P2 + 1 个数比左侧的值大，累加上当前arr[p1]的值
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            // 这里遇到相等需要先让右侧的值入help数组，这样才能快速知道右侧有多少个值比左侧大
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 处理右边数组已经遍历完的情况
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        // 处理左边数组已将遍历完的情况
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 最后返回给原数组
        for (i = 0; i < help.length - 1; i ++) {
            arr[L + i] = help[i];  // 这里用 L + i 是因为起点是每个数组的第 0 个值，并不是需要排序数组的第0个
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};

        int res = sum(arr);

        System.out.println(res);
    }

}
