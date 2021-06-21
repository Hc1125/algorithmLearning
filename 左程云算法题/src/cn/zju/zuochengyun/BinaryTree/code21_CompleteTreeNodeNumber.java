package cn.zju.zuochengyun.BinaryTree;

/**
 * 求完全二叉树节点的个数
 * 要求时间复杂度低于O（N）
 */

/**
 * 1、计算出最大深度，一直往左
 * 2、如果右树的最左节点的深度等于总最大深度，则说明左树是满的
 * 3、反之右树是满的
 * 4、进入不满的子树进行递归
 */
public class code21_CompleteTreeNodeNumber {
    //O(N)时间复杂度以下求取完全二叉树的节点个数
    // 时间复杂度O(logN)^2

    public static int nodeNum(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    /**
     * node在第level层，h是总的深度（h永远不变，全局变量）
     * 以node为头的完全二叉树，节点个数是多少
     */
    public static int bs(TreeNode node, int level, int h) {
        if (level == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, level + 1) == h) {
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        } else {
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }

    /**
     * 如果node在第level层
     * 求以node为头的子树，最大深度是多少
     * node为头的子树，一定是完全二叉树
     */
    public static int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
}
