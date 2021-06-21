package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code11_1319 {
    public int makeConnected(int n, int[][] connections){
        int m = connections.length;
        if(m < n - 1){
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] connection : connections) {
            int index1 = connection[0];
            int index2 = connection[1];
            uf.union(index1, index2);
        }
        return uf.setCount - 1;
    }
    class UnionFind {
        int[] parent;
        int[] size;
        int n ;
        int setCount;
        public UnionFind(int n){
            this.n = n;
            this.setCount = n;
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size,1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int x){
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }
        public boolean union(int x, int y){
            x = find(x);
            y = find(y);
            if(x == y){
                return false;
            }
            if(size[x] < size[y]){
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
            x = find(x);
            y = find(y);
            return x == y;
        }
    }
    List<Integer>[] edges;
    boolean[] used;
    public int makeConnected1(int n, int[][] connections){
        if(connections.length < n - 1){
            return -1;
        }
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int[] connection : connections) {
            edges[connection[0]].add(connection[1]);
            edges[connection[1]].add(connection[0]);
        }
        used = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if(!used[i]){
                dfs(i);
                ans++;
            }
        }
        return ans - 1;
    }
    public void dfs(int u){
        used[u] = true;
        for (int v : edges[u]) {
            if(!used[v]){
                dfs(v);
            }
        }
    }
}
