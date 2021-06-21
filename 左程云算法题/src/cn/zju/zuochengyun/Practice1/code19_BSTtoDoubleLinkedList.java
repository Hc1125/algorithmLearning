package cn.zju.zuochengyun.Practice1;

public class code19_BSTtoDoubleLinkedList {
    public class Node {
        int val;
        Node left;
        Node right;
        public Node(int val){
            this.val = val;
        }
    }
    public static class Info {
        public Node start;
        public Node end;
        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }
    public static Info process(Node X) {
        if (X == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        if (leftInfo.end != null) {
            leftInfo.end.right = X;
        }
        X.left = leftInfo.end;
        X.right = rightInfo.start;
        if (rightInfo.start != null) {
            rightInfo.start.left = X;
        }
        return new Info(
                leftInfo.start != null ? leftInfo.start : X,
                rightInfo.end != null ? rightInfo.end : X);
    }

}
