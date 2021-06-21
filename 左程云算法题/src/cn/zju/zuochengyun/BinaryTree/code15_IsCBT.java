package cn.zju.zuochengyun.BinaryTree;

import java.util.LinkedList;

public class code15_IsCBT {
    public static boolean isBCT1(TreeNode head){
        if(head == null){
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点。 以及发现了有右孩子无左孩子的节点
            if((leaf && !(l == null && r ==null)) || (l == null && r != null)){
                return false;
            }
            if(l != null){
                queue.add(l);
            }
            if(r != null){
                queue.add(r);
            }
            if(l == null || r == null){
                leaf = true;
            }
        }
        return true;
    }

    public static boolean isBCT2(TreeNode head){
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public static class Info {
        public boolean isCBT;
        public boolean isFull;
        public int height;

        public Info(boolean c, boolean f, int h) {
            isCBT = c;
            isFull = f;
            height = h;
        }
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.height == rightInfo.height && leftInfo.isFull && rightInfo.isFull;
        boolean isCBT = isFull;
        if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        }
        return new Info(isCBT, isFull, height);
    }
}
