package LinkList;

import java.util.HashMap;

/**
 * 复制含有随机指针节点的链表
 * Node 结构如下
 * class Node {
 *     int value;
 *     Node next;
 *     Node rand;
 *     Node (int val) {
 *         value = val;
 *     }
 * }
 * rand 指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向 null.
 * 给定一个由 Node 节点类型组成的无环单链表的头节点 head, 请实现一个函数完成这个链表的复制，
 * 并返回复制的新链表的头节点
 *
 * 要求：时间复杂度 O(N), 额外空间复杂度为 O(1)
 */

public class CopyLinkList {

    public static Node copyListWidthRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur 老节点
            // map.get(cur) 新节点
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWidthRand2 (Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 复制每一个节点到当前节点的next
        // 1 -> 2
        // 1 -> 1' -> 2
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        // 设置 rand 值
        // 1 -> 1' -> 2 -> 2'
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // 分离新老节点
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
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

        Node res = null;

        res = copyListWidthRand2(linkList);

        while (res != null) {
            System.out.println(res.value);
            res = res.next;
        }
    }

}
