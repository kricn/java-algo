package Sort;

public class Unit {

    // 交换数组中两个数的值
    public static void swap(int[] arr, int i, int j) {
        if (i == j) return ;
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

}
