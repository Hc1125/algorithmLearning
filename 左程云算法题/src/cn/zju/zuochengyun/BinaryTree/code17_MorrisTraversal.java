package cn.zju.zuochengyun.BinaryTree;

public class code17_MorrisTraversal {
    public static void morris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {  //有左树的情况下
                // 找到cur左树上，真实的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostright一定是cur左树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }
    // 中序遍历
    public static void morrisIn(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {  //有左树的情况下
                // 找到cur左树上，真实的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostright一定是cur左树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.println(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
    }
    // 先序遍历
    public static void morrisPre(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {  //有左树的情况下
                // 找到cur左树上，真实的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostright一定是cur左树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.println(cur.val + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.println(cur.val + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }
    // 后序遍历
    public static void morrisPos(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {  //有左树的情况下
                // 找到cur左树上，真实的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostright一定是cur左树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.println(cur.val + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            } else {
                System.out.println(cur.val + " ");
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }
    public static void printEdge(TreeNode head) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.println(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }
    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }
    // 是否是搜索二叉树
    public static boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        Integer pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {  //有左树的情况下
                // 找到cur左树上，真实的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostright一定是cur左树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.val) {
                return false;
            }
            pre = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
