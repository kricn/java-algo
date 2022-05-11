package Graph;

import java.util.*;

/**
 * 最小树生成树（克鲁斯卡尔算法）
 * 适合无向图
 * 将连通网中所有的边按照权值大小做升序排序，从权值最小的边开始选择，只要此边不和已选择的边一起构成环路，就可以选择它组成最小生成树
 * 难点：如何判断选择了这条边后图已成环
 * 可以使用并查集判断是否成环（但这里并没有用到并查集，而是模拟）
 */
public class Kruskal {

    public static class MySet {
        public HashMap<Node, List<Node>> setMap;

        // 初始化点集合，每个点只有只自己
        public MySet(List<Node> nodes) {
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        // 判断两个点是否在同一个集合内
        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            // 两个点的内存地址一样即为在同一个集合中
            return fromSet == toSet;
        }

        // 合并两个点到同一个集合中
        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                // 将 toSet 中的点全部加到 fromSet 中
                fromSet.add(toNode);
                // 并把 toSet 中所有的点都变成 fromSet
                setMap.put(toNode, fromSet);
            }
        }
    }

    // 权重比较器
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    // 函数调用
    public static Set<Edge> kruskalMST(Graph graph) {
        List nodes = new LinkedList<Node>();
        for (Node node : graph.nodes.values()) {
            nodes.add(node);
        }
        // 初始化所有点
        MySet myset = new MySet(nodes);
        // 优先队列处理边的权重
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 将所有的边加入优先队列中
        for (Edge edge : graph.edges) {  // O(logN)
            priorityQueue.add(edge);
        }
        // 结果集
        Set<Edge> result = new HashSet<>();
        // 循环队列
        while (!priorityQueue.isEmpty()) {
            // 每次取出权重最小的边
            Edge edge = priorityQueue.poll();  // O(logN)
            if (!myset.isSameSet(edge.from, edge.to)) {  // O(1)
                // 判断图是否成环，不成环则加入结果集合
                result.add(edge);
                myset.union(edge.from, edge.to);
            }
        }
        return result;
    }

}
