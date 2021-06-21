package cn.zju.leetcode;

import javax.print.DocFlavor;
import java.util.*;

public class code7_103 {
    protected void DFS(TreeNode node,int level,List<List<Integer>> results){
        if(level == results.size()){
            LinkedList<Integer> newLevel = new LinkedList<Integer>();
            newLevel.add(node.val);
            results.add(newLevel);
        }else{
            if(level % 2 == 0)
                results.get(level).add(node.val);
            else
                results.get(level).add(0,node.val);
        }
        if(node.left != null)DFS(node.left,level+1,results);
        if(node.right != null)DFS(node.right,level+1,results);
    }
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root){
        if(root == null){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        DFS(root,0,results);
        return results;
    }
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root){
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if(root == null){
            return ans;
        }
        LinkedList<TreeNode> nodequeue = new LinkedList<TreeNode>();
        nodequeue.offer(root);
        boolean isOrderLeft = true;
        while(!nodequeue.isEmpty()){
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodequeue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodequeue.poll();
                if(isOrderLeft){
                    levelList.offerLast(curNode.val);
                }else{
                    levelList.offerFirst(curNode.val);
                }
                if(curNode.left != null){
                    nodequeue.offer(curNode.left);
                }
                if(curNode.right != null){
                    nodequeue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;

    }
}
