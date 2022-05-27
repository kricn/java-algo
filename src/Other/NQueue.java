package Other;

/**
 * N 皇后问题 时间复杂度是 O(n^n) 指标没法优化
 */
public class NQueue {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];  // 记录第 i 行的皇后放在第几列
        return process1(0, record, n);
    }

    /**
     *
     * @param i 表示来到第 i 行
     * @param record 表示第 i 行放的皇后
     * @param n 表格的大小
     * @return 多少种摆法
     */
    public static int process1(int i, int[] record, int n) {
        if (i == n) {  // base case 来到终止行，则返回一种摆法
            return 1;
        }
        int res = 0;
        // 判断第 i 行的每一列能不能摆
        // 这里为什么不用清除record呢？
        // 因为递归回到第一行时，
        // record[0] 会变为新的位置
        // 虽然这里 record 后也有记录，但由于 isValid 方法
        // isValid 并不会去比较当前行之后的位置
        for (int j = 0; j < n; j ++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                // 深度优先遍历
                // 因为 i 行放了皇后后，这一行已经不行再放了，到下一行
                res += process1(i +1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // 遍历之前行的皇后
        // 不会比较当前行之后的位置，k < i ，i 表示当前行
        for (int k = 0; k < i; k++) {
            // j == record[k]  表示在同一列
            // ath.abs(record[k] - j) == Math.abs(i - k) 表示在对角线，x方向的差值 == y方向的差值
            // 因为是一行一行来放皇后的，所以行不用判断，一定不会在同一行
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    /*********** 常熟优化版本 **************************/
    // 不超过 32 位
    // 因为是通过位运算来优化的，为了保证是整数，最好不要超过32位
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 生成一个32位的二进制数
        // 这个数的后几位是1，其他的全是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     *
     * @param limit 与棋盘大小相对应的32痊二进制数
     * @param colLim 列的限制，1位置不能放放皇后，0位置可以
     * @param leftDiaLim 左斜线限制，1不能，0能
     * @param rightDiaLim 右斜线限制
     * @return 有多少种摆法
     */
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {  // 所有列都放上了皇后或者说，没有列可以放皇后了 base case
            return 1;
        }
        int mostRightOne = 0;
        // 取列、左、右的限制或运算，得到下一行的限制
        // 下一行的限制取反再和 Limit 与运算，得到的二进制数中1的位置是可以放皇后的
        // pos 就是可以放皇后列的集合
        // 这里要和限制列的数区分开来，pos 中的1表示可以放皇后
        // 但 colLim, leftDiaLim, rightDiaLim 中的 1 表示不能放皇后
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos != 0) {
            // 取最右边的1，即尝试最右边可以放皇后的那一列
            // 这也是为什么在pos中用1来表示可以放皇后的列
            mostRightOne = pos & (~pos + 1);
            // 往左继续尝试
            pos = pos - mostRightOne;
            // 重新计算列限制
            // 列限制：之前的列限制与上当前尝试的列
            // 左限制：之前的左限制与上当前尝试的更再左移一位
            // 右限制：之前的右限制与上当前尝试的更再右移一位
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;
        long start = System.currentTimeMillis();
        System.out.println(num1(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }

}
