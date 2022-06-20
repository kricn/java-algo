package Other;

/**
 * 给定一个整形数组 arr, 代表数值不同的纸牌连成一条线。玩家 A 和玩家 B 依次拿走每张牌，
 * 规定玩家 A 先拿，每个玩家每次只能拿走最左或最右的牌, 你假设每个玩家的玩法都会使他的分数最大化。
 *
 * 举例
 * arr = [1,2,100,4]
 * 玩家 A 只能拿走 1 或 4， 如果开始时玩家 A 拿走 1, 则排列变成 [2,100,4], 接下来玩家 B 可以
 * 拿走 2 或 4, 再到玩家 A 拿牌
 * 在假设情况下，每个玩家都会使他分数最大第，所以 A 会先拿 1, 之后无论 B 怎么拿, A 值都会拿 100，
 * A 获胜，返回分数 101
 * arr = [1,100,2]
 * 不管 A 拿什么，B 都会拿 100, 返回分数 100
 */
public class PredictTheWinner {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 先手玩家 在 i 到 j 范围去尝试拿对自己最有利的
     * @param arr 牌堆
     * @param i 左边界
     * @param j 右边界
     * @return 得分
     */
    public static int f(int[] arr, int i, int j) {
        if (i == j) { // base case
            // 先选左边的
            return arr[i];
        }
        // 在之后的 i + 1 到 j 里，先手玩家为后手，调用后手函数
        // 同时也要选右边的牌做尝试，比较两次选择的大小
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i , j - 1));
    }

    /**
     * 后玩家 在 i 到 j 范围去尝试拿对自己最有利的
     * @param arr 牌堆
     * @param i 左边界
     * @param j 右边界
     * @return 得分
     */
    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        // 因为是后手，所以在别人选完后，会留下最不优的情况，返回最小值
        return Math.min(f(arr, i + 1, j), f(arr, i , j - 1));
    }

}
