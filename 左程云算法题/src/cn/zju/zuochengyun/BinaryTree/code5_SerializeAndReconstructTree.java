package cn.zju.zuochengyun.BinaryTree;


import cn.zju.zuochengyun.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class code5_SerializeAndReconstructTree {
    /**
     * 先序遍历序列化以及反序列化
     */
    public static Queue<String> preSerial(TreeNode head){
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }
    public static void pres(TreeNode head, Queue<String> ans){
        if(head == null){
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }
    public static TreeNode buildByPreQueue(Queue<String> prelist){
        if(prelist == null || prelist.size() == 0){
            return null;
        }
        return preb(prelist);
    }
    public static TreeNode preb(Queue<String> prelist){
        String value = prelist.poll();
        if(value == null){
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }
    /**
     * 按层遍历序列化以及反序列化
     */
    public static Queue<String> levelSerial(TreeNode head){
        Queue<String> ans = new LinkedList<>();
        if(head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.val));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(head);
            while(!queue.isEmpty()){
                head = queue.poll();
                if(head.left != null){
                    ans.add(String.valueOf(head.left.val));
                    queue.add(head.left);
                } else {
                    ans.add(null);
                }
                if(head.right != null){
                    ans.add(String.valueOf(head.right.val));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }
    public static TreeNode buildByLevelQueue(Queue<String> levelList){
        if(levelList == null || levelList.size() == 0){
            return null;
        }
        TreeNode head = generateNode(levelList.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        if(head != null){
            queue.add(head);
        }
        TreeNode node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        return head;
    }
    public static TreeNode generateNode(String val){
        if(val == null){
            return null;
        }
        return new TreeNode(Integer.parseInt(val));
    }
}
