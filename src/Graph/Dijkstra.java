package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * 求 图的最短路径
 * 适用于边权值没有负数的图
 */
public class Dijkstra {

    public static HashMap<Node, Integer> Dijkstra(Node head) {
        // 从 head 出发到所有点的最小距离
        // key: 从 head 到 key，
        // value: 从 head 出发到 key 的最小距离
        // 如果在表中，没有 key 的记录，则表示从 head 出发到 key 这个点的距离为正无穷
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);
        // 已经求过距离的节点，存在 selectedNodes 中，就不再去改变
        HashSet<Node> selectedNodes = new HashSet<>();
        // 在 distanceMap 中选择一条最小的记录，并且这条记录不在 selectedNodes 中
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        // 第一次获取到的最小点肯定是 head
        while (minNode != null) {
            // 从 map 中 获取原点到 minNode 的距离
            int distance = distanceMap.get(minNode);
            // 处理 minNode 的每一条边
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                // 若该点没有在 map 中，表示原点到该点的距离为正无穷
                if (!distanceMap.containsKey(toNode)) {
                    // 更新原点到该点的距离（值）-- 最小点的距离 + 该条边的权重
                    distanceMap.put(toNode, distance + edge.weight);
                }
                // 若已经在 map 中了，则比较两个距离（值）的大小，取小的那个
                distanceMap.put(edge.to, Math.min(distanceMap.get(toNode),
                        distance + edge.weight));
            }
            // 记录已经完成的点，原点到这个点的距离已经是最小了
            selectedNodes.add(minNode);
            // 重新找 minNode
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchNodes) {
        Node minNode = null;

        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!touchNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }

        return minNode;
    }

}
