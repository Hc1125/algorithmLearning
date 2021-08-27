package cn.zju.leetcode;

import java.util.PriorityQueue;

public class code3_295 {
    class MedianFinder {
        PriorityQueue<Integer> pqMin;
        PriorityQueue<Integer> pqMax;
        public MedianFinder() {
            pqMax = new PriorityQueue<>((a, b) -> (a - b));
            pqMin = new PriorityQueue<>((a, b) -> (b - a));
        }

        public void addNum(int num) {
            if (pqMin.isEmpty() || num <= pqMin.peek()) {
                pqMin.add(num);
            } else {
                pqMax.add(num);
            }
            balance();
        }

        public double findMedian() {
            return pqMax.size() == pqMin.size() ? (double) (pqMax.peek() + pqMin.peek()) / 2 :
                    (pqMin.size() > pqMax.size() ? pqMin.peek() : pqMax.peek());
        }

        public void balance() {
            if (Math.abs(pqMax.size() - pqMin.size()) == 2) {
                if (pqMax.size() > pqMin.size()) {
                    pqMin.add(pqMax.poll());
                } else {
                    pqMax.add(pqMin.poll());
                }
            }
        }
    }
}
