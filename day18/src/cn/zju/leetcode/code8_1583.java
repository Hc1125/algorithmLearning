package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code8_1583 {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] order = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                order[i][preferences[i][j]] = j;
            }
        }
        Set<Integer> set = new HashSet<>();
        int[] match = new int[n];
        for (int[] pair : pairs) {
            int i = pair[0];
            int j = pair[1];
            if (order[i][j] == 0) {
                set.add(i);
            }
            if (order[j][i] == 0) {
                set.add(j);
            }
            match[i] = j;
            match[j] = i;
        }
        int unhappy = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }
            int j = match[i];
            int index = order[i][j];
            for (int k = 0; k < index; k++) {
                int u = preferences[i][k];
                int v = match[u];
                if (order[u][i] < order[u][v]) {
                    unhappy++;
                    break;
                }
            }
        }
        return unhappy;
    }
}
