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

    // 算法2
    public static class ReturnType {
        int min;
        int max;
        boolean isBST;
        public ReturnType (int mi, int ma, boolean isB) {
            min = mi;
            max = ma;
            isBST = isB;
        }
    }
    public static boolean isBST2(Node x) {
        return process(x).isBST;
    }

    public static ReturnType process(Node x) {
        if (x == null) {
            return null;
        }
        // 记录当前节点下左右节点的最大最小以及其几点是否为平衡二叉树
        // 一般 leftData 中记录的 min 是当前节点左节点的左节点，max 则为当前节点左节点的右节点，rightData 同理
        ReturnType leftData = process((x.left));
        ReturnType rightData = process(x.right);

        int min = x.value;
        int max = x.value;
        // 左节点有记录过，找出其最大最小值
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        // 右节点有记录过，找出其最大最小值
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        boolean isBST = true;
        // 判断是否为搜索二叉树
        // 若左节点没有记录过，则说明其是页节点，不操作
        // 若左节点有记录过，先判断其子节点是否为平衡二叉树，是则不操作
        // 再判单其子节点的最大值是否比父几点大，比父节点小则不操作
        if (leftData != null && (!leftData.isBST || leftData.max >= x.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || rightData.min <= x.value)) {
            isBST = false;
        }
        return new ReturnType(min, max, isBST);
    }
}
