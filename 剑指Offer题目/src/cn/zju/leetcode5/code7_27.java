package cn.zju.leetcode5;

public class code7_27 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
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
            ListNode next =slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        ListNode cur1 = head, cur2 = pre;
        while (cur1 != null) {
            if (cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }
}
