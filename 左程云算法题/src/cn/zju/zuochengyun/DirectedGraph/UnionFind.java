package cn.zju.zuochengyun.DirectedGraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class UnionFind {
    public static class Node<V> {
        V value;
        public Node(V v) {
            value = v;
        }
    }
    public static class UnionSet<V> {
        public HashMap<V, Node> nodes;
        public HashMap<Node<V>, Node<V>> parentMap;
        public HashMap<Node<V>, Integer> sizeMap;
        public UnionSet(List<V> values) {
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }
        public Node<V> findFather(Node<V> cur) {
            Deque<Node<V>> stack = new ArrayDeque<>();
            while (cur != parentMap.get(cur)) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }
        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }
        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parentMap.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }
    }
}
