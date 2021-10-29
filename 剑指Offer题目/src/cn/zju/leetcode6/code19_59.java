package cn.zju.leetcode6;

import java.util.PriorityQueue;

public class code19_59 {
    class KthLargest {
        PriorityQueue<Integer> pq;
        int size;
        public KthLargest(int k, int[] nums) {
            size = k;
            pq = new PriorityQueue<>();
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (pq.size() < size) {
                pq.offer(val);
            } else {
                if (val > pq.peek()) {
                    pq.poll();
                    pq.offer(val);
                }
            }
            return pq.peek();
        }
    }
}
