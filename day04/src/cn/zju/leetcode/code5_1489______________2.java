package cn.zju.leetcode;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class code5_1489______________2 {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges1(int n, int[][] edges){
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, new Comparator<int[]>() {
            @Override
            public int compare(int[] u, int[] v) {
                return u[2] - v[2];
            }
        });
        UnionFind uf1 = new UnionFind(n);
        int value = 0;
        for (int i = 0; i < m; i++) {
            if(uf1.union(newEdges[i][0], newEdges[i][1])){
                value += newEdges[i][2];
            }
        }
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; i++) {
            ans.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            UnionFind uf2 = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < m; j++) {
                if(i != j && uf2.union(newEdges[j][0], newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            if(uf2.setCount != 1 || ((uf2.setCount == 1) && (v > value))){
                ans.get(0).add(newEdges[i][3]);
                continue;
            }
            uf2 = new UnionFind(n);
            uf2.union(newEdges[i][0], newEdges[i][1]);
            v = newEdges[i][2];
            for (int j = 0; j < m; j++) {
                if(i != j && uf2.union(newEdges[j][0], newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            if(v == value){
                ans.get(1).add(newEdges[i][3]);
            }
        }
        return ans;
    }
    class UnionFind{
        int[] parent;
        int[] size;
        int n;
        int setCount;
        public UnionFind(int n){
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
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
        public boolean connected(int x, int y){
            x = find(x);
            y = find(y);
            return x == y;
        }
    }
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges2(int n, int[][] edges){
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, new Comparator<int[]>() {
            @Override
            public int compare(int[] u, int[] v) {
                return u[2] - v[2];
            }
        });
        UnionFind uf= new UnionFind(n);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; i++) {
            ans.add(new ArrayList<Integer>());
        }
        int[] label = new int[m];
        for (int i = 0; i < m;) {
            int w = newEdges[i][2];
            int j = i;
            while(j < m && newEdges[j][2] == newEdges[i][2]){
                ++j;
            }
            Map<Integer, Integer> compToId = new HashMap<Integer, Integer>();
            int gn = 0;
            for (int k = i; k < j; k++) {
                int x = uf.find(newEdges[k][0]);
                int y = uf.find(newEdges[k][1]);
                if(x != y){
                    if(!compToId.containsKey(x)){
                        compToId.put(x, gn++);
                    }
                    if(!compToId.containsKey(y)){
                        compToId.put(y, gn++);
                    }
                } else {
                  label[newEdges[k][3]] = -1;
                }
            }
            List<Integer>[] gm = new List[gn];
            List<Integer>[] gmid = new List[gn];
            for (int k = 0; k < gn; k++) {
                gm[k] = new ArrayList<Integer>();
                gmid[k] = new ArrayList<Integer>();
            }
            for (int k = i; k < j; k++) {
                int x = uf.find(newEdges[k][0]);
                int y = uf.find(newEdges[k][1]);
                if(x != y){
                    int idx = compToId.get(x), idy = compToId.get(y);
                    gm[idx].add(idy);
                    gmid[idx].add(newEdges[k][3]);
                    gm[idy].add(idx);
                    gmid[idy].add(newEdges[k][3]);
                }
            }
            List<Integer> bridges = new TarjanSCC(gn, gm, gmid).getCuttingEdge();
            for (int id : bridges) {
                ans.get(0).add(id);
                label[id] = 1;
            }
            for (int k = i; k < j; k++) {
                uf.union(newEdges[k][0], newEdges[k][1]);
            }
            i = j;
        }
        for (int i = 0; i < m; i++) {
            if(label[i] == 0){
                ans.get(1).add(i);
            }
        }
        return ans;
    }
    class TarjanSCC{
        List<Integer>[] edges;
        List<Integer>[] edgesId;
        int[] low;
        int[] dfn;
        List<Integer> ans;
        int n;
        int ts;
        public TarjanSCC(int n, List<Integer>[] edges, List<Integer>[] edgesId){
            this.edges = edges;
            this.edgesId = edgesId;
            this.low = new int[n];
            Arrays.fill(low,-1);
            this.dfn = new int[n];
            Arrays.fill(dfn,-1);
            this.n = n;
            this.ts = -1;
            this.ans = new ArrayList<Integer>();
        }
        public List<Integer> getCuttingEdge(){
            for (int i = 0; i < n; i++) {
                if(dfn[i] == -1){
                    getCuttingEdge(i, -1);
                }
            }
            return ans;
        }
        public void getCuttingEdge(int u, int parentEdgeId){
            low[u] = dfn[u] = ++ts;
            for (int i = 0; i < edges[u].size(); i++) {
                int v = edges[u].get(i);
                int id = edgesId[u].get(i);
                if(dfn[v] == -1){
                    getCuttingEdge(v, id);
                    low[u] = Math.min(low[u], low[v]);
                    if(low[v] > dfn[u]){
                        ans.add(id);
                    }
                } else if(id != parentEdgeId){
                    low[u] = Math.min(low[u], dfn[v]);
                }
            }
        }
    }
}
