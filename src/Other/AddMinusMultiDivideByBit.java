package Other;

public class AddMinusMultiDivideByBit {

    // 如果传入的参数，a + b 就是溢出的
    public static int add (int a, int b) {
        int sum = a;
        // 有进位的话，再异或，得到这次的和
        while (b != 0) {
            // 异或无进位求和
            sum = a ^ b;
            // 看两个数有没有进位
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * 求相反数
     * 任意一个数取反再加1为其相反数
     * 举例：
     * 1 二进制是 0000 0001 第一位是表示正负数的
     * 计算机中存的是 补码，而在输出时是源码
     * 负数在转码时符号位不变
     * 整数的源码和补码是一样的，补码转源码为 -1 再取反，源码转补码则是取反再加1
     * 所以 1 取反后为 1111 1110
     * 加 1 则为 1111 1111
     * 在读取时，1 开头的表示负数，所以将其转为源码为 1000 0001 就是 -1
     */
    public static int negNum(int n) {
        return add(~n, 1);
    }

    /**
     * 减法
     */
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * 乘法
     */
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            // 当 b 的最低位不为 0 时，累加当前 a 的劫夺
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            // a 往左移，相当于 b 的一位数和 a 相乘
            // 因为高位和 a 相乘，对对应的结果也会进一位
            a <<= 1;
            // b 则无符号向右移动，相当于取 b 的下一个数
            b >>>= 1;
        }
        return res;
    }

    /**
     * 判断是否是负数
     */
    public static boolean isNeg(int n) {
        return n < 0;
    }

    /**
     * 除法就是乘法的逆运算
     * 乘法是通过 b 不断像左移（如果能移）再累加的结果
     * 当 b 左移到 a 能减去 b 时 b 的最大值 x
     * 乘法的结果 res 减去 x 后，b 左移的位数上必然有 1
     */
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        // 让 y 右移是防止溢出
        for (int i = 31; i > -1; i = minus(i , 1)) {
            // 找到 b 向左移的最大位数 n
            if ((x >> i) >= y) {
                // res 的第 n 位上必然有 1
                res |= (1 << i);
                // 更新 x 为 x 与 y 左移 n 位后的差值
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

}
