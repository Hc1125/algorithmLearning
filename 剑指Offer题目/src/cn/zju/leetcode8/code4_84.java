package cn.zju.leetcode8;

import java.util.*;

public class code4_84 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] vis = new boolean[nums.length];
        process(nums, 0, nums.length, path, ans, vis);
        return ans;
    }

    public void process(int[] nums, int index, int n, Deque<Integer> path, List<List<Integer>> ans, boolean[] vis) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            vis[i] = true;
            path.addLast(nums[i]);
            process(nums, index + 1, n, path, ans, vis);
            vis[i] = false;
            path.removeLast();
        }
    }

}
