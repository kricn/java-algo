package Other;

/**
 * 规定 1 和 A 对应，2 和 B 对应，3 和 C 对应...
 * 那么一个数字字符串比如“111”，就可以转成”AAA"、“KA”、"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class ContentToLetterString {

    // i 之前的位置，如何做转化已经做过决定了
    // i 之后的有多少种转化的结果
    public static int process(char[] str, int i) {
        // 当来到最后时，可以保证之前做的决定是有效的，返回一种
        if (i == str.length) {
            return 1;
        }
        // 当 str[i] 为 0 时，无法转换，所以之后的组合也是无效的，当前组合也是无效的，返回 0
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            // i 自己作为单独部分，后续有多少种方法
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                // i 和 i + 1 做为单独
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            // i 后有字符，且大于 0 小于 6 才有效
            if (i + 1 < str.length && (str[i + 1] > '0' && str[i + 1] <= '6')) {
                res += process(str, i +2);
            }
            return res;
        }
        // 3 ~ 9 的决定，只有自己一种
        return process(str, i + 1);
    }

    public static void main(String[] args) {
        char[] str = {'1', '2', '1', '1', '2', '7', '3', '9'};
        int res = process(str, 0);
        System.out.println(res);
    }
}
