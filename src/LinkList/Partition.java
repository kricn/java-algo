package LinkList;

/**
 * 将单向链表按某值划分为左边小、中间相等、右边大的形式
 *
 * 给定一个单链表的头节点 head, 节点类型是整形，再给写一个证书 pivot。实现一个调整链表的函数，将链表调整为左部分都是小于 pivot 的节点，
 * 中间部分都是值等于 pivot 的节点，右部分都是值大于 pivot 的节点
 *
 * 进阶：
 * 调整后所有小于，大于，等于 pivot 的节点之间的相对顺序和调整前一样
 * 时间复杂度为O(N)，额外空间复杂度为O(1)
 */

public class Partition {

    public static Node listPartition(Node head, int pivot) {
        Node sH = null;  // 最小头
        Node sT = null;  // 最小尾
        Node eH = null;  // 等于头
        Node eT = null;  // 等于尾
        Node mH = null;  // 大于头
        Node mT = null;  // 大于尾
        Node next = null;  // 保存下一个节点
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 连接小于区和大于区
        if (sT != null) {  // 如果有小于区
            sT.next = eH;
            eT = eT == null ? sT : eT; // 谁去连接大于区域的头，谁就是 eT, 这是为了防止没有等于区
        }
        if (eT != null) {  // 如果有等于区
            eT.next = mH;
        }
        // 返回小于头的整个链表并判断是否是小于区
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void main(String[] args) {

        Node linkList = null;
        int[] arr = {1,2,3,4,5,6,5,4,5,3,2,1};
        for (int item : arr) {
            if (linkList == null) {
                linkList = new Node(item);
            } else {
                Node cur = linkList;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = new Node(item);
            }
        }

        listPartition(linkList, 5);

        Node head = linkList;
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

}
