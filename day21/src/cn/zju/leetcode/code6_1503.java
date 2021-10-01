package cn.zju.leetcode;

public class code6_1503 {
    public int getLastMoment(int n, int[] left, int[] right) {
        int max = 0;
        for (int i : left) {
            max = Math.max(max, i);
        }
        for (int i : right) {
            max = Math.max(max, n - i);
        }
        return max;
    }
}
