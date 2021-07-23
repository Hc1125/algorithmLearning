package cn.zju.leetcode;

public class code13_138 {
    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        while (node != null) {
            Node temp = new Node(node.val);
            temp.next = node.next;
            node.next = temp;
            node = temp.next;
        }
        node = head;
        while (node != null) {
            node.next.random = node.random != null ? node.random.next : null;
            node = node.next.next;
        }
        Node ans = head.next;
        node = head;
        while (node != null) {
            Node newNode = node.next;
            node.next = node.next.next;
            newNode.next = newNode.next != null ? newNode.next.next : null;
            node = node.next;
        }
        return ans;
    }
}
