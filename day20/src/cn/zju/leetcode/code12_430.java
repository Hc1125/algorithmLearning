package cn.zju.leetcode;


import java.util.ArrayDeque;
import java.util.Deque;

public class code12_430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node pre = null;
        Deque<Node> stack = new ArrayDeque<>();
        stack.addLast(cur);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            if (pre != null) {
                pre.next = node;
                node.prev = pre;
            }
            if (node.next != null) {
                stack.addLast(node.next);
            }
            if (node.child != null) {
                stack.addLast(node.child);
                node.child = null;
            }
            pre = node;
        }
        return head;
    }
}
