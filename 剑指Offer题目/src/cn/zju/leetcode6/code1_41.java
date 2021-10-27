package cn.zju.leetcode6;

import java.util.LinkedList;
import java.util.Queue;

public class code1_41 {
    class MovingAverage {
        int capacity;
        Queue<Integer> queue;
        double sum;
        public MovingAverage(int size) {
            sum = 0;
            capacity = size;
            queue = new LinkedList<>();
        }

        public double next(int val) {
            queue.add(val);
            sum += val;
            if (queue.size() > capacity) {
                sum -= queue.poll();
            }
            return sum / queue.size();
        }
    }
}
