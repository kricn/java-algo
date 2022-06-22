package Other;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，不能使用额外的数据结构，只能用递归函数
 */
public class ReverseStack {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return ;
        }
        // 拿到栈底元素
        // 这时栈的顺序是不变的
        int i = f(stack);
        reverse(stack);
        // 将栈底的压入栈
        stack.push(i);
    }

    /**
     * 弹出栈底元素并保持栈顺序不变
     * @param stack 栈数据
     * @return
     */
    public static int f(Stack<Integer> stack) {
        // 先弹出栈顶元素
        int result = stack.pop();
        // 到底部直接返回
        if (stack.isEmpty()) {
            return result;
        } else {
            // 这里返回的是栈底的元素
            int last = f(stack);
            // 这里保存的是上一次的元素
            // 再重新压回栈中
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack<Integer>();
        int[] arr = {1,2,3,4,5};
        for (int a : arr) {
            stack.add(a);
        }
        reverse(stack);
        System.out.println(stack);
    }

}
