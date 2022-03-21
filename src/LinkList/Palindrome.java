package LinkList;

import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构
 * 例子 1 -> 2 -> 1, 返回true; 1 -> 2 -> 2 - 1。返回 true; 15 -> 6 -> 15, 返回true
 * 1 -> 2 -> 3 返回 false
 * 例子 如果链表的长度为N，时间复杂度达到O(N) 额外空间复杂度达到O(1)
 */

public class Palindrome {

    // 堆栈法
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<Node>();

        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 快慢指针
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;
        Node cur = head;
        // 判断快指针有没有走到终点
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<Node>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need O(1) extra space
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;  // n1 -> mid
            n2 = n2.next.next; // n2 -> end
        }
        n2 = n1.next;  // 右侧的头节点
        n1.next = null;  // 中点指向null
        Node n3 = null;
        while (n2 != null) {  // 反转右侧
            n3 = n2.next; // 保存右侧的下一个节点
            n2.next = n1;  // 右侧节点的 next 往前指
            n1 = n2;  // n1 移动
            n2 = n3; // n2 移动
        }
        n3 = n1;  // 右侧反转后的第一个节点
        n2 = head; // 左侧第一个节点
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
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

        Boolean res = null;

        res = isPalindrome3(linkList);

        System.out.println(res);
    }

}
