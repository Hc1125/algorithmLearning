package cn.zju.leetcode;



public class code18_26 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return process(A, B);
    }
    public boolean process(TreeNode A, TreeNode B) {
        if (A == null) {
            return false;
        }
        if (A.val == B.val) {
            if (dfs(A, B)) {
                return true;
            }
        }
        if (process(A.left, B) || process(A.right, B)) {
            return true;
        }
        return false;
    }
    public boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val == B.val) {
            return dfs(A.left, B.left) && dfs(A.right, B.right);
        } else {
            return false;
        }
    }
    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure1(A.left, B) || isSubStructure1(A.right, B));
    }
    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
