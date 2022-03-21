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

}
