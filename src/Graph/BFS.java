package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历
 */
public class BFS {

    public static void bfs(Node node) {
        if (node == null) {
            return ;
        }
        Queue<Node> queue = new LinkedList<>();
        // 备忘录，防止走重复的点
        HashSet<Node> set = new HashSet<>();

        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()) {
            // 出队列
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                // 若其指向没有在备忘录里，则加入备忘录及队列中
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
