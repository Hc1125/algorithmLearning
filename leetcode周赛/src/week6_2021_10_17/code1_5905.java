package week6_2021_10_17;

import java.util.*;

/**
 * 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。
 * 图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。
 * 每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
 *
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。
 * 你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
 *
 * 第二小的值 是 严格大于 最小值的所有值中最小的值。
 *
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 *
 * 注意：
 *
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 */
public class code1_5905 {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            int x = e[0] - 1;
            int y = e[1] - 1;
            graph.putIfAbsent(x, new ArrayList<>());
            graph.putIfAbsent(y, new ArrayList<>());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        List<Set<Integer>> dist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dist.add(new HashSet<>());
        }
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[n];
        q.offer(0);
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            Set<Integer> y_set = new HashSet<>();
            for (int i = 0; i < size; i++) {
                int x = q.poll();
                if (visited[x] < 2) {
                    visited[x]++;
                    for (int y : graph.get(x)) {
                        dist.get(y).add(step + 1);
                        y_set.add(y);
                        if (dist.get(n - 1).size() == 2) {
                            return calculate(step, time, change);
                        }
                    }
                }
            }
            for (int y : y_set) {
                q.offer(y);
            }
        }
        return -1;
    }

    public int calculate(int step, int time, int change) {
        int ans = 0;
        for (int i = 0; i < step; i++) {
            int x = ans / change;
            if (x % 2 == 1) {
                ans = (x + 1) * change;
            }
            ans += time;
        }
        return ans;
    }
}
