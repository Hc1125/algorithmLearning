package cn.zju.zuochengyun.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
// 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
public class code23_ConvertBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    int sum = 0;
    public TreeNode convertBST1(TreeNode root) {
        if (root != null) {
            convertBST1(root.right);
            sum += root.val;
            root.val = sum;
            convertBST1(root.left);
        }
        return root;
    }
    public TreeNode convertBST2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        dfs(root, deque);
        TreeNode pre = deque.pollLast();
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            node.val = node.val + pre.val;
            pre = node;
        }
        return root;
    }
    public void dfs(TreeNode root, Deque<TreeNode> deque) {
        if (root == null) {
            return;
        }
        dfs(root.left, deque);
        deque.addLast(root);
        dfs(root.right, deque);
    }

    // Morris遍历，额外空间O(1)
    public TreeNode convertBST3(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode succ = getSuccessor(node);
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                } else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }
}
