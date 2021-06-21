package cn.zju.zuochengyun.LinkedList;
public class code1_LinkedListMid {
    //奇数的时候返回中点，偶数的时候返回上中点
    public static Node midOrUpMidNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrDownMidNode(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static Node midOrUpMidPreNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static Node midOrDownMidPreNode(Node head){
        if(head == null || head.next == null){
            return null;
        }
        if(head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
