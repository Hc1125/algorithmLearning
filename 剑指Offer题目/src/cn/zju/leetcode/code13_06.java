package cn.zju.leetcode;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class code13_06 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
    public int[] reversePrint(ListNode head) {
        List<Integer> ans = new LinkedList<>();
        reverseprint(head, ans);
        int[] res = ans.stream().mapToInt(i -> i).toArray();
        return res;
    }
    public void reverseprint(ListNode head, List<Integer> ans) {
        if (head == null) {
            return;
        }
        if (head.next == null) {
           ans.add(head.val);
        } else {
            reverseprint(head.next, ans);
            ans.add(head.val);
        }
    }
    public int[] reversePrint1(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.addLast(head.val);
            head = head.next;
        }
        int n = stack.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = stack.pollLast();
        }
        return ans;
    }
}
