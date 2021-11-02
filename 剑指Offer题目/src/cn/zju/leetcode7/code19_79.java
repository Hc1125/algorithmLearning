package cn.zju.leetcode7;

import java.util.ArrayList;
import java.util.List;

public class code19_79 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, 0, path, ans);
        return ans;
    }

    public void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        dfs(nums, index + 1, path, ans);
        path.add(nums[index]);
        dfs(nums, index + 1, path, ans);
        path.remove(path.size() - 1);
    }

}
