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

    /**
     * morris 整体流程
     * @param head
     */
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

    /**
     * 前序遍历
     * 只出现一次的节点，直接打印
     * 出现两次的，打印第一次出现的
     */
    public static void morrisPre(Node head) {
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
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    // cur 向左移动
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null; // 恢复原来的树结构
                }
            } else {
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    /**
     * 中序遍历
     * 只出现一次的节点，直接打印
     * 出现两次的，打印第二次出现的
     */
    public static void morrisIn(Node head) {
        if (head == null) {
            return ;
        }
        Node cur = head;
        Node mostRight = null; // 当前节点左节点的最右节点
        while (cur != null) {
            mostRight = cur.left;
            // 没有左节点和第一次打印都会触发打印行为
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
            System.out.println(cur.value);
            cur = cur.right;
        }
    }


    /**
     * 后续遍历
     * 只出现一次的节点，什么也不做
     * 出现两次的，逆序打印该节点的右边界
     */
    public static void morrisPos(Node head) {
        if(head == null) {
            return ;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 第一次来到该节点，什么也不做
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.right;
                    continue;
                } else {
                    mostRight.right = null;
                    // 逆序打印左数右边界
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    /**
     * 以 X 为头的树，逆序打印这棵树的右边界
     */
    public static void printEdge(Node x) {
        Node tail = reverseEdge(x);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    /**
     * 返回逆序边界
     */
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

}
