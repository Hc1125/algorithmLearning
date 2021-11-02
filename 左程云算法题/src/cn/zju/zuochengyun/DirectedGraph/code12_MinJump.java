package cn.zju.zuochengyun.DirectedGraph;

import java.util.PriorityQueue;

/**
 * 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
 *
 * 跳蚤跳跃的规则如下：
 *
 * 它可以 往前 跳恰好 a 个位置（即往右跳）。
 * 它可以 往后 跳恰好 b 个位置（即往左跳）。
 * 它不能 连续 往后跳 2 次。
 * 它不能跳到任何 forbidden 数组中的位置。
 * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
 *
 * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。
 * 如果没有恰好到达 x 的可行方案，请你返回 -1 。
 */
public class code12_MinJump {
    /**
     * 可以证明，一定可以在下标 [0,M + a + b][0,M+a+b] 的范围内找到最优解，M = max(ban[i],x),也就是说搜索范围不会超过 6000。
     * 因为不能连续后退，所以每步要么走 a，要么走 a-b
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int g = gcd(a, b);
        if ((x % g) != 0) {
            return -1;
        }
        boolean[] vis = new boolean[6001];
        for (int v : forbidden) {
            vis[v] = true;
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
                if (node[1] + a - b <= 6000 && node[1] + a - b >= 0 &&
                        !vis[node[1] + a - b] && !(node[1] + a <= 2000 && vis[node[1] + a])) {
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
