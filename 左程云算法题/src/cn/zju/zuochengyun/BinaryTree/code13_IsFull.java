package cn.zju.zuochengyun.BinaryTree;

public class code13_IsFull {
    public static boolean isFull(TreeNode head){
        if(head == null){
            return true;
        }
        Info all = process(head);
        return (1 << all.height) - 1 == all.nodes;
    }
    public static class Info{
        public int height;
        public int nodes;
        public Info(int h, int n){
            height = h;
            nodes = n;
        }
    }
    public static Info process(TreeNode head){
        if(head == null){
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }
}
