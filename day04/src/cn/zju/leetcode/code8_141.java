package cn.zju.leetcode;


import java.util.HashSet;
import java.util.Set;

public class code8_141 {
    public boolean hasCycle(ListNode head){
        Set<ListNode> set = new HashSet<ListNode>();
        while(head != null){
            if(!set.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }
    public boolean hasCycle1(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
