package cn.zju.zuochengyun.DirectedGraph;

import java.util.*;

public class code5_Kruskal {
    public static class UnionFind {
        // key 某一个节点，value key节点往上的节点
        private HashMap<Node, Node> fatherMap;
        // key 某一个集合的代表节点， value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;
        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }
        public void makeSet(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }
        private Node findFather(Node n) {
            Deque<Node> path = new ArrayDeque<>();
            while (n != fatherMap.get(n)) {
                path.addLast(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pollLast(), n);
            }
            return n;
        }
        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }
        public void union(Node a, Node b){
            if(a == null || b == null) {
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if(aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if(aSetSize < bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2){
            return o1.weight - o2.weight;
        }
    }
    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if(!unionFind.isSameSet(edge.from, edge.to)){
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
