package cn.zju.zuochengyun.BinaryTree;

public class code11_MaxSubBSTSize {
    // 任何子树
    public static class Info{
        public boolean isAllBST;
        public int maxSubBSTSize;
        public int min;
        public int max;
        public Info(boolean is, int size, int mi, int ma){
            isAllBST = is;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }
    public static int maxSubBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBSTSize;
    }
    public static Info process(TreeNode head){
        if(head == null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.val;
        int max = head.val;
        if(leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if(rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        int maxSubBSTSize = 0;
        if(leftInfo != null){
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if(rightInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }
        boolean isAllBST = false;
        if(
                (leftInfo == null || leftInfo.isAllBST)
                &&
                (rightInfo == null || rightInfo.isAllBST)
                &&
                (leftInfo == null || leftInfo.max < head.val)
                &&
                (rightInfo == null || rightInfo.min > head.val)
        ){
            maxSubBSTSize =
                    (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    +
                    (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                    + 1;
            isAllBST = true;
        }
        return new Info(isAllBST, maxSubBSTSize, min, max);

    }

}
