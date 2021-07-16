package cn.zju.leetcode;

import java.util.LinkedList;

public class code18_662 {
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
    public class PosNode {
        TreeNode node;
        int pos;
        public PosNode(TreeNode node, int pos) {
            this.node = node;
            this.pos = pos;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        LinkedList<PosNode> queue = new LinkedList<>();
        queue.addLast(new PosNode(root, 0));
        while (!queue.isEmpty()) {
            ans = Math.max(ans, queue.peekLast().pos - queue.peekFirst().pos + 1);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                PosNode posNode = queue.pollFirst();
                TreeNode node = posNode.node;
                int pos = posNode.pos;
                if (node.left != null) {
                    queue.addLast(new PosNode(node.left, 2 * pos));
                }
                if (node.right != null) {
                    queue.addLast(new PosNode(node.right, 2 * pos + 1));
                }
            }
        }
        return ans;
    }
}
