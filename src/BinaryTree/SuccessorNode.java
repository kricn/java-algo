package BinaryTree;

/**
 * 给定一个新的Node结构,其中包含了每个节点的父节点 parent，结构如下
 * 获取指定节点的后缀节点
 * 后缀节点：中序遍历中，当前节点的下一个节点即后缀节点
 * 如：中序遍历的结果是 2，4，5，6，7，则 2 的后缀节点是4
 */
public class SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;  // 当前节点的父节点
        public Node (int data) {
            this.value = data;
        }
    }

    /**
     * 分两种情况
     * 1、节点有右节点，则其后缀节点是其右节点的最左节点
     * 2、节点没有右节点，则往上遍历，当遍历到目标节点其父节点的左节点就是目标节点时，则其父节点为节点的后缀节点
     */
    public static Node getSuccessorNode (Node node) {
        if (node == null) {
            return node;
        }
        // 满足条件1
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            Node parent = node.parent;
            // 没有右节点，往上找父节点，直到有有个节点是其父节点的左节点时
            // 因为在中序遍历中，一个节点没有右节点，则是其后缀节点的左节点的最后一个值
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
