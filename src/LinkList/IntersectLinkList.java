package LinkList;

/**
 * 给定两个可能有环也可能无环的单链表，头节点 head1 和 head2。请实现一个函数，如果两个链表相交，请返回相交的一个头节点。
 * 如果不相交，返回 null
 * 【要求】 如果两个单链表长度之各为 N, 时间复杂度请达到 O(N), 额外空间复杂度请达到 O(1)
 */

public class IntersectLinkList {

    // 获取链表中和第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next;  // 快指针
        Node n2 = head.next.next; // 慢指针
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next; // 快指针走两步
            n1 = n1.next; // 慢指针走一步
        }
        // 快指针回到头节点，之后两个指针都各走一步，则它们一定会在入环点相遇
        n2 = head;
        while(n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交，返回 null
    public static Node noLoop (Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n ++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n --;
            cur2 = cur2.next;
        }
        // 两个链表相交，它们最后一个节点必然是同一个
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2; // 判断两个俩表谁长，长的指向 cur1
        cur2 = cur1 == head1 ? head2 : head1;  // 短的指向 cur2
        n = Math.abs(n);
        // 长俩表先走两个链表长度的差值步
        while (n != 0) {
            n --;
            cur1 = cur1.next;
        }
        // 之后两个链表一起走
        // 第一次相等时即为第一个相交点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个有环链表，返回第一个相交节点，不相交则返回 null

    /**
     *
     * @param head1 链表1
     * @param loop1 链表1的入环节点
     * @param head2 链表2
     * @param loop2 链表2的入环节点
     * @return Node
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        // 共用一个入环点
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            // 以入环点为结束点
            while (cur1 != loop1) {
                n ++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n --;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            // 长俩表先走两个链表长度的差值步
            while (n != 0) {
                n --;
                cur1 = cur1.next;
            }
            // 之后两个链表一起走
            // 第一次相等时即为第一个相交点
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
            // 不共用入环点
            // 有两种情况 1 两个环互不相关，即两个链表不相交
            // 2 共用一个环，但不有两个不同的入环点，返回任意一个入环点都对
        } else {
            // 从任意一个入环点开始往下走
            cur1 = loop1.next;
            // 当再次回到入环点时，如果遇到另一个入环点，则是情况2，否则是情况1
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
            }
            return null;
        }
    }

}
