package Sort;

// 归并排序
public class MergeSort {

    // 二分排序函数
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return ;
        }
        int mid = L + ((R - L) >> 1);  // 为什么不直接 L + R / 2, 因为这样数组可能会越界
        process(arr, L, mid);
        process(arr, mid + 1, R);
        // 合并二分后的数组
        merge(arr, L, mid, R);
    }

    // 合并数组
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        // 遍历左右两边的数组
        while (p1 <= M && p2 <= R) {
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
    }

    // 排序函数
    public static void sort(int[] arr ) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        process(arr, 0, arr.length - 1);
    }
}
