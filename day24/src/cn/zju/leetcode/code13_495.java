package cn.zju.leetcode;

public class code13_495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int begin = timeSeries[0];
        int end = timeSeries[0] + duration - 1;
        int ans = 0;
        for (int time : timeSeries) {
            if (time > end) {
                ans += end - begin + 1;
                begin = time;
            }
            end = time + duration - 1;
        }
        ans += end - begin + 1;
        return ans;
    }
}
