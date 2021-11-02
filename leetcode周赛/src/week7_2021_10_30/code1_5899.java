package week7_2021_10_30;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei, valuei] 。
 * 第 i 个活动开始于 startTimei ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。
 * 你 最多 可以参加 两个时间不重叠 活动，使得它们的价值之和 最大 。
 *
 * 请你返回价值之和的 最大值 。
 *
 * 注意，活动的开始时间和结束时间是 包括 在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。
 * 更具体的，如果你参加一个活动，且结束时间为 t ，那么下一个活动必须在 t + 1 或之后的时间开始。
 *
 */
public class code1_5899 {
    public int maxTwoEvents1(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int n = events.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(events[n - 1][0], events[n - 1][2]);
        int ans = events[n - 1][2];
        int index = n - 2;
        while (index >= 0) {
            Integer key1 = map.ceilingKey(events[index][1] + 1);
            if (key1 != null) {
                ans = Math.max(ans, events[index][2] + map.get(key1));
            }
            Integer key2 = map.ceilingKey(events[index][0]);
            if (key2 != null) {
                if (events[index][2] > map.get(key2)) {
                    map.put(events[index][0], events[index][2]);
                }
            } else {
                map.put(events[index][0], events[index][2]);
            }
            ans = Math.max(ans, events[index][2]);
            index--;
        }
        return ans;
    }

    public int maxTwoEvents2(int[][] events) {
        int n = events.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, events[i][2]);
        }
        Arrays.sort(events, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && events[i][2] > events[stack.peekLast()][2]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (!stack.isEmpty()) {
            int index = stack.pollFirst();
            treeMap.put(events[index][0], events[index][2]);
        }
        for (int i = 0; i < events.length - 1; i++) {
            Integer key = treeMap.ceilingKey(events[i][1] + 1);
            if (key != null) {
                ans = Math.max(ans, events[i][2] + treeMap.get(key));
            }

        }
        return ans;
    }
}
