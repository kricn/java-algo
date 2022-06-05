package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * 求 图的最短路径
 * 适用于边权值没有负数的图
 */
public class Dijkstra {

    public static HashMap<Node, Integer> dijkstra(Node head) {
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

    public static class NodeHeap {
        // 将所有节点放入数组中
        private Node[] nodes;
        // 用于查询 node 在堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // node 目前到 head 的最短距离
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public static void addOrUpdateOrIgnore(Node head, int index) {

        }

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<Node, Integer>();
            distanceMap = new HashMap<Node, Integer>();
            size = 0;
        }

        // 往上调整为小根堆
        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 往下调整成小根堆
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
            }
        }

        // 判断是否为空
        private boolean isEmpty() {
            return size == 0;
        }

        // 判断节点是否进入过堆
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        // 判断节点是否在堆上，不在堆上的节点会被标记为 -1，即表示该节点被弹出
        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int i, int j) {
            heapIndexMap.put(nodes[i], j);
            heapIndexMap.put(nodes[j], i);
            Node tmp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tmp;
        }
    }

    /**
     * 改进后的dijkstra算法
     * 利用小根堆加速
     */
    public static HashMap<Node, Integer> dijkstra2 (Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }


}
