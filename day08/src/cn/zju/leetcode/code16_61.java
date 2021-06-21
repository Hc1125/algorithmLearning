package cn.zju.leetcode;


public class code16_61 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int num = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            num++;
        }
        k = k % num;
        k = num - k;
        if (k == 0) {
            return head;
        }
        cur = head;
        while (k > 1) {
            cur = cur.next;
            k--;
        }
        ListNode begin = cur.next;
        cur.next = null;
        ListNode node = begin;
        while (node.next != null) {
            node = node.next;
        }
        node.next = head;
        return begin;
    }
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }
}
