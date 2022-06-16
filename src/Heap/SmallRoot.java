package Heap;

import java.util.LinkedList;
import java.util.List;

/**
 * 小根堆
 */
public class SmallRoot {

    // 父节点 (n - 1) / 2
    // 左节点位置 2n + 1
    // 右节点位置 2n + 2

    public List<Integer> root;
    public int heapSize;

    public SmallRoot(int[] data) {
        this.root = new LinkedList<Integer>();
        for (int i = 0; i < data.length; i ++) {
            this.root.add(data[i]);
            heapInsert(i);
        }
        this.heapSize = this.root.size();
        heapify(0);
    }
    public SmallRoot() {
        this.root = new LinkedList<Integer>();
        this.heapSize = 0;
    }

    /**
     * 某个数在index能否向下移动，形成小根堆
     * @param index 从哪个下标开始
     */
    public void heapify(int index) {
        int left = 2 * index + 1;  // 左下标节点
        while (left < this.heapSize) {
            // 比较两个子节点
            // left + 1 < heapSize 表示有右节点
            int smallest = left + 1 < heapSize && this.root.get(left + 1) < this.root.get(left)
                    ? left + 1 : left;
            // 较小的子节点和父节点比较
            smallest = this.root.get(smallest) < this.root.get(index) ? smallest : index;
            // 较小的子节点没有比父节点小，退出循环
            if (smallest == index) break;
            // 交换父子节点
            swap(this.root, smallest, index);
            // 更新index的位置
            index = smallest;
            // 更新左节点
            left = index * 2 + 1;
        }
    }

    // 某个数在index能否向上移动，形成小根堆
    public void heapInsert(int index) {
        // 判断是否比父节点小
        while (this.root.get(index) < this.root.get((index - 1) / 2)) {
            swap(this.root, index , (index - 1) / 2);
            // 更新节点位置，即更新为父节点的下标
            index = (index - 1) / 2;
        }
    }

    public void swap (List<Integer> list, int i, int j) {
        if (i == j) return ;
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public int pop() {
        swap(this.root, 0, this.heapSize - 1);
        int res = this.root.get(heapSize - 1);
        this.root.remove(heapSize - 1);
        this.heapSize --;
        heapify(0);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,9,7,10,2,3,100,5,12};
        SmallRoot smallRoot = new SmallRoot(arr);
        System.out.println(smallRoot.root);
        while (smallRoot.heapSize != 0) {
            System.out.println(smallRoot.pop());
            System.out.println(smallRoot.root);
        }
    }
}
