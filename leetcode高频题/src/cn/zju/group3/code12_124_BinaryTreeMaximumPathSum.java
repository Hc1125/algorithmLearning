package cn.zju.group3;

public class code12_124_BinaryTreeMaximumPathSum {
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

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPathSum;
    }

    public class Info {
       public int maxPathSum;
       public int maxPathSumFromHead;

        public Info(int maxPathSum, int maxPathSumFromHead) {
            this.maxPathSum = maxPathSum;
            this.maxPathSumFromHead = maxPathSumFromHead;
        }
    }

    public Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int maxPathSumFromHead = node.val;
        if (leftInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, node.val + leftInfo.maxPathSumFromHead);
        }
        if (rightInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, node.val + rightInfo.maxPathSumFromHead);
        }

        int maxPathSum = maxPathSumFromHead;
        if (leftInfo != null) {
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
        }
        if (rightInfo != null) {
            maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
        }
        if (leftInfo != null && rightInfo != null && leftInfo.maxPathSumFromHead > 0 && rightInfo.maxPathSumFromHead > 0) {
            maxPathSum = Math.max(maxPathSum, node.val + leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead);
        }
        return new Info(maxPathSum, maxPathSumFromHead);
    }

    // 进阶版本，打印出最大路径和的路径

    public class Data {
        public int maxAllSum;
        public TreeNode from;
        public TreeNode to;
        public int maxHeadSum;
        public TreeNode end;

        public Data(int maxAllSum, TreeNode from, TreeNode to, int maxHeadSum, TreeNode end) {
            this.maxAllSum = maxAllSum;
            this.from = from;
            this.to = to;
            this.maxHeadSum = maxHeadSum;
            this.end = end;
        }
    }
}
