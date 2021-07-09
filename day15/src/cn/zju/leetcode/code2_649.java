package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code2_649 {
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        char[] chars = senate.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int r = radiant.poll(), d = dire.poll();
            if (r < d) {
                radiant.add(r + n);
            } else {
                dire.add(d + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }
}
