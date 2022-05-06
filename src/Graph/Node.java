package Graph;

import java.util.ArrayList;

public class Node {

    public int value;  // 节点的值
    public int in; // 点的入度，指有多少条边指向该点
    public int out; // 点的出度，指有多少条边从这个点指出
    public ArrayList<Node> nexts; // 由该点发散出去的边所连接的点
    public ArrayList<Edge> edges; // 属于该点的边（由该点发散出去的边）

    public Node(int val) {
        this.value = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

}
