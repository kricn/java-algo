package BinaryTree;

import java.util.HashMap;
import java.util.HashSet;

// 找出两个节点的最低公共祖先
public class LowestCommonAncestor {

    public static Node lca (Node head, Node o1, Node o2) {
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        process(head, fatherMap);
        HashSet<Node> set1 = new HashSet<>();
        Node cur = o1;
        // 往上回溯到头节点，并记录下其把经过的你父节点
        while (cur != fatherMap.get(cur)) {
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        // 单独加入头节点
        set1.add(head);
        cur = o2;
        // o2 往上回溯，每一次都检查其父节点是否在 set1 里
        while (!set1.contains(fatherMap.get(cur))) {
            cur = fatherMap.get(cur);
        }
        return cur;
    }

    // 记录所有节点的父节点，除了 head
    public static void process(Node head, HashMap<Node, Node> fatherMap) {
        if (head == null) {
            return ;
        }
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);
        process(head.left, fatherMap);
        process(head.right, fatherMap);
    }


    // 解法2

    /**
     * 分两种情况
     * 1、o1 和 o2 其中有一个为另一个的父节点，这样在递归到其中一个时，会直接返回“父节点”
     * 2、两两节点需要通过往上递归找到交集的
     */
    public static Node lca2 (Node head, Node o1, Node o2) {
        // 满足条件1
        if (head == null || head == o1 || head == o2) { // base case
            return head;
        }
        // 递归左右节点
        Node left = lca2(head.left, o1, o2);
        Node right = lca2(head.right, o1, o2);
        // 左右都有节点，则当前节点即为两个节点的公共祖先节点
        if (left != null && right != null) {
            return head;
        }
        // 若只有一个节点，返回不为空的那个节点
        // 这里在走到最底时，会返回null,因为最底部的节点是没有左右节点的，所以返回的是Null
        // 找到o1或o2时会返回o1或o2，只有满足条件2时才有可能走到这里
        return left != null ? left : right;
    }

}
