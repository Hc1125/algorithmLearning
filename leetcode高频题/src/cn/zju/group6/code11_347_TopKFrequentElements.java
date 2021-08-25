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

    // 改写随机快排
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Node> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new Node(num));
            } else {
                map.get(num).count++;
            }
        }
        Node[] nodes = new Node[map.size()];
        int i = 0;
        for (Node value : map.values()) {
            nodes[i++] = value;
        }
        Node tar = process(nodes, 0, map.size() - 1, k - 1);
        int[] ans = new int[k];
        i = 0;
        for (Node node : nodes) {
            if (node.count >= tar.count) {
                ans[i++] = node.num;
            }
        }
        return ans;
    }

    public Node process(Node[] nodes, int L, int R, int k) {
        if (L == R) {
            return nodes[L];
        }
        Node pivot = nodes[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(nodes, L, R, pivot);
        if (range[0] > k) {
            return process(nodes, L, range[0] - 1, k);
        } else if (range[1] < k) {
            return process(nodes, range[1] + 1, R, k);
        } else {
            return nodes[k];
        }
    }

    public int[] partition(Node[] nodes, int L, int R, Node pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (nodes[cur].count > pivot.count) {
                swap(nodes, ++less, cur++);
            } else if (nodes[cur].count < pivot.count) {
                swap(nodes, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public void swap(Node[] nodes, int i, int j) {
        Node tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }



}
