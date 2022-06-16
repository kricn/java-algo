package Heap;

import java.util.LinkedList;
import java.util.List;

/**
 * 大堆根
 */
public class LargeRoot {

    // 父节点 (n - 1) / 2
    // 左节点位置 2n + 1
    // 右节点位置 2n + 2

    public List<Integer> root;
    public int heapSize;

    public LargeRoot(int[] data) {
        this.root = new LinkedList<Integer>();
        for (int i = 0; i < data.length; i ++) {
            this.root.add(data[i]);
            heapInsert(i);
        }
        this.heapSize = this.root.size();
        heapify(0);
    }
    public LargeRoot() {
        this.root = new LinkedList<Integer>();
        this.heapSize = 0;
    }
    /**
     * 某个数在index能否向下移动，形成大根堆
     * @param index 从哪个下标开始
     */
    public void heapify(int index) {
        int left = 2 * index + 1;  // 左下标节点
        while (left < this.heapSize) {
            // 比较两个子节点
            // left + 1 < heapSize 表示有右节点
            int largest = left + 1 < heapSize && this.root.get(left + 1) > this.root.get(left)
                    ? left + 1 : left;
            // 较大的子节点和父节点比较
            largest = this.root.get(largest) > this.root.get(index) ? largest : index;
            // 较大的子节点没有比父节点大，退出循环
            if (largest == index) break;
            // 交换父子节点
            swap(this.root, largest, index);
            // 更新index的位置
            index = largest;
            // 更新左节点
            left = index * 2 + 1;
        }
    }

    // 某个数在index能否向上移动，形成大根堆
    public void heapInsert(int index) {
        // 判断是否比父节点大
        while (this.root.get(index) > this.root.get((index - 1) / 2)) {
            swap(this.root, index , (index - 1) / 2);
            // 更新节点位置，即更新为父节点的下标
            index = (index - 1) / 2;
        }
    }

    public int pop() {
        swap(this.root, 0, this.heapSize - 1);
        int res = this.root.get(heapSize - 1);
        this.root.remove(heapSize - 1);
        this.heapSize --;
        heapify(0);
        return res;
    }


    public void swap (List<Integer> list, int i, int j) {
        if (i == j) return ;
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public static void main(String[] args) {
        int[] arr = {4,9,7,10,2,3,100,5,12};
        LargeRoot largeRoot = new LargeRoot(arr);
        System.out.println(largeRoot.root);
        while (largeRoot.heapSize != 0) {
            System.out.println(largeRoot.pop());
            System.out.println(largeRoot.root);
        }
    }



}
