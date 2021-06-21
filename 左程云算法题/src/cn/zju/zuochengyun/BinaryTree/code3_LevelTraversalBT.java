package cn.zju.zuochengyun.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class code3_LevelTraversalBT {
    public static void level(TreeNode head){
        if(head == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            System.out.println(cur.val);
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }
    public static int levelMax1(TreeNode head){
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int ans = 0;
        while (!queue.isEmpty()) {
            int num = queue.size();
            ans = Math.max(ans, num);
            for (int i = 0; i < num; i++) {
                TreeNode cur = queue.poll();
                if(cur.left != null){
                    queue.add(cur.left);
                }
                if(cur.right != null){
                    queue.add(cur.right);
                }
            }
        }
        return ans;
    }
    public static int levelMax2(TreeNode head){
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode curEnd = head;
        TreeNode nextEnd = null;
        int max = 0;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
