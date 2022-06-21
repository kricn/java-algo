package Other;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，不能使用额外的数据结构，只能用递归函数
 */
public class ReverseStack {

    public static void reverse(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            return ;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (!stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

}
