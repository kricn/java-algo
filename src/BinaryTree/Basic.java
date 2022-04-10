package BinaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的递归遍历过程中，每递归一次都有三次对当前次进行操作的机会
 * 即注释中的 1 2 3
 * 比如在递归第一次的时候，在未递归前一次，递归完左节点后一次，递归完右节点后一次
 * 称为递归序
 * 在第一次进行操作的叫前序遍历
 * 在第二次进行操作的叫中序遍历`
 * 在第三次进行操作的叫后序遍历
 */
public class Basic {
    // 前序遍历 - 递归
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return ;
        }
        // 1 前序
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        // 2 中序
        preOrderRecur(head.right);
        // 3 后序
    }

    // 前序遍历 - 迭代
    public static void preOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                // 右边的先入栈
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.right);
                }
            }
        }
    }

    // 后序遍历 - 迭代
    public static void posOrderUnRecur(Node head) {
        if (head != null) {
            // 储存节点栈
            Stack<Node> s1 = new Stack<Node>();
            // 辅助栈
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while(!s1.isEmpty()) {
                // 出栈时不打印，面是放入辅助栈中
                head = s1.pop();
                s2.push(head);
                // 先放左边，到时候在辅助栈中就会先打印
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // 收集完所有节点后统一打印
            while (!s2.isEmpty()) {
                System.out.print(s2.pop() + " ");
            }
        }
    }

    // 中序遍历
    public static void inOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                // 先将左边界压栈
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    // 在弹栈过程中，将该节点的右节点也压入栈中
                    head = stack.pop();
                    System.out.print(head + " ");
                    head = head.right;
                }
            }
        }
    }

    // 宽度优先遍历的基础上找到最宽的那一层
    public static void widthOrder(Node head) {
        if (head == null) {
            return ;
        }
        // 用队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 记录层级的 hash map
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        // 当前层级
        int curLevel = 1;
        // 当前层有多少个节点
        int curLevelNodes = 0;
        // 当前层级最大的宽度
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            // 打印的同时将左右节点加入队列
            Node cur = queue.poll();
            // 出队列时，从 hash map 中获取当前的层级
            int curNodeLevel = levelMap.get(cur);
            // 如果和当前层一致，说明是在同一层
            if (curLevel == curNodeLevel) {
                // 则说明当前节点还在同一层
                // 更新当前层节点数
                curLevelNodes ++;
            } else {
                // 进入下一层
                curLevel ++;
                // 结算上一层
                max = Math.max(max, curLevelNodes);
                // 重置当前层节点
                curLevelNodes = 0;
            }
            // System.out.print(cur.value);
            // 先放左
            if (cur.left != null) {
                // 入队列时 hash map 记录层级
                levelMap.put(cur.left, curLevelNodes + 1);
                queue.add(cur.left);
            }
            // 再放右
            if (cur.right != null) {
                // 入队列时 hash map 记录层级
                levelMap.put(cur.right, curLevelNodes + 1);
                queue.add(cur.right);
            }
        }
    }

    public static void main(String[] args) {
//        Node head = null;
//        for (int i = 0; i < 10; i ++) {
//
//        }
    }

}
