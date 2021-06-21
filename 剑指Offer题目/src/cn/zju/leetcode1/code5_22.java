package cn.zju.leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class code5_22 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode getKthFromEnd(ListNode head, int k) {
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.addLast(head);
            head = head.next;
        }
        ListNode ans = new ListNode(0);
        for (int i = 0; i < k; i++) {
            ans = stack.pollLast();
        }
        return ans;
    }
    public ListNode getKthFromEnd1(ListNode head, int k) {
        ListNode former = head, latter = head;
        for (int i = 0; i < k; i++) {
            former = former.next;
        }
        while (former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
}
