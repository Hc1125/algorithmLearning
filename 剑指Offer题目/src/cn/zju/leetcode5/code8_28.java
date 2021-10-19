package cn.zju.leetcode5;

import java.util.ArrayDeque;
import java.util.Deque;

public class code8_28 {
    public class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
    public Node flatten1(Node head) {
        dfs(head);
        return head;
    }

    public Node dfs(Node node) {
        Node cur = node;
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                 Node newTail = dfs(cur.child);
                 cur.next = cur.child;
                 cur.child.prev = cur;
                 if (next != null) {
                     newTail.next = next;
                     next.prev = newTail;
                 }
                 cur.child = null;
                 last = newTail;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }

    public Node flatten2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node pre = null;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            Node node = stack.poll();
            if (pre != null) {
                pre.next = node;
                node.prev = pre;
            }
            if (node.next != null) {
                stack.push(node.next);
            }
            if (node.child != null) {
                stack.push(node.child);
                node.child = null;
            }
            pre = node;
        }
        return head;
    }
}
