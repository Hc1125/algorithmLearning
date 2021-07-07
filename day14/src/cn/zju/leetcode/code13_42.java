package cn.zju.leetcode;

public class code13_42 {
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int maxLeft = height[0];
        int maxRight = height[height.length - 1];
        int i = 1;
        int j = height.length - 2;
        int ans = 0;
        while (i <= j) {
            if (maxLeft < maxRight) {
                ans += Math.max(0, maxLeft - height[i]);
                maxLeft = Math.max(maxLeft, height[i]);
                i++;
            } else {
                ans += Math.max(0, maxRight - height[j]);
                maxRight = Math.max(maxRight, height[j]);
                j--;
            }
        }
        return ans;
    }
}
