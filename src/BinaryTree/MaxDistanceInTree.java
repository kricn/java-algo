package BinaryTree;

/**
 * 二叉树节点间的最大距离
 *  从二叉树 a 点出发，可以向上或向下走，但沿途的节点只能经过一次，到达节点 b 时路径的节点个数叫作 a 到 b 的距离
 *  那么二叉树两两节点之间都有距离，取整棵树上的最大距离
 */
public class MaxDistanceInTree {

    public static int maxDistance(Node head) {
        return process(head).maxDistance;
    }

    /**
     * 记录每个节点的信息
     */
    public static class Info {
        public int maxDistance;
        public int height;
        public Info (int dis, int h) {
            maxDistance = dis;
            height = h;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        // 节点信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // info
        // 分两种大情况，最大距离是否包含 x 节点
        // 1、不包含，则最长距离可能是在左右节点中产生
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        // 包含，那必然是左子树最深的节点到右子树最深的节点
        int p3 = leftInfo.height + 1 + rightInfo.height;
        // 三种情况对比，取最大的
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        // 记录下当前节点的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }



}
