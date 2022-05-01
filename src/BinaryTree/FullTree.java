package BinaryTree;

public class FullTree {

    public static class Info {
        int height;
        int nodes;
        public Info (int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static boolean isFullTree (Node head) {
        if (head == null) {
            return true;
        }
        Info data = f(head);
        // 节点个数是否等于 2^n - 1 (n为高度)
        return data.nodes == (1 << data.height - 1);
    }

    public static Info f (Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftData = f(x.left);
        Info rightData = f(x.right);
        // 当前节点的高度为最高子节点的高度加1
        int height = Math.max(leftData.height, rightData.height) + 1;
        // 节点数
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new Info(height, nodes);
    }

}
