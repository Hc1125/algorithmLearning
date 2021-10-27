package cn.zju.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class code17_919 {
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
    class CBTInserter {
        TreeNode root;
        Deque<TreeNode> deque;
        public CBTInserter(TreeNode root) {
            this.root = root;
            deque = new LinkedList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left == null || node.right == null)
                    deque.offerLast(node);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }

        public int insert(int val) {
            TreeNode node = deque.peekFirst();
            deque.offerLast(new TreeNode(val));
            if (node.left == null) {
                node.left = deque.peekLast();
            } else {
                node.right = deque.peekLast();
                deque.pollFirst();
            }
            return node.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}
