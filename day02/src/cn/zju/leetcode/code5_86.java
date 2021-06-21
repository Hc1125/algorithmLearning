package cn.zju.leetcode;

public class code5_86 {
    private static ListNode partition1(ListNode head, int x){
        ListNode listNode = head;
        ListNode listHead = listNode;
        ListNode mark = null;
        while(head != null){
            if(mark == null && head.next.val >= x){
                mark = head;
            }
            if(mark != null && head.next.val < x){
                ListNode temp1 = head.next;
                head.next = temp1.next;
                ListNode temp2 = mark.next;
                mark.next = temp1;
                temp1.next = temp2;
                mark = mark.next;
            }
            listNode.next = head;
            listNode = listNode.next;
            head = head.next;
        }
        return listHead;
    }
    private static ListNode partition2(ListNode head, int x){
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while(head != null){
            if(head.val < x){
                small.next = head;
                small = small.next;
            }else{
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
