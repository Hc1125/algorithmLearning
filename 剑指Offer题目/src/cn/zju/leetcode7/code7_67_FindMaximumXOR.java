package cn.zju.leetcode7;

import java.util.HashSet;
import java.util.Set;

public class code7_67_FindMaximumXOR {

    public int findMaximumXOR1(int[] nums) {
        int x = 0;
        for (int k = 30; k >= 0; k--) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num >> k);
            }
            int xNext = x * 2 + 1;
            boolean found = false;
            for (int num : nums) {
                if (set.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }
            if (found) {
                x = xNext;
            } else {
                x = xNext - 1;
            }
        }
        return x;
    }

    public class Node {
        Node left;
        Node right;
    }
    public void add(Node root, int num) {
        Node cur = root;
        for (int k = 30; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Node();
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Node();
                }
                cur = cur.right;
            }
        }
    }

    public int check(Node root, int num) {
        Node cur = root;
        int x = 0;
        for (int k = 30; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.right != null) {
                    cur = cur.right;
                    x = 2 * x + 1;
                } else {
                    cur = cur.left;
                    x *= 2;
                }
            } else {
                if (cur.left != null) {
                    cur = cur.left;
                    x = 2 * x + 1;
                } else {
                    cur = cur.right;
                    x *= 2;
                }
            }
        }
        return x;
    }

    public int findMaximumXOR2(int[] nums) {
        int n = nums.length;
        int x = 0;
        Node root = new Node();
        for (int i = 1; i < n; i++) {
            // ??? nums[i-1] ???????????????????????? nums[0 .. i-1] ??????????????????
            add(root, nums[i - 1]);
            // ??? nums[i] ?????? ai?????????????????? x ????????????
            x = Math.max(x, check(root, nums[i]));
        }
        return x;
    }
}
