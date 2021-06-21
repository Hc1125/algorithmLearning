package cn.zju.leetcode2;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class code20_59_I {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) {
            return new int[]{};
        }
        int[] ans = new int[n - k + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{i, nums[i]});
        }
        int index = 0;
        ans[index++] = pq.peek()[1];
        for (int i = k; i < n; i++) {
            pq.offer(new int[]{i, nums[i]});
            while (pq.peek()[0] <= i - k) {
                pq.poll();
            }
            ans[index++] = pq.peek()[1];
        }
        return ans;
    }
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
}
