package Greedy;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，需要花费和长度数值一橷的铜板。比如长度为 20 的金条，不管切成多大长度的两半，都要花费 20 个铜板
 * 一群人想分整块金条，怎么分最省铜板
 * 例如，给定数组 {10， 20， 30}, 代表一共三个人，整块金条长度为 60
 * 金条要分成 10, 20, 30 三个部分，如果先把长度 60 的金条分成 10 和 50，再把 50 分成 30 和 20，一共花费 110 个铜板
 * 但是如果先把长度为 60 的金条分成 30 和 30，再把 30 分成 10 和 20，则花费了 90 铜板
 * 求：输入一个数组，返回分割的最小代价
 */
public class Gold {

    public static int lessMoney(int[] arr) {
        // 将数组成小根堆排序
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i ++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        // 每次弹出两个，将结果再次压入小根堆
        // 最后小根堆会剩下一个数，跳出循环
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

}
