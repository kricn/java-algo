package Graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer, Node> nodes; // 点集，key是编号，node是具体的节点
    public HashSet<Edge> edges; // 边集

    public Graph() {
        nodes = new HashMap<Integer, Node>();
        edges = new HashSet<Edge>();
    }
}
