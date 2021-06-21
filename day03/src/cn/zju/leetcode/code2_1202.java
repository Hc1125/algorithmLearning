package cn.zju.leetcode;

import java.util.*;

public class code2_1202 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs){
        char[] chars = s.toCharArray();
        int n = chars.length;
        if(pairs.size() == 0){
            return s;
        }
        UnionFind unionFind = new UnionFind(n);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1,index2);
        }
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int root = unionFind.find(i);
            /*if(hashMap.containsKey(root)){
                hashMap.get(root).offer(chars[i]);
            }else{
                PriorityQueue<Character> minHeap = new PriorityQueue<>();
                minHeap.offer(chars[i]);
                hashMap.put(root,minHeap);
            }*/
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(chars[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();

    }
    public class UnionFind{
        private int[] parent;
        private int[] rank;
        public UnionFind(int n){
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return;
            }
            if(rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                rank[rootY]++;
            }else if(rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
            }else {
                parent[rootY] = rootX;
            }
        }
        public int find(int index){
            return parent[index] == index ? index : (parent[index] = find(parent[index]));
        }
    }

}
