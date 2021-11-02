package cn.zju.leetcode;

import java.util.*;

public class code4_1654 {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int g = gcd(a, b);
        if ((x % g) != 0) {
            return -1;
        }
        boolean[] vis = new boolean[6001];
        boolean[] ban = new boolean[2001];
        for (int v : forbidden) {
            vis[v] = true;
            ban[v] = true;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (a >= b && node[1] > x) {
                continue;
            }
            if (node[1] == x) {
                return node[0];
            }
            if (!vis[node[1]]) {
                vis[node[1]] = true;
                if (node[1] + a <= 6000 && !vis[node[1] + a]) {
                    pq.offer(new int[]{node[0] + 1, node[1] + a});
                }
                if (node[1] + a - b <= 6000 && node[1] + a - b >= 0 && !vis[node[1] + a - b] && !(node[1] + a <= 2000 && ban[node[1] + a])) {
                    pq.offer(new int[]{node[0] + 2, node[1] + a - b});
                }
            }
        }
        return -1;
    }
    public int gcd (int x, int y) {
        return x % y == 0 ? y : gcd(y, x % y);
    }
}
