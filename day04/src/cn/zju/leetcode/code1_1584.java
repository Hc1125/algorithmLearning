package cn.zju.leetcode;

import java.util.*;

public class code1_1584 {
    public int minCostConnectPoints(int[][] points){
        int n = points.length;
        UnionFind uf = new UnionFind(n);
        List<Edge> edges = new LinkedList<Edge>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                edges.add(new Edge(dist(points,i,j),i,j));
            }
        }
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.len - o2.len;
            }
        });
        int ret = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if(uf.union(x,y)){
                ret += len;
                num++;
                if(num == n){
                    break;
                }
            }
        }
        return ret;
    }
    public int dist(int[][] points, int x, int y){
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }

}
class Solution{
    List<Edge> edges = new LinkedList<Edge>();
    Pos[] pos;
    public int minCostConnectPoints(int[][] points){
        int n = points.length;
        solve(points,n);
        UnionFind uf = new UnionFind(n);
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.len - o2.len;
            }
        });
        int ret = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if(uf.union(x,y)){
                ret += len;
                num++;
                if(num == n){
                    break;
                }
            }
        }
        return ret;
    }
    public void solve(int[][] points, int n){
        pos = new Pos[n];
        for (int i = 0; i < n; i++) {
            pos[i] = new Pos(i, points[i][0], points[i][1]);
        }
        build(n);
        for (int i = 0; i < n; i++) {
            int temp = pos[i].x;
            pos[i].x = pos[i].y;
            pos[i].y = temp;
        }
        build(n);
        for (int i = 0; i < n; i++) {
            pos[i].x = -pos[i].x;
        }
        build(n);
        for (int i = 0; i < n; i++) {
            int temp = pos[i].x;
            pos[i].x = pos[i].y;
            pos[i].y = temp;
        }
        build(n);
    }
    public void build(int n){
        Arrays.sort(pos, new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                return o1.x == o2.x ? o1.y - o2.y : o1.x - o2.x;
            }
        });
        int[] a = new int[n];
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            a[i] = pos[i].y - pos[i].x;
            set.add(pos[i].y - pos[i].x);
        }
        int num = set.size();
        int[] b = new int[num];
        int index = 0;
        for (int element : set) {
            b[index++] = element;
        }
        Arrays.sort(b);
        BIT1 bit = new BIT1(num + 1);
        for(int i = n - 1; i >= 0; i--){
            int poss = binarySearch(b, a[i])+1;
            int j = bit.query(poss);
            if(j != -1){
                int dis = Math.abs(pos[i].x - pos[j].x) + Math.abs(pos[i].y - pos[j].y);
                edges.add(new Edge(dis, pos[i].id, pos[j].id));
            }
            bit.update(poss, pos[i].x + pos[i].y, i);

        }
    }
    public int binarySearch(int[] array, int target){
        int low = 0, high = array.length - 1;
        while(low < high){
            int mid = (high - low) / 2 + low;
            int num = array[mid];
            if(num < target){
                low = mid + 1;
            }else {
                high = mid;
            }
        }
        return low;
    }
}
class UnionFind{
    int[] parent;
    int[] rank;
    int n;
    public UnionFind(int n){
        this.n = n;
        this.rank = new int[n];
        Arrays.fill(this.rank,1);
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }
    public int find(int index){
        return parent[index] == index ? index : (parent[index] = find(parent[index]));
    }
    public boolean union(int index1, int index2){
        int rootX = find(index1);
        int rootY = find(index2);
        if(rootX == rootY){
            return false;
        }
        if(rank[rootX] < rank[rootY]){
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        rank[rootX] += rank[rootY];
        parent[rootY] = rootX;
        return true;
    }

}
class BIT1 {
    int[] tree;
    int[] idRec;
    int n;
    public BIT1(int n){
        this.n = n;
        this.tree = new int[n];
        Arrays.fill(this.tree,Integer.MAX_VALUE);
        this.idRec = new int[n];
        Arrays.fill(this.idRec,-1);
    }
    public int lowbit(int k){
        return k & (-k);
    }
    public void update(int pos, int val, int id){
        while(pos > 0){
            if(tree[pos] >= val){
                tree[pos] = val;
                idRec[pos] = id;
            }
            pos -= lowbit(pos);
        }
    }
    public int query(int pos){
        int minval = Integer.MAX_VALUE;
        int j = -1;
        while(pos < n){
            if(minval > tree[pos]){
                minval = tree[pos];
                j = idRec[pos];
            }
            pos += lowbit(pos);
        }
        return j;
    }
}
class Edge{
    int len, x, y;
    public Edge(int len, int x, int y){
        this.len = len;
        this.x = x;
        this.y = y;
    }
}
class Pos{
    int id,x,y;
    public Pos(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

