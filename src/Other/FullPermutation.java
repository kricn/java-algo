package Other;

import Sort.Unit;

import java.util.*;

/**
 * 全排雷问题 时间复杂度 O(N*N!)
 */
public class FullPermutation {

    /********* 无重复数全排列第一种方法 ***************/
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
            // 记录后移除这个下标下的结果,下一个循环才能记录下一个结果
            collection.remove(index);
        }
    }

    /********** 无重复数全排列 第二种不使用额外的数据结果 **************/
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

    /********* 有重复数全排列第一种方法 ***************/
    public static List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> collection = new ArrayList<Integer>(); // 回溯过程中收集到的结果
        boolean path[] = new boolean[nums.length]; // 标记该数字是否被使用过
        Arrays.sort(nums);
        process3(result, collection, path, 0, nums);
        return result;
    }
    public static void process3(List<List<Integer>> result, List<Integer> collection, boolean[] path, int index, int[] nums) {
        int len = nums.length;
        if (index == len) {
            result.add(new ArrayList<Integer>(collection));
            return ;
        }
        for (int i = 0; i < len; i ++) {
            // 回溯剪枝
            // 因为数组是排序好的，所以第一次传入的数组中，相同的数字肯定是挨在一起的
            // 在重复数字中，排第一个肯定是先记录过一次
            // 在之后递归中，当遇到相同数字时，若其前一位没有被标记过，则说明遇到了重复顺序，需要跳过这种情况
            // 这里需要 i 大于 1 时才做重复判断
            // 一来是防止数组越界，二来是这样判断才有效
            // 在 i > 1 后，取到相同的数，之后的数和上一次出现的数之后的数是相同的
            // 如 [1,2,1,1], 1 1(1) 2 1(2) 和 1 1(2) 2 1(1) 是一样的
            if(path[i] || (i > 0 && nums[i]==nums[i-1] && !path[i - 1]))
                continue;
            // 锁定一个数字
            path[i] = true;
            // 记录递归过程
            collection.add(nums[i]);
            process3(result, collection, path, index + 1, nums);
            // 解锁数字
            path[i] = false;
            // 记录后移除这个下标下的结果,下一个循环才能记录下一个结果
            collection.remove(index);
        }
    }

    /********* 有重复数全排列第二种方法 ***************/
    public static List<List<Integer>> permuteUnique4(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        process4(result, 0, nums.length - 1, nums);
        return result;
    }

    public static void process4(List<List<Integer>> result, int start, int end, int[] nums) {
        if (start == end) { // base case 首尾指针相同，跳出递归
            List<Integer> tmp = new ArrayList<Integer>();
            for (int num : nums) {
                tmp.add(num);
            }
            result.add(tmp);
        }
        // 记录重复数的字典，遇到重复数则不再进行减缓
        Set<Integer> set=new HashSet<Integer>();
        // 两两交换数组中数的位置
        for (int i = start; i <= end; i ++) {
            if(set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            // 交换开始指针处和后面的数，包括 start 自己
            Unit.swap(nums, i, start);
            // 交换后往下递归，这样递归到最后收集全部数字时即为排序过的结果
            process4(result, start + 1, end, nums);
            // 交换回来，为了不影响其他位置数的排序
            Unit.swap(nums, start, i);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,4,5,6,7,8,9};

        List<List<Integer>> res;

        /***** 无重复数 ********/
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

        /***** 有重复数 ********/
        int[] arr2 = {1,2,1,1};

        // 第一种方法
        start = System.currentTimeMillis();
        res = permuteUnique3(arr2);
        System.out.println(res);
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        // 第二种方法
        start = System.currentTimeMillis();
        res = permuteUnique4(arr2);
        System.out.println(res);
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }

}
