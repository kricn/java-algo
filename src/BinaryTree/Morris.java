package BinaryTree;

/**
 * Morris 遍历 时间复杂度 O(N) 空间复杂度 O(1)
 * 假设来到当前节点 cur，开始时 cur 来到头节点位置
 * 1、如果 cur 没有左节点，cur 向右移动
 * 2、如果 cur 有左节点，找到左节点上的最右的节点 mostRight
 *  2.1 如果 mostRight 的右指针为空，则让其指向 cur，然后 cur 向左移动
 *  2.2 如果 mostRight 的右指针为 cur，则让其指向 null，cur 向右移动
 * 3、cur 为空，遍历结束
 */
public class Morris {

    public static void morris(Node head) {
        if (head == null) {
            return ;
        }
        Node cur = head;
        Node mostRight = null; // 当前节点左节点的最右节点
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 找到左树上的最右节点，当右指针指向当前节点时，说明该节点已经被改过指向
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {  // 第一次来到 cur
                    mostRight.right = cur;
                    // cur 向左移动
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null; // 恢复原来的树结构
                }
            }
            cur = cur.right;
        }
    }

}
