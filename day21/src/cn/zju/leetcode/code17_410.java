package cn.zju.leetcode;

public class code17_410 {
    public int splitArray1(int[] nums, int m) {
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        int l = max, r = sum;
        int mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (getNeedPerson(nums, mid) <= m) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int getNeedPerson(int[] nums, int aim) {
        int count = 1;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum + nums[i] > aim) {
                count++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return count;
    }
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[n][m + 1];
        int[][] best = new int[n][m + 1];
        for (int i = 1; i <= m; i++) {
            dp[0][i] = nums[0];
            best[0][i] = -1;
        }
        for (int i = 1; i < n; i++) {
            dp[i][1] = sum[i + 1];
            best[i][1] = -1;
        }
        for (int j = 2; j <= m; j++) {
            for (int i = n - 1; i >= 1; i--) {
                int down = best[i][j - 1];
                int up = i == n - 1 ? n - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum[i + 1] - sum[leftEnd + 1];
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }
        }
        return dp[n - 1][m];
    }
}
