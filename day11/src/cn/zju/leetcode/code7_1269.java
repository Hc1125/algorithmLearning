package cn.zju.leetcode;

import org.w3c.dom.ls.LSOutput;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class code7_1269 {
    public int numWays(int steps, int arrLen) {
        return dp(steps, arrLen);
    }
    public int process(int step, int i, int arrLen) {
        if (step == 0) {
            return i == 0 ? 1 : 0;
        }
        if (i == 0) {
            return process(step - 1, i + 1, arrLen) + process(step - 1, i, arrLen);
        } else if (i == arrLen - 1) {
            return process(step - 1, i - 1, arrLen) + process(step - 1, i, arrLen);
        } else {
            return process(step - 1, i + 1, arrLen) + process(step - 1, i - 1, arrLen) + process(step - 1, i, arrLen);
        }
    }

    public int dp(int steps, int arrLen) {
        int base = (int)Math.pow(10,9) + 7;
        int[][] dp = new int[steps + 1][arrLen];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < arrLen; j++) {
                if (j == 0) {
                    dp[i][j] = (int)(((long)dp[i - 1][j + 1] + (long)dp[i - 1][j]) % base);
                } else if (j == arrLen - 1) {
                    dp[i][j] = (int)(((long)dp[i - 1][j - 1] + (long)dp[i - 1][j]) % base);
                } else {
                    dp[i][j] = (int)(((long)dp[i - 1][j - 1] + (long)dp[i - 1][j] + (long)dp[i - 1][j + 1]) % base);
                }
            }
        }
        return dp[steps][0];
    }
    public int dp1(int steps, int arrLen) {
        int base = (int)Math.pow(10,9) + 7;
        // 关键优化，剔除无用位置计算，以及节省dp空间
        arrLen = Math.min(arrLen, steps + 1);
        int[] dp = new int[arrLen];
        dp[0] = 1;
        for (int i = 1; i <= steps; i++) {
            int last = 0;
            // 关键优化，剔除无用计算，当j > steps - i就不可能走回去
            for (int j = 0; j < arrLen && j <= steps - i; j++) {
                int temp = dp[j];
                if (j == 0) {
                    dp[j] = (dp[j + 1] + dp[j]) % base;
                } else if (j == arrLen - 1) {
                    dp[j] = (last + dp[j]) % base;
                } else {
                    dp[j] = ((last + dp[j]) % base + dp[j + 1]) % base;
                }
                last = temp;
            }
        }
        return dp[0];
    }

}
