package cn.zju.leetcode3;

import java.util.LinkedList;
import java.util.Queue;

public class code14_37 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuffer sb = new StringBuffer("[");
            if (root == null) {
                return "[]";
            } else {
                Queue<TreeNode> queue = new LinkedList<>();
                queue.add(root);
                while (!queue.isEmpty()) {
                    TreeNode node = queue.poll();
                    if (node != null) {
                        sb.append(node.val + ",");
                        queue.add(node.left);
                        queue.add(node.right);
                    } else {
                        sb.append("null,");
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("[]")) return null;
            String[] vals = data.substring(1, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            int i = 1;
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!vals[i].equals("null")) {
                    node.left = new TreeNode(Integer.valueOf(vals[i]));
                    queue.add(node.left);
                }
                i++;
                if (!vals[i].equals("null")) {
                    node.right = new TreeNode(Integer.valueOf(vals[i]));
                    queue.add(node.right);
                }
                i++;
            }
            return root;
        }
    }

}
