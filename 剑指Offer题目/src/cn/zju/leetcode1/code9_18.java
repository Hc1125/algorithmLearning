package cn.zju.leetcode1;

public class code9_18 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode prev = null;
        ListNode node = head;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
                return node;
            }
            prev = head;
            head = head.next;
        }
        return node;
    }
}
