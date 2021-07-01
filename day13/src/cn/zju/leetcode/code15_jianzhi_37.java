package cn.zju.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class code15_jianzhi_37 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public class Codec {
        public String serialize(TreeNode root) {
            return rserialize(root, new StringBuilder()).toString();
        }

        public TreeNode deserialize(String data) {
            String[] dataArray = data.split(",");
            List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
            return rdeserialize(dataList);
        }

        public StringBuilder rserialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("Null,");
            } else {
                sb.append(String.valueOf(root.val)).append(",");
                sb = rserialize(root.left, sb);
                sb = rserialize(root.right, sb);
            }
            return sb;
        }

        public TreeNode rdeserialize(List<String> dataList) {
            if (dataList.get(0).equals("Null")) {
                dataList.remove(0);
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);
            return root;
        }
    }
}
