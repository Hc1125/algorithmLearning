package cn.zju.zuochengyun.BinaryTree;

public class code14_MaxSubBSTHead {
    public static class Info {
        public TreeNode maxSubBSTHead;
        public int maxSubBSTSize;
        public int min;
        public int max;
        public Info(TreeNode h, int size, int mi, int ma) {
            maxSubBSTHead = h;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }
    public static Info process(TreeNode head){
        if(head ==null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.val;
        int max = head.val;
        TreeNode maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        if (leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if(rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if(rightInfo.maxSubBSTSize > maxSubBSTSize){
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }
        if((leftInfo == null ? true : (leftInfo.maxSubBSTHead == head.left && leftInfo.max < head.val))
        && (rightInfo == null ? true : (rightInfo.maxSubBSTHead == head.right && rightInfo.min > head.val))){
            maxSubBSTHead = head;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
                    (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }
        return new Info(maxSubBSTHead, maxSubBSTSize, min, max);
    }
}
