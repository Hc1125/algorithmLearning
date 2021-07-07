package cn.zju.leetcode;



public class code8_2 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode node = head;
        int temp = 0;
        while (l1 != null && l2 != null) {
            node.next = new ListNode((l1.val + l2.val + temp) % 10);
            temp = (l1.val + l2.val + temp) / 10;
            node = node.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            node.next = new ListNode((l1.val + temp) % 10);
            temp = (l1.val + temp) / 10;
            node = node.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            node.next = new ListNode((l2.val + temp) % 10);
            temp = (l2.val + temp) / 10;
            node = node.next;
            l2 = l2.next;
        }
        if (temp != 0) {
            node.next = new ListNode(temp);
        }
        return head.next;
    }
}
