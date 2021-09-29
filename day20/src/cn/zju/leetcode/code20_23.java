package cn.zju.leetcode;

import java.util.PriorityQueue;

public class code20_23 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode head = new ListNode();
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }
        ListNode pre = head;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            pre.next = node;
            if (node.next != null) {
                pq.offer(node.next);
            }
            pre = node;
        }
        return head.next;
    }
}
