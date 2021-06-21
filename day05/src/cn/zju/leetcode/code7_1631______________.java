package cn.zju.leetcode;

import java.util.*;

public class code7_1631______________ {
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                if(i > 0){
                    edges.add(new int[]{id - n, id, Math.abs(heights[i - 1][j] - heights[i][j])});
                }
                if(j > 0){
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j - 1] - heights[i][j])});
                }
            }
        }
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        UnionFind uf = new UnionFind(m * n);
        int ans = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            uf.union(u, v);
            if(uf.isConnected(0, m * n - 1)){
                ans = w;
                break;
            }
        }
        return ans;
    }
    public int minimumEffortPath1(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0, right = 999999, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0,0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;
            while(!queue.isEmpty()){
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if(nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[nx][ny] - heights[x][y]) <= mid){
                        seen[nx * n + ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            if(seen[m * n - 1]){
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    public class UnionFind {
        public int[] parent;
        public int[] size;
        public int n;
        public int setCount;

        public  UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
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
            x = find(x);
            y = find(y);
            return x == y;
        }
    }
    public int minimumEffortPath2(int[][] heights) {
        return 0;
    }
}

