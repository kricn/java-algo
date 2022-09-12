package DP;

/**
 * 在一个范围内有个机器人
 * 给定一个大于 1 的正整数 N，一共有 1 ~ N 个位置
 * 给定一个起始点（范围是 1 ~ N） S
 * 给定一个终点（范围是 1 ~ N）E
 * 给定一个步数 K，表示机器人一定要走 K 步并到达终点
 * 求机器人有几种走法
 */
public class RobotWalk {

    /**
     * 暴力递归
     * @param N 范围
     * @param E 终点
     * @param K 步数
     * @param S 起点
     * @return
     */
    public static int walkWays1(int N, int E, int K, int S) {
        return f1(N, E, K, S);
    }

    public static int f1(int N, int E, int rest, int cur) {
        // 步数为 0 时，统计不前是否到到达了终点
        if (rest == 0) {
            return cur == E ? 1 : 0;
        }
        // 当前点在 1 位置，只能往右走
        if (cur == 1) {
            return f1(N, E, rest - 1, 2);
        }
        // 当前点在 N 位置，只能往左走
        if (cur == N) {
            return f1(N, E, rest - 1, N - 1);
        }
        // 其他位置，返回其左右的和
        return f1(N, E, rest - 1, cur - 1) + f1(N, E, rest - 1, cur + 1);
    }

    /**
     * 记忆化搜索优化
     * @param N 范围
     * @param E 终点
     * @param K 步数
     * @param S 起点
     * @return
     */
    public static int walkWays2(int N, int E, int K, int S) {
        // dp 表，表的大小看暴力递归时怎么调用，也可以用哈希表
        // 一维数组表示剩下多少步，一直处于递减的状态，所以给空间 K + 1，因为 0 是没有的
        // 二位数组表示范围，1 ~ N
        int[][] dp = new int[K+1][N+1];
        // 将所以的值变成 -1，因为 0 这个值在 dp 数组中是有表示意义的
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j ++) {
                dp[i][j] = -1;
            }
        }
        // 将 dp 表带入递归中
        return f2(N, E, K, S, dp);
    }

    public static int f2(int N, int E, int rest, int cur, int[][] dp) {
        if (dp[rest][cur] != -1) {
            return dp[rest][cur];
        }
        // 步数为 0 时，统计不前是否到到达了终点
        if (rest == 0) {
            dp[rest][cur] = cur == E ? 1 : 0;
            return dp[rest][cur];
        }
        // 当前点在 1 位置，只能往右走
        if (cur == 1) {
            dp[rest][cur] = f2(N, E, rest - 1, 2, dp);
        } else if (cur == N) {
            dp[rest][cur] = f2(N, E, rest - 1, N - 1, dp);
        } else {
            dp[rest][cur] = f2(N, E, rest - 1, cur - 1, dp) + f2(N, E, rest - 1, cur + 1, dp);
        }
        return dp[rest][cur];
    }

    /**
     * 严格表结构优化, 规划 dp 数组
     * 以剩余步数 K 为行，当前位置 N 为列生成一个二位数组，其中第 0 列不可用，因为机器人不会在 0 位置
     * 第 [...][1] 的数即其右上角（[...+1][2]的数，同理，最后一列的数即其左上角的数，其它位置则是左右上角的数相加
     * @param N 当前位置
     * @param E 终点
     * @param S 起点
     * @param K 剩余步数
     * @return
     */
    public static int dpWay(int N, int E, int S, int K) {
        int[][] dp = new int[K + 1][N + 1]; // dp[...][0] 不可用
        dp[0][E] = 1; // 在第 0 行且是终点的位置，则记为 1
        for (int rest = 1; rest <= K; rest++) {
            for (int cur = 1; cur <= N; cur++) {
                if (cur == 1) {
                    dp[rest][cur] = dp[rest - 1][2];
                } else if (cur == N) {
                    dp[rest][cur] = dp[rest - 1][N - 1];
                } else {
                    dp[rest][cur] = dp[rest - 1][cur - 1] + dp[rest - 1][cur + 1];
                }
            }
        }
        for (int i = 0; i < K + 1; i++)
        {
            for (int j = 0; j < N + 1; j++)
            {
                System.out.printf("%s", dp[i][j]);
            }
            System.out.printf("\r\n");
        }
        return dp[K][S];
    }

    public static void main(String[] args) {
        int N = 6, S = 3, E = 5, K = 4;
        int res1 = walkWays1(N, E, K, S);
        System.out.println(res1);
        int res2 = dpWay(N, E, S, K);
        System.out.println(res2);
    }
}