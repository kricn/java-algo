package Other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集维妮塔
 */
public class Subset {

    /**   无重复数的子集   *****/

    /**
     * leetcode 78 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
    public static List<List<Integer>> subset1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        boolean path[] = new boolean[nums.length];
        dfs1(nums,-1,res,path);
        return res;
    }

    /**
     * 辅助函数
     * @param nums 原始数组
     * @param index 当前数组的下标
     * @param res 结果集
     * @param path 标记路径是否被选中过
     */
    public static void dfs1(int[] nums, int index, List<List<Integer>> res, boolean[] path) {
        // 每一次递归都要记录结果集
        List<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i ++) {
            // 记录标记过的 因为这里的循环可能出现之前储存过的
            if (path[i]) tmp.add(nums[i]);
        }
        res.add(tmp);
        for(int i = index + 1; i < nums.length;i++) {
            // 标记当前，表示该值会加入到结果集中
            path[i] = true;
            dfs1(nums, i, res, path);
            path[i] = false;
        }
    }

    /**   有重复数的子集   *****/
    /**
     * leetcode 90 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     */
    public static List<List<Integer>> subset2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        boolean path[] = new boolean[nums.length];
        dfs2(nums,-1,res,path);
        return res;
    }
    /**
     * 辅助函数
     * @param nums 原始数组
     * @param index 当前数组的下标
     * @param res 结果集
     * @param path 标记路径是否被选中过
     */
    public static void dfs2(int[] nums, int index, List<List<Integer>> res, boolean[] path) {
        // 每一次递归都要记录结果集
        List<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i ++) {
            // 记录标记过的 因为这里的循环可能出现之前储存过的
            if (path[i]) tmp.add(nums[i]);
        }
        res.add(tmp);
        for(int i = index + 1; i < nums.length;i++) {
            // 回溯剪枝
            // 因为数组是排序好的，所以第一次传入的数组中，相同的数字肯定是挨在一起的
            // 在重复数字中，排第一个肯定是先记录过一次
            // 在之后递归中，当遇到相同数字时，若其前一位没有被标记过，则说明遇到了重复顺序，则标记
            // 若当前位置的数和上一位一样，而上一位没有标记过，说明该种组合已经组合过了
            // 这里需要 i 大于 1 时才做重复判断
            // 一来是防止数组越界，二来是这样判断才有效
            // 在 i > 1 后，取到相同的数，之后的数和上一次出现的数之后的数是相同的
            // 如 [1,2,1,1], 1 1(1) 2 1(2) 和 1 1(2) 2 1(1) 是一样的
            if((i == 0) || (nums[i] != nums[i-1]) || (i > 0 && path[i-1] && nums[i] == nums[i-1])) {
                // 标记当前，表示该值会加入到结果集中
                path[i] = true;
                dfs1(nums, i, res, path);
                path[i] = false;
            }

        }
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> res = subset1(arr);
        System.out.println(res);

        int[] arr2 = {1,2,2};
        List<List<Integer>> res2 = subset2(arr2);
        System.out.println(res2);
    }

}
