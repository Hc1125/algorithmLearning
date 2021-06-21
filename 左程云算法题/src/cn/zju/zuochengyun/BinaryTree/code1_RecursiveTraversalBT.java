package cn.zju.zuochengyun.BinaryTree;

public class code1_RecursiveTraversalBT {
    // 先序打印所有节点
    public static void pre(TreeNode head){
        if(head == null){
            return;
        }
        System.out.println(head.val);
        pre(head.left);
        pre(head.right);
    }
    // 中序遍历
    public static void in(TreeNode head){
        if(head == null){
            return;
        }
        in(head.left);
        System.out.println(head.val);
        in(head.right);
    }
    // 后序遍历
    public static void pos(TreeNode head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.val);
    }
}
