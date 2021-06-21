package cn.zju.zuochengyun.BinaryTree;

public class code10_MaxDistance {

    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int dis, int h){
            maxDistance = dis;
            height = h;
        }
    }
    public int maxDistance(TreeNode head) {
        return process(head).maxDistance;
    }
    public static Info process(TreeNode head){
        if(head == null){
            return new Info(0,0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(maxDistance, height);
    }
}
