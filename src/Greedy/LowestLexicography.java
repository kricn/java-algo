package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 拼接字符串使其结果的字典序为最小
 *
 * 字典序：a 排最前，z 排最后，按照查字典那样去排序
 * 如：ab < ac，ba < cbd，cb < cba, 因为按照字典查找，先找到 c, 再找到 cb, 再到 cba
 */

/**
 * 贪心策略：两个字符串调换顺序拼接，哪种拼接方案字典序小就用哪个
 * 如： b 和 ba 拼接，有 bba 和 bab 两种，按照字典续，bab更小，所以选择 bab 这种拼接方式
 */
public class LowestLexicography {

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i ++) {
            res += strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = {"abc", "ddd", "aba", "cba", "bbq"};

        System.out.println(lowestString(strs));
    }

}
