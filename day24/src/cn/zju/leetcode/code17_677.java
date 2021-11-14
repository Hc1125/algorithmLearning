package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code17_677 {
    class MapSum {
        public class Node {
            int pass;
            Node[] next;

            public Node() {
                pass = 0;
                next = new Node[26];
            }
        }
        Node root;
        Map<String, Integer> map;

        public MapSum() {
            root = new Node();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            Node cur = root;
            int alter = val - map.getOrDefault(key, 0);
            for (int i = 0; i < key.length(); i++) {
                int c = key.charAt(i) - 'a';
                if (cur.next[c] == null) {
                    cur.next[c] = new Node();
                }
                cur = cur.next[c];
                cur.pass += alter;
            }
            map.put(key, val);
        }

        public int sum(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                int c = prefix.charAt(i) - 'a';
                if (cur.next[c] == null) {
                    cur.next[c] = new Node();
                }
                cur = cur.next[c];
            }
            return cur.pass;
        }
    }
}
