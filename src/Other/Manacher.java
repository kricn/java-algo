package Other;

/**
 * 题目：求字符串中啊长的回文字符串
 */
public class Manacher {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i ++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index ++];
        }
        return res;
    }

    public static int maxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s); // 给字符串添加虚拟位置，这个可以是任意字符  1221 -> #1#2#2#1#
        int[] pArr = new int[str.length]; // 回文半径数组，即从中心点到最右边的长度
        int C = -1; // 回文中心点
        int R = -1; // 回文右边界再往右的一个位置，回文有效区域到 R - 1
        int max = Integer.MIN_VALUE;  // 已知道回文的最大长度
        // 以 i 为中心往两边扩展查找回文
        for (int i = 0; i != str.length; i++) { // 每个位置都要求回文半径
            // 加速语句，至少不用校验的区域半径
            // 1、如果 i 在回文右边界外，则其半径至少是1（自己）
            // 2、如果在回文右边界内，因为回文基于 C 位置对称，所以 i 基于 C 对称的 i‘ 好有一个回文区域
            // 分三种情况，i'的回文区域完全在 C 为中心点的回文区域内[L~R-1]，则 i 的回文区域至少和 i' 相同
            // i'的回文区域有一部分在[L~R-1]内，则 i 的回文区域至少是 i ~ R-1
            // i'的回文区域左边界刚好是在[L~R-1]的左边界上，则 i 的回文区域至少是 i ~ R-1
            // 第二种情况的话，取比较小那个
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                // 跳过至少不用校验的区域，因为这些之前就校验过了
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 回文区域扩展后，判断该区域是否有比有边界更远的
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        // 原来字符串的回文长度
        return max - 1;
    }

}
