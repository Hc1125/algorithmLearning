package cn.zju.leetcode;

import java.util.Arrays;

public class code8_1235____________________ {
    public class Program {
        public int start;
        public int end;
        public int profit;

        public Program(int s, int e, int p) {
            start = s;
            end = e;
            profit = p;
        }
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Program[] programs = new Program[n];
        for (int i = 0; i < n; i++) {
            programs[i] = new Program(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(programs, (o1, o2) -> (o1.end - o2.end));
        return process(programs, 0, n, 0);
    }

    public int process(Program[] programs, int index, int n, int timeLine) {
        if (index == n) {
            return 0;
        }
        if (programs[index].start >= timeLine) {
            return Math.max(process(programs, index + 1, n, programs[index].end) + programs[index].profit,
                    process(programs, index + 1, n, timeLine));
        } else {
            return process(programs, index + 1, n, timeLine);
        }
    }


    public int dp(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Program[] programs = new Program[n];
        for (int i = 0; i < n; i++) {
            programs[i] = new Program(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(programs, (o1, o2) -> (o1.end - o2.end));
        int maxTime = programs[n - 1].end;
        int[][] dp = new int[n + 1][maxTime + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= maxTime; j++) {
                dp[i][j] = dp[i + 1][j];
                if (programs[i].start >= j) {
                    dp[i][j] = Math.max(dp[i + 1][programs[i].end] + programs[i].profit, dp[i][j]);
                }
            }
        }
        return dp[0][0];
    }
    public int dp1(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Program[] programs = new Program[n];
        for (int i = 0; i < n; i++) {
            programs[i] = new Program(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(programs, (o1, o2) -> (o1.end - o2.end));
        int maxTime = programs[n - 1].end;
        int[] dp = new int[maxTime + 1];
        int temp = 0;
        for (int i = n - 1; i >= 0; i--) {
            temp = dp[programs[i].end] + programs[i].profit;
            for (int j = programs[i].start; j >= 0; j--) {
                dp[j] = Math.max(temp, dp[j]);
            }
        }
        return dp[0];
    }
}
