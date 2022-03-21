package Sort;

public class QuickSort {

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

    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                Unit.swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                Unit.swap(arr, --more, L);
            } else {
                L ++;
            }
        }
        Unit.swap(arr, more, R);
        return new int[] { less + 1, more };
    }

}
