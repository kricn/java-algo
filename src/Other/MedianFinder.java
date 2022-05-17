package Other;

import java.util.PriorityQueue;

/**
 * 从一个数据流中，获取其中位数
 * 实现一个 addNum 的方法，可以添加整数到数据流中
 * 实现一个 double findMedian 方法，返回当前所有元素的中位数
 */
public class MedianFinder {

    // 大根堆，存放数据流中大的一半数据
    public PriorityQueue<Integer> maxQueue;
    // 大根堆，存放数据流中小的一半数据
    public PriorityQueue<Integer> minQueue;

    public MedianFinder() {
        maxQueue = new PriorityQueue<Integer>((x, y) -> (y - x));
        minQueue = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        // 两个堆为空时，先入大根堆
        if (maxQueue.isEmpty() && minQueue.isEmpty()) {
            maxQueue.add(num);
            return ;
        }
        int maxEle = maxQueue.peek();
        // 添加的数字小于等于大根堆顶时，入大根堆
        if (num <= maxEle) {
            maxQueue.add(num);
        } else {
            minQueue.add(num);
        }
        // 两个堆大小相差为2时，容量大的堆堆顶弹出去另一个堆
        if (maxQueue.size() - minQueue.size() == 2) {
            minQueue.add(maxQueue.poll());
        }
        if (minQueue.size() - maxQueue.size() == 2) {
            maxQueue.add(minQueue.poll());
        }
    }

    public double findMedian() {
        if (maxQueue.size() - minQueue.size() == 0) {
            return (maxQueue.peek() + minQueue.peek()) / 2.0;
        }
        return maxQueue.size() > minQueue.size() ? maxQueue.peek() : minQueue.peek();
    }

    public static void main(String[] args) {
        int[] nums = {2,7,4,5,9,10,44,22,8};
        MedianFinder finder = new MedianFinder();
        for (int i : nums) {
            finder.addNum(i);
            System.out.printf("中位数是 %s \n", finder.findMedian());
        }
    }

}
