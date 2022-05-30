package Other;

import Sort.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排雷问题 时间复杂度 O(N*N!)
 */
public class FullPermutation {

    /********* 第一种方法 ***************/
    /**
     * 回溯实现没重复数的全排列
     * @param nums 需要排列数的数组
     * @return
     */
    public static List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();  // 结果集
        List<Integer> collection = new ArrayList<Integer>(); // 回溯过程中收集到的结果
        boolean path[] = new boolean[nums.length]; // 标记该数字是否被使用过
        process1(result, collection, path, 0, nums);
        return result;
    }

    /**
     * 回溯实现没重复数的全排列 - 辅助函数
     * @param result 结果集
     * @param collection 回溯过程中收集到的结果
     * @param path 标记该数字是否被使用过
     * @param index 递归到哪个元素的下标
     * @param nums 需要排列数的数组
     */
    public static void process1(List<List<Integer>> result, List<Integer> collection, boolean[] path, int index, int[] nums) {
        if (index == nums.length) { // base case 下标越界就结束递归
            result.add(new ArrayList<Integer>(collection));  // 收集最终结果
            return ;
        }
        // 锁定一个数字，让其它数字进入递归
        for (int i = 0; i < nums.length; i++) {
            // 跳过锁定的数字
            if(path[i]) continue;
            // 锁定一个数字
            path[i] = true;
            // 记录递归过程
            collection.add(nums[i]);
            process1(result, collection, path, index + 1, nums);
            // 解锁数字
            path[i] = false;
            // 记录后移除这个下标下的结果,下一个循环才能记录下一结果
            collection.remove(index);
        }
    }

    /********** 第二种 不使用额外的数据结果 **************/
    public static List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        process2(result, 0, nums.length - 1, nums);
        return result;
    }

    public static void process2(List<List<Integer>> result, int start, int end, int[] nums) {
        if (start == end) { // base case 首尾指针相同，跳出递归
            List<Integer> tmp = new ArrayList<Integer>();
            for (int num : nums) {
                tmp.add(num);
            }
            result.add(tmp);
        }
        // 两两交换数组中数的位置
        for (int i = start; i <= end; i ++) {
            // 交换开始指针处和后面的数，包括 start 自己
            Unit.swap(nums, i, start);
            // 交换后往下递归，这样递归到最后收集全部数字时即为排序过的结果
            process2(result, start + 1, end, nums);
            // 交换回来，为了不影响其他位置数的排序
            Unit.swap(nums, start, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,4,5,6,7,8,9,10};

        List<List<Integer>> res;

        // 第一种方法
        long start = System.currentTimeMillis();
        res = permuteUnique1(arr);
//        System.out.println(res);
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        // 第二种方法
        start = System.currentTimeMillis();
        res = permuteUnique2(arr);
//        System.out.println(res);
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }

}
