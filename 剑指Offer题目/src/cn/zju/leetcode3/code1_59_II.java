package cn.zju.leetcode3;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class code1_59_II {
    class MaxQueue {
        Queue<Integer> q;
        Deque<Integer> d;
        public MaxQueue() {
            q = new LinkedList<>();
            d = new LinkedList<>();
        }

        public int max_value() {
            if (d.isEmpty()) {
                return -1;
            }
            return d.peekFirst();
        }

        public void push_back(int value) {
            q.offer(value);
            while (!d.isEmpty() && value > d.peekLast()) {
                d.pollLast();
            }
            d.offerLast(value);
        }

        public int pop_front() {
            if (q.isEmpty()) {
                return -1;
            }
            int ans = q.poll();
            if (ans == d.peekFirst()) {
                d.pollFirst();
            }
            return ans;
        }
    }
}
