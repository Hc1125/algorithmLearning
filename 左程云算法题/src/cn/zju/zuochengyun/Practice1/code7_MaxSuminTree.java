package cn.zju.zuochengyun.Practice1;
/**
 * 给定一个二叉树的头节点head，路径规定有以下三种不同的规定：
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 * 2）路径可以从任何节点出发，但必须往下走到任何节点，返回最大路径和
 * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
 */

public class code7_MaxSuminTree {
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int v){
            val = v;
        }
    }
    public static int maxSum = Integer.MIN_VALUE;
    public static int maxPath(Node head) {
        maxSum = Integer.MIN_VALUE;
        p(head, 0);
        return maxSum;
    }
    public static void p(Node x, int pre) {
        if (x.left == null && x.right == null) {
            maxSum = Math.max(maxSum, pre + x.val);
        }
        if (x.left != null) {
            p(x.left, pre + x.val);
        }
        if (x.right != null) {
            p(x.right, pre + x.val);
        }
    }
    public static int maxDis(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }
    public static int process(Node x) {
        if (x.left == null && x.right == null) {
            return x.val;
        }
        int next = Integer.MIN_VALUE;
        if (x.left != null) {
            next = process(x.left);
        }
        if (x.right != null) {
            next = Math.max(next, process(x.right));
        }
        return x.val + next;
    }


    public static int maxSum2(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head).allTreeMaxSum;
    }
    public static class Info {
        public int allTreeMaxSum;
        public int fromHeadMaxSum;
        public Info(int all, int from) {
            allTreeMaxSum = all;
            fromHeadMaxSum = from;
        }

    }
    // 1) x无关的时候 1，左树上的整体最大路径和 2，右树上的整体最大路径和
    // 2) x有关的时候 3，x自己  4，x往左走  5，x往右走
    public static Info process2(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process2(x.left);
        Info rightInfo = process2(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = x.val;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.val + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p4 = x.val + rightInfo.fromHeadMaxSum;
        }
        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
        return new Info(allTreeMaxSum, fromHeadMaxSum);
    }






    public static int maxSum3(Node head) {
        if (head == null) {
            return 0;
        }
        return process3(head).allTreeMaxSum;
    }
    public static class Info2 {
        public int allTreeMaxSum;
        public int fromHeadMaxSum;
        public Info2(int all, int from) {
            allTreeMaxSum = all;
            fromHeadMaxSum = from;
        }
    }
    // 1) x无关的时候 1，左树上的整体最大路径和 2，右树上的整体最大路径和
    // 2) x有关的时候 3，x自己  4，x往左走  5，x往右走  6,既往左，又往右
    public static Info2 process3(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process2(x.left);
        Info rightInfo = process2(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = x.val;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.val + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.val + rightInfo.fromHeadMaxSum;
        }
        int p6 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p6 = x.val + leftInfo.fromHeadMaxSum + rightInfo.fromHeadMaxSum;
        }
        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(Math.max(p4,p5), p6));
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
        return new Info2(allTreeMaxSum, fromHeadMaxSum);
    }
}
