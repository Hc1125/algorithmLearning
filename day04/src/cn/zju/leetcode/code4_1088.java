package cn.zju.leetcode;



import java.util.Deque;
import java.util.LinkedList;

public class code4_1088 {
    int idx = 0;
    int[] preorder;
    int n;
    public TreeNode helper(int lower, int upper){
        if(idx == n){
            return null;
        }
        int val = preorder[idx];
        if(val < lower || val > upper) return null;
        idx++;
        TreeNode root = new TreeNode(val);
        root.left = helper(lower,val);
        root.right = helper(val,upper);
        return root;
    }
    public TreeNode bstFromPreorder1(int[] preorder){
        this.preorder = preorder;
        n = preorder.length;
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public TreeNode bstFromPreorder2(int[] preorder){
        int n = preorder.length;
        if(n == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        deque.push(root);
        for (int i = 1; i < n; i++) {
            TreeNode node = deque.peek();
            TreeNode child = new TreeNode(preorder[i]);
            while(!deque.isEmpty() && deque.peek().val < child.val)
                node = deque.poll();
            if(node.val < child.val) node.right = child;
            else node.left = child;
            deque.push(child);
        }
        return root;
    }

}
