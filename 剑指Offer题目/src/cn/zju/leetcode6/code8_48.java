package cn.zju.leetcode6;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class code8_48 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    public class Codec {
        public String serialize(TreeNode root) {
            StringBuilder ans = new StringBuilder();
            pres(root, ans);
            return ans.toString();
        }
        public void pres(TreeNode node, StringBuilder ans) {
            if (node == null) {
                ans.append("NULL_");
            } else {
                ans.append(node.val).append("_");
                pres(node.left, ans);
                pres(node.right, ans);
            }
        }

        public TreeNode deserialize(String data) {
            if (data == null || data.equals("")) {
                return null;
            }
            String[] arr = data.split("_");
            Queue<String> queue = new LinkedList<>(Arrays.asList(arr));
            return preb(queue);
        }

        public TreeNode preb(Queue<String> queue) {
            String value = queue.poll();
            if (value == "NULL" || value.equals("NULL")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(value));
            node.left = preb(queue);
            node.right = preb(queue);
            return node;
        }
    }
}
