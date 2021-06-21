package cn.zju.leetcode;

import java.util.*;

public class code15_347 {
    public int[] topKFrequent(int[] nums, int k){
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num,0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if(queue.size() == k){
                if(queue.peek()[1] < count){
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            }else{
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
    public int[] topKFrequent1(int[] nums, int k){
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num,0) + 1);
        }
        int maxTimes = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxTimes = Math.max(entry.getValue(), maxTimes);
        }
        while(k > 0){
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if(entry.getValue() == maxTimes){
                    res[k - 1] = entry.getKey();
                    k--;
                }
            }
            maxTimes--;
        }
        return res;
    }
}
