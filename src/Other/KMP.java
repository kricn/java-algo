package Other;

public class KMP {

    public static int KMP(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);  // O(M)
        // O(N)
        // i1 和 i2 都不越界
        while (i1 < str1.length && i2 < str2.length) {
            // 匹配成功，两个下标向右移
            if (str1[i1] == str2[i2]) {
                i1 ++;
                i2 ++;
            //
            } else if (next[i2] == -1) { // 当 i2 还是开头的时候还是匹配不上，i1 则往右移
                i1 ++;
            } else { // i2 往回跳，即 m 往右推
                i2 = next[i2];
            }
        }
        // i1 越界或 i2 越界
        return i2 == str2.length ? i1 - i2 : -1;
    }

    public static int[] getNextArray (char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length];
        // 第 0 位认为规定为 -1
        next[0] = -1;
        // 第 1 位认为规定为 0
        next[1] = 0;
        int i = 2; // next 数组的位置
        int cn = 0; // 哪个位置的字符和 i - 1 对比，同时也表示 i - 1 的信息，即有多少个相同的前后缀
        while (i < ms.length) {
            // i-1 和最长前缀的一下个比较，相等则 i 的信息在 i-1的基础上加1，
            // 为什么是 +1? 因为如果是比 +1 大的话，则i-1的信息就是不正确的
            // 比如 i-1 的信息是7，假设 i 的信息是 9，则0~8和i-7~i-1也是相等的，此时 i-1 应该为8才对，矛盾
            // i 往下增加去求 i + 1 的信息，cn 也增加，则时也代表了 i 的信息
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) { // 当前跳到 cn 位置的字符，和 i - 1 位置的字符匹配不上，cn 也有对应的信息，继续往回跳
                cn = next[cn];
            } else { // 都匹配不上，则为 0
                next[i ++] = 0;
            }
        }
        return next;
    }

}
