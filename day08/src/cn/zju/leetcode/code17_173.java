package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class code17_173 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    class BSTIterator1 {
        List<Integer> list;
        int size;
        int index;
        public BSTIterator1(TreeNode root) {
            list = new LinkedList<>();
            list.add(-1);
            if (root != null) {
                Deque<TreeNode> stack = new ArrayDeque<>();
                while (!stack.isEmpty() || root != null) {
                    if (root != null) {
                        stack.push(root);
                        root = root.left;
                    } else {
                        root = stack.pop();
                        list.add(root.val);
                        root = root.right;
                    }
                }
            }
            size = list.size();
            index = 0;
        }

        public int next() {
            return list.get(++index);
        }

        public boolean hasNext() {
            return index < size - 1;
        }
    }
    class BSTIterator {
        private TreeNode cur;
        private Deque<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            cur = root;
            stack = new LinkedList<TreeNode>();
        }
        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }
        public boolean hasNext() {
            return !stack.isEmpty() || cur != null;
        }

    }
}
