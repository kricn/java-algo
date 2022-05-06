package Graph;

public class Edge {

    public int weight; // 边的权重
    public Node from; // 从哪个点来
    public Node to; // 到哪个点去

    public Edge(int weight, Node from , Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
