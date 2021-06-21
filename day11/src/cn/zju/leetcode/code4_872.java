package cn.zju.leetcode;

import java.util.*;

public class code4_872 {
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

        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> list = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.addLast(root1);
            while (!stack.isEmpty()) {
                TreeNode temp = stack.pollLast();
                if (temp.left == null && temp.right == null) {
                    list.add(temp.val);
                } else {
                    if (temp.right != null) {
                        stack.addLast(temp.right);
                    }
                    if (temp.left != null) {
                        stack.addLast(temp.left);
                    }
                }
            }
            int n = list.size();
            int index = 0;
            stack.add(root2);
            while (!stack.isEmpty()) {
                TreeNode temp = stack.pollLast();
                if (temp.left == null && temp.right == null) {
                    if (index >= n || temp.val != list.get(index++)) {
                        return false;
                    }
                } else {
                    if (temp.right != null) {
                        stack.addLast(temp.right);
                    }
                    if (temp.left != null) {
                        stack.addLast(temp.left);
                    }
                }

            }
            return index == n;
        }


    }
}
