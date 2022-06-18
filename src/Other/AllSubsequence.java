package Other;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 */
public class AllSubsequence {

    public static void function1(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0, new ArrayList<Character>());
    }

    /**
     * 当前来到 i 位置，是否选择该字符
     * @param str 字符数组
     * @param i 当前的位置
     * @param res 记录所有的结果集
     */
    public static void process1(char[] str, int i, List<Character> res) {
        if (i == str.length) {
            printList(res);
            return ;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(str[i]);
        process1(str, i + 1, resKeep); // 选择加上当前的字符
        List<Character> resNoInclude = copyList(res);
        process1(str, i + 1, resNoInclude);  // 不加上当前的字符
    }

    public static void printList(List<Character> list) {
        String res = "";
        for (char l : list) {
            res += l + "";
        }
        System.out.println(res);
    }

    public static List<Character> copyList(List<Character> list) {
        List<Character> res = new ArrayList<Character>();
        for (char l : list) {
            res.add(l);
        }
        return res;
    }

    public static void function2(String str) {
        char[] chs = str.toCharArray();
        process2(chs, 0);
    }

    /**
     * 当前来到 i 位置，是否选择该字符 空间复杂度小
     * @param str 字符数组
     * @param i 当前的位置
     */
    public static void process2(char[] str, int i) {
        if (i == str.length) {  // 当 i 来到末尾时，则打印
            System.out.println(String.valueOf(str));
            return ;
        }
        process2(str, i + 1); // 选择当前位置的字符
        char tmp = str[i];  // 保留当前的穿字符
        str[i] = 0;
        process2(str, i + 1);  // 不选择当前的字符
        str[i] = tmp; // 递归结束后还原
    }



    public static void main(String[] args) {
        String str = "ABcdefg";
//        function1(str);
        function2(str);
    }

}
