package cn.zju.leetcode;

import java.util.List;

public class code10_725 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];
        if (head == null) {
            return ans;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        int q = len / k;
        int m = len % k;
        cur = head;
        for (int i = 0; i < k && cur != null; i++) {
            ans[i] = cur;
            int size = q + (i < m ? 1 : 0);
            for (int j = 1; j < size; j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return ans;
    }
}
