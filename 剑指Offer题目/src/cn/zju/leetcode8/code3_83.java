package cn.zju.leetcode8;

import java.util.ArrayList;
import java.util.List;

public class code3_83 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(nums, 0, nums.length, ans);
        return ans;
    }
    public void process(int[] nums, int index, int n, List<List<Integer>> ans) {
        if (index == n) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            ans.add(list);
            return;
        }
        for (int i = index; i < n; i++) {
            swap(nums, index, i);
            process(nums, index + 1, n, ans);
            swap(nums, index, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
