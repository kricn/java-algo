package Graph;


import java.util.*;

/**
 * 拓扑排序
 * 在一些框架中，有一点 config 文件，下面放着些依赖
 * 比如 A 依赖 B, C，B 依赖 D, E, C 依赖 F
 * 等 F D E 编译完才能编译 B C, B, C 编译完才编译 A
 * 这构成一个有向图(拓扑图)
 */
public class Topology {

    public static List<Node> sortedTopology(Graph graph) {
        // key: 某一个 node
        // value: 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 入度为 0 的点才能进入队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.value);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        // 拓扑排序的结果，依次加入result
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            // 加入结果集，并将下一个节点的入度减一
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

}
