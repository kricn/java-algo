package Sort;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSort {

    // 父节点 (n - 1) / 2
    // 左节点位置 2n + 1
    // 右节点位置 2n + 2

    // 某个数在index能否向上移动，形成大根堆
    public static void heapInsert(int[] arr, int index) {
        // 判断是否比父节点大
        while (arr[index] > arr[(index - 1) / 2]) {
            Unit.swap(arr, index , (index - 1) / 2);
            // 更新节点位置，即更新为父节点的下标
            index = (index - 1) / 2;
        }
    }

    // 某个数在index位置，能否向下移动，形成大根堆
    public static void heapify (int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;  // 左节点下标
        while(left < heapSize) { // 还有子节点的时候
            // 比较两个子节点
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                            ? left + 1 : left;
            // 较大的子节点和父节点比较
            largest = arr[largest] > arr[index] ? largest : index;
            // 较大的子节点没有比父节点大，退出循环
            if (largest == index) break;
            // 交换父子节点
            Unit.swap(arr, largest, index);
            // 更新index的位置
            index = largest;
            // 更新左节点
            left = index * 2 + 1;
        }
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        for (int i = 0; i < arr.length; i ++) { // O(N)
            // 生成大根堆
            heapInsert(arr, i); // O(logN)
        }
        // 记录堆的高度
        int heapSize = arr.length;
        // 交换 0 位置上的数和堆顶的数，这样最大的就排在了最后
        // 同时减少 heapSize
        Unit.swap(arr, 0, --heapSize);
        // 当堆的高度不为0时，从 0 位置开始向下移动形成大根堆
        while(heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize);  // O(logN)
            Unit.swap(arr, 0, --heapSize); // O(1)
        }
    }

    /**
     * 已知一个几乎有序的数组，几乎有序是指，如果把数组排号，每个元素移动的距离可以不操作k，并且k相对于数组来说比较小，
     * 请选择一个合适的算法针对这个数组进行排序
     */
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        // 使用java中的优先队列，形成小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for(; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for(; index < arr.length; i ++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while(!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        int[] testExampleArr = {9,2,199,3,44,0};
        sortedArrDistanceLessK(testExampleArr, 4);
        System.out.println(Arrays.toString(testExampleArr));
    }
}
