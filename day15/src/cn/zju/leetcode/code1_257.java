package cn.zju.leetcode;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code1_257 {

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

    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> paths = new ArrayList<>();
        dfs(root, paths, "");
        return paths;
    }
    public void dfs(TreeNode root, List<String> paths, String path) {
        if (root != null) {
            StringBuilder sb = new StringBuilder(path);
            sb.append(root.val);
            if (root.left == null && root.right == null) {
                paths.add(sb.toString());
                return;
            } else {
                sb.append("->");
                dfs(root.left, paths, sb.toString());
                dfs(root.right, paths, sb.toString());
            }
        }
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.add(root);
        pathQueue.add(String.valueOf(root.val));
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.add(node.left);
                    pathQueue.add(new StringBuilder(path).append("->").append(node.left.val).toString());
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                    pathQueue.add(new StringBuilder(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return paths;
    }

}
