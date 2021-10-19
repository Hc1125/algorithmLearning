package cn.zju.leetcode5;

import java.util.HashMap;
import java.util.Map;

public class code11_31 {
    class LRUCache {
        public class Node {
            Node prev;
            Node next;
            int key;
            int val;
            public Node() {}
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        Map<Integer, Node> map;
        int size;
        int capacity;
        Node head, tail;
        public LRUCache(int capacity) {
            map = new HashMap<>();
            size = 0;
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            moveToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                Node node = new Node(key, value);
                map.put(key, node);
                addToHead(node);
                size++;
                if (size > capacity) {
                    Node last = removeTail();
                    map.remove(last.key);
                    size--;
                }
            } else {
                Node node = map.get(key);
                node.val = value;
                moveToHead(node);
            }
        }

        public void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public Node removeTail() {
            Node node = tail.prev;
            removeNode(node);
            return node;
        }

        public void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }
    }
}
