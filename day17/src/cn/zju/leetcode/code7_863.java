package cn.zju.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code7_863 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    Map<Integer, TreeNode> parents = new HashMap<>();
    List<Integer> ans = new ArrayList<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParents(root);
        findAns(target, null, 0, k);
        return ans;
    }

    public void findParents(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left.val, node);
            findParents(node.left);
        }
        if (node.right != null) {
            parents.put(node.right.val, node);
            findParents(node.right);
        }
    }

    public void findAns(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) {
            return;
        }
        if (depth == k) {
            ans.add(node.val);
        }
        if (node.left != from) {
            findAns(node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findAns(node.right, node, depth + 1, k);
        }
        if (parents.get(node.val) != from) {
            findAns(parents.get(node.val), node, depth + 1, k);
        }
    }
}
