package cn.zju.zuochengyun.BinaryTree;

public class code7_SuccessorNode {
    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int data){
            this.value = data;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode ans = null;
        while (cur != null) {
            if (cur.val > p.val) {
                ans = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return ans;
    }

    public static Node getSuccessorNode(Node node){
        if(node == null){
            return node;
        }
        if(node.right != null){
            return getLeftMost(node.right);
        } else {  // 无右子树
            Node parent = node.parent;
            while(parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    public static Node getLeftMost(Node node){
        if (node == null){
            return node;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
}
