package Other;


/**
 * 背包问题
 * weight[i] 表示 i 的重量 values[i] 表示 i 的价值
 */
public class Knapsack {

    /**
     * 每一个获取都有选和不选两种选择
     * i 往后后的货物作选择，形成最大值返回
     * @param weights 每件货物的重量
     * @param values 每件货物的价值
     * @param i 第几个货物
     * @param alreadyWeight 已选货物所累加的重量
     * @param bag 背包所能承受的最大重量
     * @return
     */
    public static int process1(int[] weights, int[] values, int i, int alreadyWeight, int bag) {
        // 超重，则该方案行不通
        if (alreadyWeight > bag) {
            return 0;
        }
        // 当 i 来到最后时，往后形成最大价值为 0
        // 最后一个是默认不选的
        if (i == weights.length) {
            return 0;
        }
        // 往下走有两种选择
        // 选择当前 i 的货物，则值和所选重量都要加上
        return Math.max(
            // 不选 i 货物
            process1(weights, values, i + 1, alreadyWeight, bag),
            // 选择 i 货物，则需要加上其价值和重量
            values[i] + process1(weights, values, i + 1, alreadyWeight + weights[i], bag)
        );
    }

}
