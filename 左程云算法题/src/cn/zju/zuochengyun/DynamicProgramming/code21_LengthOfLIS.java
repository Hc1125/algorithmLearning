package cn.zju.zuochengyun.DynamicProgramming;

public class code21_LengthOfLIS {
    // 最长递增子序列的长度
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;
                while (l < r) {
                    int mid = l + ((r - l) >> 1);
                    if (d[mid] < nums[i]) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                d[l] = nums[i];
            }

        }
        return len;
    }
}
