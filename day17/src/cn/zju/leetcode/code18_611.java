package cn.zju.leetcode;

import java.util.Arrays;

public class code18_611 {

    public int triangleNumber1(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (isValid(nums, i, j, k)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public boolean isValid(int[] nums, int i, int j, int k) {
        int a = nums[i];
        int b = nums[j];
        int c = nums[k];
        return (a + b) > c && (a + c) > b && (b + c) > a;
    }
    public int triangleNumber2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 核心
            int k = i;
            for (int j = i + 1; j < n; j++) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    k++;
                }
                ans += Math.max(k - j, 0);
            }
        }
        return ans;
    }
}
