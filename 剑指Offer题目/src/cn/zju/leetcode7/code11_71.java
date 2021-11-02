package cn.zju.leetcode7;

import java.util.TreeMap;

public class code11_71 {
    class Solution {
        int sum;
        int[] pre;
        TreeMap<Integer, Integer> treeMap;
        public Solution(int[] w) {
            pre = new int[w.length];
            sum = w[0];
            pre[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                pre[i] = pre[i - 1] + w[i];
                sum += w[i];
            }
        }

        public int pickIndex() {
            int num = (int)(Math.random() * sum) + 1;
            return binarySearch(num);
        }

        public int binarySearch(int x) {
            int l = 0, r = pre.length - 1;
            while (l < r) {
                int m = l + ((r - l) >> 1);
                if (pre[m] < x) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            return l;
        }
    }
}
