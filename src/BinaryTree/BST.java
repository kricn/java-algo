package BinaryTree;

/**
 * 判断一个二叉树是否为二叉搜索树(比根节点小的在左边，大的在右边)
 */
public class BST {

    public static int preValue = Integer.MIN_VALUE;

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBST = isBST(head.left);
        // 判断左树是不是搜索二叉树，不一个不是那就不是了
        if (!isLeftBST) {
            return false;
            // 如果是，记录最小值
        } else {
            preValue = head.value;
        }
        // 判断右树，右树也是，那整棵树就是搜索二叉树
        return isBST(head.right);
    }

}
