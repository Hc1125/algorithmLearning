package cn.zju.leetcode;


import java.util.HashMap;
import java.util.Map;

public class code11_621 {
    public int leastInterval(char[] tasks, int n) {
        int[] table = new int[26];
        int maxExec = 0;
        for (char task : tasks) {
            table[task - 'A']++;
            maxExec = Math.max(maxExec, table[task - 'A']);
        }
        int maxCount = 0;
        for (int i : table) {
            if (i == maxExec) {
                maxCount++;
            }
        }
        return Math.max((n + 1) * (maxExec - 1) + maxCount, tasks.length);
    }
}
