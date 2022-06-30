package BinaryTree;

import java.util.LinkedList;

/**
 * 判断一个二叉树是否为完全二叉树(每一个节点都是完全二叉树)
 */
public class CBT {

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右节点不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.push(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                    (leaf && (l != null || r != null)) // 遇到过左右不双全的节点，其子节点不是叶节点
                    || (l == null && r != null)  // 有右节点但没有左节点
            ) {
                return false;
            }
            if (l != null) {
                queue.push(l);
            }
            if (r != null) {
                queue.push((r));
            }
            // 遇到左右节点不双全的节点，则更新标记
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

}
