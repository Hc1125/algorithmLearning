package cn.zju.leetcode;

import java.util.Collections;
import java.util.LinkedList;

public class code7_92 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode node = head;
        int i = 1;
        while (i < left - 1) {
            node = node.next;
            i++;
        }
        ListNode leftNode = null;
        if (left != 1) {
            leftNode = node;
        }
        LinkedList<ListNode> list = new LinkedList<>();
        if (leftNode == null) {
            list.add(node);
        }
        while (i < right) {
            node = node.next;
            i++;
            list.addLast(node);
        }
        ListNode rightNode = node.next;
        Collections.reverse(list);
        if (leftNode != null) {
            leftNode.next = list.get(0);
        }
        for (int j = 0; j < list.size() - 1; j++) {
            list.get(j).next = list.get(j + 1);
        }
        list.get(list.size() - 1).next = rightNode;
        return leftNode == null ? list.get(0) : head;
    }
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}
