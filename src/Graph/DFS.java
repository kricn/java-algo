package Graph;


import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历
 */
public class DFS {

    public static void dfs(Node node) {
        if (node == null) {
            return ;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.add(node);
        set.add(node);
        // 处理压栈的节点
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            // 弹栈
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    // 将弹出的点再次压入栈中，最后栈即为图的深度遍历路径
                    stack.add(cur);
                    stack.add(next);
                    set.add(next);
                    // 处理邻居节点
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
