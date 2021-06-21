package cn.zju.leetcode;

import java.util.Arrays;

public class code20_1579 {
    public int maxNumEdgesToRemove(int n, int[][] edges){
        int ans = 0;
        UnionFind uf1 = new UnionFind(n);
        UnionFind uf2 = new UnionFind(n);
        for (int[] edge : edges) {
            if(edge[0] == 3){
                if(!uf1.union(edge[1], edge[2])){
                    ++ans;
                }else{
                    uf2.union(edge[1], edge[2]);
                }
            }
        }
        for (int[] edge : edges) {
            if(edge[0] == 1){
                if(!uf1.union(edge[1], edge[2])){
                    ans++;
                }
            }
            if(edge[0] == 2){
                if(!uf2.union(edge[1], edge[2])){
                    ans++;
                }
            }
        }
        return (uf1.setCount == 1 && uf2.setCount == 1) ? ans : -1;
    }
    public class UnionFind {
        public int[] parent;
        public int setCount;
        public int n;
        public int[] size;
        public UnionFind(int n){
            this.parent = new int[n + 1];
            this.n = n;
            this.setCount = n;
            this.size = new int[n + 1];
            Arrays.fill(size,1);
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }
        public int find(int x){
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }
        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return false;
            }
            if(size[rootX] < size[rootY]){
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            setCount--;
            return true;
        }
        public int getSetCount(){
            return setCount;
        }
    }
}
