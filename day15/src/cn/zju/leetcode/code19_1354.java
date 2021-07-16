package cn.zju.leetcode;

import java.util.*;

public class code19_1354 {
    public boolean isPossible(int[] target) {
        if (target.length == 1) {
            return target[0] == 1;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o2.intValue() - o1.intValue();
            }
        });
        long sum = 0;
        for (int i : target) {
            sum += i;
            pq.offer(new Long(i));
        }
        while (true) {
            if (pq.peek() == 1) {
                return true;
            }
            long first = pq.poll();
            long second = pq.peek();
            long reduce = sum - first;
            long reduceCnt = 0;
            if (second == 1) {
                reduceCnt = Math.max((first - second) / reduce, 1L);
            } else {
                reduceCnt = (first - second) / reduce + 1;
            }
            first -= reduce * reduceCnt;
            sum -= reduce * reduceCnt;
            if (first < 1) {
                return false;
            }
            pq.offer(first);
        }
    }
}

