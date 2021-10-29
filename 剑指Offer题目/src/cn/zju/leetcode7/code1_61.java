package cn.zju.leetcode7;

import java.util.*;

public class code1_61 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        Set<Integer> set = new HashSet<>();
        set.add(0);
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[1], y = node[2];
            ans.add(Arrays.asList(nums1[x], nums2[y]));
            if (ans.size() == k) {
                return ans;
            }
            if (x < m - 1) {
                if (set.add((x + 1) * n + y)) {
                    pq.offer(new int[]{nums1[x + 1] + nums2[y], x + 1, y});
                }
            }
            if (y < n - 1) {
                if (set.add(x * n + y + 1)) {
                    pq.offer(new int[]{nums1[x] + nums2[y + 1], x, y + 1});
                }
            }
        }
        return ans;
    }
}
