package DP;

/**
 * 给定一个数组，数组中有无重复值的正整数面值的硬币，每个硬币不限次数使用
 * 请找出所有能凑够目标金额 aim 的所有方式
 */
public class CoinsWay {

    /**
     * 递归方法
     * @param arr 硬币数组
     * @param aim 目标值
     */
    public static int coinsWay1(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    /**
     * @param index 表示从 index 位置取硬币
     * @param rest 在 index 处还剩多少钱需要凑齐
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        /**
         * 当前面值每选一枚，在不超过目标值的情况下，往下一个值递归，同时减去剩下的目标值
         */
        for (int i = 0; arr[index] * i <= rest; i ++) {
            ways += process(arr, index + 1, rest - arr[index] * i);
        }
        return ways;
    }

    /**
     * 动态规划
     */
    public static int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // 以数组的下标为纵坐标，aim 为横坐标，则数组的下标为行，aim 为列
        int[][] dp = new int[N + 1][aim + 1];
        // 初始值,归后一行且目标值为0时是一种方式
        // 可以根据递归的 base case 确定
        dp[N][0] = 1;

        // 从行的尾部算起
        for (int index = N - 1; index >=0; index --) {
            for (int rest = 0; rest <= aim; rest ++) {
                // 枚举过程，观察递归函数递归方式，将递归行为转成成dp表
                // 观察表规律发现，当前值 dp[x][y] 依赖其下一行的当前列，下一行的当前列减去当前行的面值
                // 即 dp[x][y] = dp[x+1][y] + dp[x+1][y - arr[x] * 1] + dp[x+1][y - arr[x] * 2]] + ...
                int ways = 0;
                for (int i = 0; arr[index] * i <= rest; i ++) {
                    ways += dp[index + 1][rest - arr[index] * i];
                }
                dp[index][rest] = ways;
            }
        }

        // 所有硬币都有使用过并且目标值为0的位置
        return dp[0][aim];
    }

    /**
     * 动态规划（斜率优化）
     */
    public static int coinsWay3(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // 以数组的下标为纵坐标，aim 为横坐标，则数组的下标为行，aim 为列
        int[][] dp = new int[N + 1][aim + 1];
        // 初始值,归后一行且目标值为0时是一种方式
        // 可以根据递归的 base case 确定
        dp[N][0] = 1;

        // 从行的尾部算起
        for (int index = N - 1; index >=0; index --) {
            for (int rest = 0; rest <= aim; rest ++) {
                /**
                 * 斜率优化枚举
                 * 通过观察 dp 表可知 在求 dp[x][y] 时需要 dp[x+1][y] + dp[x+1][y - arr[x] * 1] + dp[x+1][y - arr[x] * 2]] + ...
                 * 而在 dp 表中，dp[x][y-arr[y-arr[x]] = dp[x+1][y - arr[x] * 1] + dp[x+1][y - arr[x] * 2]] + ...
                 * 故在上面动态规划的枚举过程中，可以把求当前仩的枚举过程优化成通过其前一个面试的差值列加不一下行的当前列获得
                 * 斜率优化在于观察当前dp表，不再考虑题目的实际意义，这是从已经生成的dp表中观察得来的优化，不在必要考虑题目的实际意义
                 */
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }

        // 所有硬币都有使用过并且目标值为0的位置
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,5};
        int aim = 100;
        System.out.println(coinsWay1(arr, aim));
        System.out.println(coinsWay2(arr, aim));
        System.out.println(coinsWay3(arr, aim));
    }

}
