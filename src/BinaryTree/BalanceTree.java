package BinaryTree;

/**
 * 判断一个二叉树是否为平衡二叉树
 */
public class BalanceTree {

    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType (boolean isB, int hei) {
            isBalanced = isB;
            height = hei;
        }
    }

    public static ReturnType process(Node x) {
        if (x == null) {  // base case
            return new ReturnType(true, 0);
        }
        // 左树和右树信息
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        // 获取节点的高度
        int height = Math.max(leftData.height, rightData.height) + 1;
        // 判断节点是否为平衡二叉树
        /**
         * 满足条件
         * 左右子树都是平衡二叉树
         * 左右子树高度差不超过1
         */
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

}
