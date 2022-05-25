package Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：
 * 正整数组 costs
 * 正整数组 profits
 * 整数 k
 * 整数 m
 * 含义：
 * costs[i] 表示 i 项目的花费
 * profits[i] 表示 i 项目在扣除花费后的纯利润
 * k 表示你只能串行的最多做 k 个项目
 * m 表示你的启动资金
 * 说明：
 * 你每做完一个项目，马上获得收益，可以去做下一个项目
 * 输出：
 * 你最后获得的最大钱数
 */

/**
 * 贪心策略：
 * 以花费为准生成小根堆，以收益为准生成小根堆
 * 当可支配金额改变时，逐渐解锁项目，从花费的小根堆中取出项目到收益的小根堆
 */
public class FindMaxCapital {

    public static class Node {
        public int p;  // 利润
        public int c;  // 花费

        public Node (int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.p - o2.p;
        }
    }

    public static int findMaxCapital (int k, int W, int[] Profits, int[] Capital) {
        // 以花费为基础的小根堆
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        // 以收益为基础的小根堆
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        // 所有项目放入未解锁池中（初始项目不可选择），花费组织的小根堆
        for (int i = 0; i < Profits.length; i ++) {
            minCostQ.add(new Node(Profits[i], Capital[i]));
        }
        // 进行了 k 轮
        for (int i = 0; i <k; i ++) {
            // 能力所级的项目就解锁
            // 花费队列不为空且最小值小于等于我的启动资金
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;  // 进行不了 K 轮，启动资金不再支持继续投资
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }

}
