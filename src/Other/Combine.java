package Other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合问题
 * 给定两个整数 n 和 k，返回 1 … n 中所有可能的 k 个数的组合。
 */
public class Combine {

    public static List<List<Integer>> combine (int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int nums[]=new int[n];//数组存储1-n
        boolean path[]=new boolean[n];//用于判断是否使用
        for(int i=0;i<n;i++)
        {
            nums[i]=i+1;
        }
        dfs(nums, -1, k, result, path, n);
        return result;
    }

    public static void dfs(int[] nums, int index, int count, List<List<Integer>> result, boolean[] path, int n) {
        if (count == 0) {
            List<Integer> tmp = new LinkedList<Integer>();
            for (int i = 0 ; i < n; i ++) {
                // 记录标记过的 因为这里的循环可能出现之前储存过的
                if (path[i]) tmp.add(i + 1);
            }
            result.add(tmp);
            return ;
        }
        // 从第 index + 1 个开始遍历，最多递归 count 次
        // 即从 index 后去挑选组合数
        for (int i = index + 1; i < n; i ++) {
            path[i] = true;
            dfs(nums, i, count - 1, result, path, n);
            path[i] = false;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res;
        res = combine(3, 2);
        System.out.println(res);
    }

}
