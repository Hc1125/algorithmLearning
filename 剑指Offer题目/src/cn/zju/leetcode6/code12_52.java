package cn.zju.leetcode6;

import java.util.LinkedList;
import java.util.Queue;

public class code12_52 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    public TreeNode increasingBST(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        dfs(root, queue);
        TreeNode head = queue.peek();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            node.left = null;
            node.right = queue.peek();
        }
        return head;
    }
    public void dfs(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) {
            return;
        }
        dfs(root.left, queue);
        queue.add(root);
        dfs(root.right, queue);
    }

}
