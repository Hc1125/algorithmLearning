package cn.zju.leetcode;

import java.util.*;

public class code4_671 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public int findSecondMinimumValue1(TreeNode root) {
        if (root == null) {
            return -1;
        }
        TreeSet<Integer> set = new TreeSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        set.add(root.val);
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    set.add(node.left.val);
                    set.add(node.right.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        if (set.size() > 1) {
            set.remove(root.val);
            return set.first();
        }
        return -1;
    }

    int ans;
    int rootVal;
    public int findSecondMinimumValue2(TreeNode root) {
        ans = -1;
        rootVal = root.val;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (ans != -1 && node.val >= ans ) {
            return;
        }
        if (node.val > rootVal) {
            ans = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }
}
