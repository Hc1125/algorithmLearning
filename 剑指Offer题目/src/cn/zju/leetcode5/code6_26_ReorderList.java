package cn.zju.leetcode5;

public class code6_26_ReorderList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head, fast = head;
        while (true) {
            if (fast.next == null || fast.next.next == null) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next != null) {
            ListNode pre = slow;
            slow = slow.next;
            pre.next = null;
        }
        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        ListNode cur1 = head, cur2 = pre;
        while (cur1 != null && cur2 != null && cur1 != cur2) {
            ListNode next = cur2.next;
            cur2.next = cur1.next;
            cur1.next = cur2;
            cur1 = cur2.next;
            cur2 = next;
        }
    }

}
