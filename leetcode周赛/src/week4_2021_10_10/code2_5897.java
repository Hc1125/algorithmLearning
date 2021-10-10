package week4_2021_10_10;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 给你一个长度为 2 * n 的整数数组。你需要将 nums 分成两个长度为 n 的数组，分别求出两个数组的和，并最小化两个数组和之差的绝对值 。
 * nums 中每个元素都需要放入两个数组之一。
 *
 * 请你返回 最小 的数组和之差。
 */
public class code2_5897 {
    public int minimumDifference(int[] nums) {
        Map<Integer, TreeSet<Integer>> left = new HashMap<>();
        Map<Integer, TreeSet<Integer>> right = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int total = 0;
        int n = nums.length / 2;
        for (int i = 0; i < 2 * n; i++) {
            total += nums[i];
            if (i < n) {
                left.put(i + 1, new TreeSet<>());
            } else {
                right.put(i - n + 1, new TreeSet<>());
            }
        }
        dfs(nums, 0, 0, 0, n, left);
        dfs(nums, 0, 0, n, 2 * n, right);
        for (int i = 1; i < n; i++) {
            TreeSet<Integer> set = left.get(i);
            for (int leftSum : set) {
                Integer rightSum = right.get(n - i).ceiling(total / 2 - leftSum);
                if (rightSum != null) {
                    int sum = leftSum + rightSum;
                    min = Math.min(min, Math.abs(sum * 2 - total));
                }
                if (min == 0) {
                    return 0;
                }
            }
        }
        TreeSet<Integer> set = left.get(n);
        for (int sum : set) {
            min = Math.min(min, Math.abs(sum * 2 - total));
        }
        return min;
    }
    public void dfs(int[] nums, int sum, int count, int idx, int limit, Map<Integer, TreeSet<Integer>> visited) {
        if (visited.containsKey(count)) {
            visited.get(count).add(sum);
        }
        if (idx == limit) return;
        dfs(nums, sum + nums[idx], count + 1, idx + 1, limit, visited);
        dfs(nums, sum, count, idx + 1, limit, visited);
    }
}
