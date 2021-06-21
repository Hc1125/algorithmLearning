package cn.zju.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class code2_1046 {
    private static int lastStoneWeight1(int[] stones){
        int n = stones.length;
        if(n == 1){
            return stones[0];
        }
        Arrays.sort(stones);
        while(stones[n-1] != 0 && stones[n-2] != 0){
            stones[n-1] = stones[n-1] - stones[n-2];
            stones[n-2] = 0;
            Arrays.sort(stones);
        }
        return stones[n-1];
    }
    private static int lastStoneWeight2(int[] stones){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }
        while(pq.size()>1){
            int a = pq.poll();
            int b = pq.poll();
            if(a > b){
                pq.offer(a-b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
