package cn.zju.leetcode;

import javax.swing.border.AbstractBorder;
import java.util.*;

public class code7_765 {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int N = n / 2;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < n; i += 2) {
            uf.union(row[i] / 2, row[i + 1] / 2);
        }
        return N - uf.setCount;
    }
    class UnionFind {
        public int n;
        public int[] parent;
        public int[] size;
        public int setCount;
        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            this.size = new int[n];
            this.setCount = n;
            Arrays.fill(size,1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int x){
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }
        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if(x == y) {
                return;
            }
            if(size[y] > size[x]) {
                int temp = x;
                x = y;
                y = temp;
            }
            size[x] += size[y];
            parent[y] = x;
            setCount--;
        }
    }
    public int minSwapsCouples1(int[] row) {
        int n = row.length;
        int N = n / 2;
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i += 2) {
            int l = row[i] / 2;
            int r = row[i + 1] / 2;
            if (l != r) {
                graph[l].add(r);
                graph[r].add(l);
            }
        }
        boolean[] visited = new boolean[N];
        int ret = 0;
        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                Queue<Integer> queue = new LinkedList<Integer>();
                visited[i] = true;
                queue.offer(i);
                int cnt = 0;
                while (!queue.isEmpty()) {
                    int x = queue.poll();
                    cnt++;
                    for (int y : graph[x]) {
                        if(!visited[y]) {
                            visited[y] = true;
                            queue.offer(y);
                        }
                    }
                }
                ret += cnt - 1;
            }
        }
        return ret;
    }
}
