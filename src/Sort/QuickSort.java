package Sort;

public class QuickSort {

    // 主程序调用
    public static void mainSort (int[] arr, int L, int R) {
        if (L < R) {
            Unit.swap(arr, L + (int)(Math.random()*(R - L + 1)), R);
            int[] p = partition(arr, L, R);
            mainSort(arr, L, p[0] -1);
            mainSort(arr, p[1] + 1, R);
        }
    }

    public static void sort(int[] arr) {
        mainSort(arr, 0, arr.length - 1);
    }

    // 对数组作分割
    // 荷兰国旗问题变式
    public static int[] partition(int[] arr, int L, int R) {
        // 小分区
        int less = L - 1;
        // 大分区，以最后一个数为基数
        int more = R;
        // 以大小分区的指针是否重合为结束条件
        while (L < more) {
            // 小分区变大，指针往前移动
            if (arr[L] < arr[R]) {
                Unit.swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) { // 大分区变大，指针不动，因为交换后， L 位置的值是一个新的值，需要重新比较一遍
                Unit.swap(arr, --more, L);
            } else { // 相等则不动
                L ++;
            }
        }
        Unit.swap(arr, more, R);
        return new int[] { less + 1, more };
    }

}
