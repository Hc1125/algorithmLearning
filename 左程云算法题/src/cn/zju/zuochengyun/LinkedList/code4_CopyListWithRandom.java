package cn.zju.zuochengyun.LinkedList;
import java.util.HashMap;

public class code4_CopyListWithRandom {
    public static Node copyListWithRand1(Node head){
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while(cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }
    public static Node copyListWithRand2(Node head){
        if(head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        while(cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // split
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
