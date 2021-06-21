package cn.zju.leetcode2;

import java.util.ArrayDeque;
import java.util.Deque;

public class code12_52 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
        val = x;
        next = null;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Deque<ListNode> stackA = new ArrayDeque<>();
        Deque<ListNode> stackB = new ArrayDeque<>();
        while (headA != null) {
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            stackB.push(headB);
            headB = headB.next;
        }
        if (stackA.peek() != stackB.peek()) {
            return null;
        }
        ListNode ans = null;
        while (!stackA.isEmpty() && !stackB.isEmpty() && stackA.peek() == stackB.peek()) {
            ans = stackA.pop();
            stackB.pop();
        }
        return ans;
    }
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2) {
            node1 = node1 != null ? node1.next : headB;
            node2 = node2 != null ? node2.next : headA;
        }
        return node1;
    }
}
