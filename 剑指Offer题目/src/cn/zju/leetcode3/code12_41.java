package cn.zju.leetcode3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class code12_41 {
    class MedianFinder {
        PriorityQueue<Integer> minpq;
        PriorityQueue<Integer> maxpq;
        public MedianFinder() {
            minpq = new PriorityQueue<>((x, y) -> (y - x));
            maxpq = new PriorityQueue<>();
        }
        public void addNum(int num) {
            if (minpq.size() == maxpq.size()) {
                maxpq.offer(num);
                minpq.offer(maxpq.poll());
            } else {
                minpq.offer(num);
                maxpq.offer(minpq.poll());
            }
        }
        public double findMedian() {
            if (minpq.size() > maxpq.size()) {
                return minpq.peek();
            } else {
                return (double) (minpq.peek() + maxpq.peek()) / 2;
            }
        }
    }
}
