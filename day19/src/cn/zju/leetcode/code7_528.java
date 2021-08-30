package cn.zju.leetcode;

import java.util.Arrays;
import java.util.TreeMap;

public class code7_528 {
    class Solution1 {
        TreeMap<Integer, Integer> treeMap;
        int limit;
        public Solution1(int[] w) {
            treeMap = new TreeMap<>();
            int sum = 0;
            for (int i = 0; i < w.length; i++) {
                sum += w[i];
                treeMap.put(sum, i);
            }
            limit = sum;
        }

        public int pickIndex() {
            int index = (int) (Math.random() * limit + 1);
            int key = treeMap.ceilingKey(index);
            return treeMap.get(key);
        }
    }

    class Solution2 {
        int[] pre;
        int total;

        public Solution2(int[] w) {
            pre = new int[w.length];
            pre[0] = w[0];
            for (int i = 1; i < w.length; ++i) {
                pre[i] = pre[i - 1] + w[i];
            }
            total = Arrays.stream(w).sum();
        }

        public int pickIndex() {
            int x = (int) (Math.random() * total) + 1;
            return binarySearch(x);
        }

        private int binarySearch(int x) {
            int low = 0, high = pre.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (pre[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

}
