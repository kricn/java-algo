package Sort;

public class RadixSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // 获取最大值是几个数
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i ++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while(max != 0) {
            res ++;
            max /= 10;
        }
        return res;
    }

    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数就准备多少个辅助空间
        int[] bucket = new int [R - L + 1];
        for (int d = 1; d <= digit; d++) { // 有多少位就进出多少次
            // 10 个空间
            // count[i] 磁片 表示当前位是(0~i)的数字有多少个
            int[] count = new int[radix];
            // 获取到对应位置的数并放入磁片中
            // 相当于入桶
            for (i = L; i <= R; i ++) {
                j = getDigit(arr[i], d);
                count[j] ++;
            }
            // 当前磁片的值叠加上一个磁片的值，表示当前磁片小于i的数字有几个
            // 如 d = 1, count[5] = 7 表示个位数<=5的数有7个
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 数组从右往左遍历，相当于出桶
            for (i = R; i >= L; i --) {
                j = getDigit(arr[i], d);
                // 将对应位的数字放入辅助数组中
                // 如 d = 1, count[5] = 7，则将 arr[i] 放入 bucket 的第 6 位中
                // 对应磁片(5)上的值减少1
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            // 将辅助数组的值赋值给原数组
            for(i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[i];
            }
        }
    }

    // 获取第几位数的值
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
}
