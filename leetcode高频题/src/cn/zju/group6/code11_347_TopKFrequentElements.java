package cn.zju.group6;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class code11_347_TopKFrequentElements {
    public class Node {
        public int num;
        public int count;

        public Node(int num) {
            this.num = num;
            this.count = 1;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Node> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new Node(num));
            } else {
                map.get(num).count++;
            }
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.count - b.count));
        for (Node node : map.values()) {
            if (heap.size() < k || (heap.size() == k && node.count > heap.peek().count)) {
                heap.add(node);
            }
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().num;
        }
        return ans;
    }
}
