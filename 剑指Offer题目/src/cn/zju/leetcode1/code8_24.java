package cn.zju.leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class code8_24 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode reverseNode(ListNode head) {
        if (head == null) {
            return null;
        }
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.addLast(head);
            head = head.next;
        }
        head = stack.pollLast();
        ListNode node = head;
        while (!stack.isEmpty()) {
            head.next = stack.pollLast();
            head = head.next;
        }
        head.next = null;
        return node;
    }
    public ListNode reverseNode1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
