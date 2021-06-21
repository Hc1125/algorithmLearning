package cn.zju.zuochengyun.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class code2_UnRecursiveTraversalBT {
    public static void pre(TreeNode head){
        System.out.println("pre-order:");
        if(head != null){
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.addLast(head);
            while(!stack.isEmpty()){
                head = stack.pollLast();
                System.out.println(head.val + " ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }
    public static void in(TreeNode head){
        System.out.println("in-roder: ");
        if(head != null){
            Deque<TreeNode> stack = new ArrayDeque<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                } else{
                    head = stack.pollLast();
                    System.out.println(head.val + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }
    public static void pos1(TreeNode head){
        System.out.println("pos-order: ");
        if(head != null){
            Deque<TreeNode> s1 = new ArrayDeque<TreeNode>();
            Deque<TreeNode> s2 = new ArrayDeque<TreeNode>();
            s1.push(head);
            while(!s1.isEmpty()){
                head = s1.pollLast();
                s2.push(head);
                if(head.left != null){
                    s1.push(head.left);
                }
                if(head.right != null){
                    s1.push(head.right);
                }
            }
            while(!s2.isEmpty()){
                System.out.println(s2.pollLast().val + " ");
            }
        }
        System.out.println();
    }
    public static void pos2(TreeNode head){
        System.out.println("pos-order: ");
        if(head != null){
            Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
            stack.push(head);
            TreeNode c = null;
            while(!stack.isEmpty()){
                c = stack.peekLast();
                if(c.left != null && head != c.left && head != c.right){
                    stack.push(c.left);
                } else if(c.right != null && head != c.right){
                    stack.push(c.right);
                } else{
                    System.out.println(stack.pollLast().val + " ");
                    head = c;
                }
            }
        }
    }
}
