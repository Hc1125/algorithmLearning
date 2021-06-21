package cn.zju.leetcode;

import java.util.Arrays;

public class code9_839 {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(uf.isConnected(i, j)){
                    continue;
                } else {
                    if(check(strs[i], strs[j], m)){
                        uf.union(i, j);
                    }
                }
            }
        }
        return uf.setCount;
    }
    public boolean check(String a, String b, int len){
        int num = 0;
        for (int i = 0; i < len; i++) {
            if(a.charAt(i) != b.charAt(i)){
                num++;
            }
            if(num > 2) {
                return false;
            }
        }
        return true;
    }
    public class UnionFind {
        public int n;
        public int[] parent;
        public int[] size;
        public int setCount;
        public UnionFind(int n) {
            this.n = n;
            size = new int[n];
            Arrays.fill(size,1);
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            setCount = n;
        }
        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }
        public boolean union(int x, int y){
            x = find(x);
            y = find(y);
            if(x == y){
                return false;
            }
            if(size[y] > size[x]){
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            setCount--;
            return true;
        }
        public boolean isConnected(int x, int y){
            return find(x) == find(y);
        }
    }
}
