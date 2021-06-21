package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code5_448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(nums[i] <= n) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
