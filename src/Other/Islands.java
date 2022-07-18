package Other;

/**
 * 岛屿问题
 * 一个矩阵只有0和1两种值，每个位置都可以和自己的上、下、左、右四个位置相连，如果有一片1连在一起，这部分叫做一个岛
 * 求一个矩阵中有多少个岛
 * 举例
 * 001010
 * 111010
 * 100100
 * 000000
 * 这个矩阵中有三个岛
 * 【进阶】如何设计一个冰箱算法解决这个问题
 */
public class Islands {

    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        for (int i = 0; i< N; i++) {
            for (int j = 0; j < M; j ++) {
                // 将连着的 1 感染成 2
                if (m[i][j] == 1) {
                    res ++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] m, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j>= M || m[i][j] != 1) {
            return ;
        }
        // 这里表示的的是 i, j 没有越界并 m[i][j] 为 1 的情况下
        m[i][j] = 2;
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }

    public static void main(String[] args) {
        int[][] m1 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0, 0, 0, 1, 1},
                {0, 1, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 0}
        };
        int res = countIslands(m1);
        System.out.println(res);
    }

}
