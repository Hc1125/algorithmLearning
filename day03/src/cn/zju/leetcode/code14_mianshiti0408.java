package cn.zju.leetcode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class code14_mianshiti0408 {
    private TreeNode ans;
    public void Solution(){
        this.ans = null;
    }
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return false;
        boolean lson = dfs(root.left, p , q);
        boolean rson = dfs(root.right, p , q);
        if((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))){
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        this.dfs(root, p, q);
        return this.ans;
    }
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();
    public void dfs1(TreeNode root){
        if(root.left != null){
            parent.put(root.left.val, root);
            dfs1(root.left);
        }
        if(root.right != null){
            parent.put(root.right.val, root);
            dfs1(root.right);
        }
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q){
        dfs1(root);
        while(p != null){
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while(q != null){
            if(visited.contains(q.val)){
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

}
