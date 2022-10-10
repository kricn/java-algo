package DP;


/**
 * 给定一个整整数数组，表示有一枚该面值的硬币
 * 给定一个正整数目标值，求最少使用多少枚硬币
 */
public class CoinsMin {

    /**
     * 递归实现
     * @param arr 硬币数组
     * @param aim 目标值
     * @return 最少使用硬币数
     */
    public static int coinsMin1(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    /**
     * 递归函数
     * @param arr 硬币数组，固定参数
     * @param index 当前硬币的下标
     * @param rest 组成 rest 这么多钱需要最少多少枚硬币
     * @return 最少使用硬币数
     */
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        // 不需要硬币了
        if (rest == 0) {
            return -1;
        }
        if (index == arr.length) {
            return -1;
        }
        // rest > 0 的情况
        // 不选择当前的硬币
        int p1 = process(arr, index + 1, rest);
        // 选择当前的硬币，对应的 rest 也要减去当前硬币的值
        int p2Next = process(arr, index + 1, rest - arr[index]);
        if (p1 == -1 && p2Next == -1) {
            // 两个都无解
            return -1;
        } else {
            // 至少一个有解
            if (p1 == -1) {
                // 因为 p2Next 在上面是选了硬币的，所以结果要加上一枚
                return p2Next + 1;
            }
            if (p2Next == -1) {
                return p1;
            }
            // 做选择
            return Math.min(p1, p2Next);
        }
    }

    /**
     * 记忆化搜索
     */
    public static int coinsMin2(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; i <= aim; i ++) {
                dp[i][j] = -2;
            }
        }
        return process2(arr, 0, aim, dp);
    }
    public static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (rest < 0) {
            return -1;
        }
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }

        // 不需要硬币了
        if (rest == 0) {
            dp[index][rest] = 0;
        } else if(index == arr.length) {
            dp[index][rest] = -1;
        } else {
            // rest > 0 的情况
            // 不选择当前的硬币
            int p1 = process(arr, index + 1, rest);
            // 选择当前的硬币，对应的 rest 也要减去当前硬币的值
            int p2Next = process(arr, index + 1, rest - arr[index]);
            if (p1 == -1 && p2Next == -1) {
                // 两个都无解
                dp[index][rest] = -1;
            } else {
                // 至少一个有解
                if (p1 == -1) {
                    // 因为 p2Next 在上面是选了硬币的，所以结果要加上一枚
                    dp[index][rest] = p2Next + 1;
                } else if (p2Next == -1) {
                    dp[index][rest] = p1;
                } else {
                    // 做选择
                    dp[index][rest] = Math.min(p1, p2Next);
                }
            }
        }
        return dp[index][rest];
    }


    /**
     * 严格表结构
     */
    public static int minCoins3(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        // 处理初始位置
        for (int row = 0; row <= N; row ++) {
            dp[row][0] = 0;
        }
        for (int col = 1; col <= aim; col ++) {
            dp[N][col] = -1;
        }

        // 根据暴力解法递归顺序
        // 每一行是依赖其下一行的
        // 每一列是依赖其下一列的
        for (int index = N - 1; index >= 0; index --) {
            for (int rest = 1; rest <= aim; rest ++) {
                int p1 = dp[index + 1][rest];
                int p2Next = rest - arr[index] == 0 ? dp[index+1][rest - arr[index]] : -1;
                if (p1 == -1 && p2Next == -1) {
                    dp[index][rest] = -1;
                } else {
                    if (p1 == -1) {
                        dp[index][rest] = p2Next + 1;
                    } else if (p2Next == -1) {
                        dp[index][rest] = p1;
                    } else {
                        dp[index][rest] = Math.min(p1, p2Next + 1);
                    }
                }
            }
        }

        return dp[0][aim];
    }


}
