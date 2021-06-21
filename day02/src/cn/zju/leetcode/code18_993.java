package cn.zju.leetcode;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class code18_993 {
    Map<Integer, Integer> depth;
    Map<Integer,TreeNode> parent;
    public boolean isCousins1(TreeNode root, int x, int y){
        depth = new HashMap<>();
        parent = new HashMap<>();
        dfs(root,null);
        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
    }
    public void dfs(TreeNode node, TreeNode par){
        if(node != null){
            depth.put(node.val,par!=null ? depth.get(par.val)+1 : 0);
            parent.put(node.val,par);
            dfs(node.left,node);
            dfs(node.right,node);
        }
    }
    public boolean isCousins2(TreeNode root, int x, int y){
        if(root == null || root.val == x || root.val == y){
            return false;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode xNode = null;
        TreeNode yNode = null;
        TreeNode xFather = null;
        TreeNode yFather = null;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                TreeNode temp = q.poll();
                if(temp.left != null){
                    q.add(temp.left);
                    if(temp.left.val == x){ xNode = temp.left; xFather = temp;}
                    if(temp.left.val == y){ yNode = temp.left; yFather = temp;}
                }
                if(temp.right != null){
                    q.add(temp.right);
                    if(temp.right.val == x){ xNode = temp.right; xFather = temp;}
                    if(temp.right.val == y){ yNode = temp.right; yFather = temp;}
                }
                if(xNode == null && yNode == null){}
                else if(xNode!=null && yNode!=null){
                    return xFather!=yFather;
                }
                else if(size == 0){
                    return false;
                }
            }
        }
        return false;
    }
}
