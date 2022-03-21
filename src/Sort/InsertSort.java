package Sort;

public class InsertSort {

    // 插入排序，时间复杂度为O(N^2) 特殊情况是O(N)
    public static  void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        for (int i = 1; i < arr.length; i ++) {
            // 前一个数比后一个数大，则交换两个数的位置
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j --) {
                Unit.swap(arr, j, j + 1);
            }
        }
    }

}
