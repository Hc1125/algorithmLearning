package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class code19_27 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }
    public TreeNode mirrorTree1(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (node.left != null) stack.addLast(node.left);
            if (node.right != null) stack.addLast(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
}
