package cn.zju.leetcode5;

import java.util.ArrayDeque;
import java.util.Deque;

public class code5_25_AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode ans = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.poll();
            int b = stack2.isEmpty() ? 0 : stack2.poll();
            int num = (a + b + carry) % 10;
            carry = (a + b + carry) / 10;
            ListNode node = new ListNode(num);
            node.next = ans;
            ans = node;
        }
        return ans;
    }
}
