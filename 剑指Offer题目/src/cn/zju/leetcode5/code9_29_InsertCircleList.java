package cn.zju.leetcode5;

public class code9_29_InsertCircleList {
    public class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node cur = head;
        while (cur.next != head) {
            if ((cur.val <= insertVal) ^ (cur.next.val >= insertVal) ^ (cur.next.val >= cur.val)) break;
            cur = cur.next;
        }
        cur.next = new Node(insertVal, cur.next);
        return head;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
