package cn.zju.group8;

public class code3_226_InvertBinaryTree {

	public class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode left = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(left);
		return root;
	}

}
