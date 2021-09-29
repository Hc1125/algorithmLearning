package cn.zju.zuochengyun.LFU;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LFU_SimpleVersion {
    class Node {
        int key;
        int val;
        int freq;
        public Node(int k, int v, int f) {
            key = k;
            val = v;
            freq = f;
        }
    }
    class LFUCache {
        int minFreq, capacity;
        Map<Integer, Node> keyMap;
        Map<Integer, LinkedList<Node>> freqMap;

        public LFUCache(int capacity) {
            this.minFreq = 0;
            this.capacity = capacity;
            keyMap = new HashMap<>();
            freqMap = new HashMap<>();
        }

        public int get(int key) {
            if (capacity == 0) {
                return -1;
            }
            if (!keyMap.containsKey(key)) {
                return -1;
            }
            Node node = keyMap.get(key);
            int val = node.val, freq = node.freq;
            freqMap.get(freq).remove(node);
            if (freqMap.get(freq).size() == 0) {
                freqMap.remove(freq);
                if (minFreq == freq) {
                    minFreq++;
                }
            }
            LinkedList<Node> list = freqMap.getOrDefault(freq + 1, new LinkedList<>());
            list.offerFirst(new Node(key, val, freq + 1));
            freqMap.put(freq + 1, list);
            keyMap.put(key, freqMap.get(freq + 1).peekFirst());
            return val;
        }

        public void put(int key, int val) {
            if (capacity == 0) {
                return;
            }
            if (!keyMap.containsKey(key)) {
                if (capacity == keyMap.size()) {
                    Node node = freqMap.get(minFreq).pollLast();
                    keyMap.remove(node.key);
                    if (freqMap.get(minFreq).size() == 0) {
                        freqMap.remove(minFreq);
                    }
                }
                LinkedList<Node> list = freqMap.getOrDefault(1, new LinkedList<>());
                list.offerFirst(new Node(key, val, 1));
                freqMap.put(1, list);
                keyMap.put(key, freqMap.get(1).peekFirst());
                minFreq = 1;
            } else {
                Node node = keyMap.get(key);
                int freq = node.freq;
                freqMap.get(freq).remove(node);
                if (freqMap.get(freq).size() == 0) {
                    freqMap.remove(freq);
                    if (minFreq == freq) {
                        minFreq++;
                    }
                }
                LinkedList<Node> list = freqMap.getOrDefault(freq + 1, new LinkedList<>());
                list.offerFirst(new Node(key, val, freq + 1));
                freqMap.put(freq + 1, list);
                keyMap.put(key, freqMap.get(freq + 1).peekFirst());
            }
        }
    }
}
