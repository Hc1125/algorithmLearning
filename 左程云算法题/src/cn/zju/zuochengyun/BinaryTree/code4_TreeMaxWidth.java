package cn.zju.zuochengyun.BinaryTree;


import cn.zju.zuochengyun.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// 计算二叉树的最大宽度
public class code4_TreeMaxWidth {
    public static int maxWidthUseMap(TreeNode head){
        if(head == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        // key 在哪一层，value
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if(cur.left != null){
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if(curNodeLevel == curLevel){
                curLevelNodes++;
            }else{
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }
    public static int maxWidthNoMap(TreeNode head){
        if(head == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode curEnd = head;  // 当前层，最右节点是谁
        TreeNode nextEnd = null; // 下一层，最右节点是谁
        int max = 0;
        int curLevelNodes = 0;   // 当前层的节点数
        while(!queue.isEmpty()){
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
            if(cur == curEnd){
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
