package Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 普里姆算法求最小生成树
 */
public class Prim {

    // 权重比较器
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        // 将解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        // 已经走过的点，若 to 点在这个 set 中，则说明这条边成环了
        HashSet<Node> set = new HashSet<>();

        // 依次挑选的边放在 result 里
        Set<Edge> result = new HashSet<>();

        for(Node node : graph.nodes.values()) { // 随便挑选一个点，这里循环是避免有一部分点是连通的，另一部分点也是连通的，但这两部分点之间并不连通
            // 当前的 node 作为起点时
            if(!set.contains(node)) {
                // 加入堆中
                set.add(node);
                // 将起点的把的边加入到优先队列里
                // 由一个点解锁所有相连的边
                priorityQueue.addAll(node.edges);
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出最小权重的边
                    Node toNode = edge.to;
                    if (!set.contains(toNode)) { // set 中不含有新的点时，则该点是新的点
                        set.add(toNode);
                        result.add(edge);
                        // 将新点解锁的边再次加入到队列中
                        priorityQueue.addAll(toNode.edges);
                    }
                }
            }
        }

        return result;
    }

}
