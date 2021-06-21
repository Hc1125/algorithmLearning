package cn.zju.zuochengyun.BinaryTree;

import cn.zju.zuochengyun.TreeNode;

public class code9_IsBalanced {
    public static boolean isBalanced2(TreeNode head){
        return process2(head).isBalanced;
    }
    public static class Info{
        public boolean isBalanced;
        public int height;
        public Info(boolean b, int h){
            isBalanced = b;
            height = h;
        }
    }
    public static Info process2(TreeNode head){
        if(head == null){
            return new Info(true,0);
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if(!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }
}
