package Graph;

public class GraphGenerator {

    /**
     * matrix 表示图
     * 一个 N * 3 的矩阵
     * [from节点的值, to节点的值, weight]
     */
    public static Graph createGraph (int [][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i ++) {
            int from = matrix[i][0];  // 发散节点
            int to = matrix[i][1];  // 指向节点
            int weight = matrix[i][2]; // 权重
            // 记录 from，如果还没记录的话
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            // 记录 to，如果还没记录的话
            if (!graph.nodes.containsKey((to))) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            // 添加边
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out ++;
            toNode.in ++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }

}
