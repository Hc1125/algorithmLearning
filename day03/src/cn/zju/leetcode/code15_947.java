package cn.zju.leetcode;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class code15_947 {
    public int removeStones(int[][] stones){
        UnionFind unionFind = new UnionFind();
        for (int[] stone : stones) {
            unionFind.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - unionFind.getCount();
    }
    private class UnionFind{
        private Map<Integer, Integer> parent;
        private int count;
        public UnionFind(){
            this.parent = new HashMap<>();
            this.count = 0;
        }
        public int getCount(){
            return count;
        }
        public int find(int x){
            if(!parent.containsKey(x)){
                parent.put(x, x);
                count++;
            }
            if(x != parent.get(x)){
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return ;
            }
            parent.put(rootX, rootY);
            count--;
        }
    }
    public int removeStones1(int[][] stones){
        int n = stones.length;
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<Integer>());
            for (int j = 0; j < n; j++) {
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    edge.get(i).add(j);
                }
            }
        }
        boolean[] vis = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if(!vis[i]){
                num++;
                dfs(i,edge,vis);
            }
        }
        return n - num;
    }
    public void dfs(int x, List<List<Integer>> edge, boolean[] vis){
        vis[x] = true;
        for (int y : edge.get(x)) {
            if(!vis[y]){
                dfs(y, edge, vis);
            }
        }
    }
    public int removeStones2(int[][] stones){
        int n = stones.length;
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<Integer>());
        }
        Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            if(!rec.containsKey(stones[i][0])){
                rec.put(stones[i][0], new ArrayList<Integer>());
            }
            rec.get(stones[i][0]).add(i);
            if(!rec.containsKey(stones[i][1] + 10001)){
                rec.put(stones[i][1] + 10001, new ArrayList<Integer>());
            }
            rec.get(stones[i][1] + 10001).add(i);
        }
        for(Map.Entry<Integer, List<Integer>> entry : rec.entrySet()){
            List<Integer> list = entry.getValue();
            int k = list.size();
            for (int i = 1; i < k; i++) {
                edge.get(list.get(i-1)).add(list.get(i));
                edge.get(list.get(i)).add(list.get(i-1));
            }
        }
        boolean[] vis = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if(!vis[i]){
                num++;
                dfs(i,edge,vis);
            }
        }
        return n - num;
    }
    public int removeStones4(int[][] stones){
        int n = stones.length;
        DisjointSetUnion dsu = new DisjointSetUnion();
        for (int i = 0; i < n; i++) {
            dsu.unionSet(stones[i][0], stones[i][1] + 10001);
        }
        return n - dsu.numberOfConnectedComponent();
    }
    class DisjointSetUnion{
        int[] f;
        int[] rank;
        public DisjointSetUnion(){
            f = new int[20001];
            rank = new int[20001];
            Arrays.fill(f,-1);
            Arrays.fill(rank,-1);
        }
        public int find(int x){
            if(f[x] < 0){
                f[x] = x;
                rank[x] = 1;
            }
            return f[x] == x ? x : (f[x] = find(f[x]));
        }
        public void unionSet(int x, int y){
            int fx = find(x), fy = find(y);
            if(fx == fy){
                return;
            }
            if(rank[fx] < rank[fy]){
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            rank[fx] += rank[fy];
            f[fy] = fx;
        }
        public int numberOfConnectedComponent(){
            int num = 0;
            for (int i = 0; i < 20000; i++) {
                if(f[i] == i){
                    num++;
                }
            }
            return num;
        }
    }
}
