package cn.zju.leetcode;

public class code13_959 {
    public int regionBySlashes(String[] grid){
        int n = grid.length;
        int size = 4 * n * n;
        UnionFind uf = new UnionFind(size);
        for (int i = 0; i < n; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < n; j++) {
                int index = 4 * (i * n + j);
                char c = row[j];
                if(c == '/'){
                    uf.union(index, index + 3);
                    uf.union(index + 1, index + 2);
                } else if(c == '\\'){
                    uf.union(index, index + 1);
                    uf.union(index + 2, index + 3);
                } else {
                    uf.union(index, index + 1);
                    uf.union(index + 1, index + 2);
                    uf.union(index + 2, index + 3);
                }
                // 单元格间合并
                // 向右合并：1（当前）、3（右一列）
                if(j + 1 < n){
                    uf.union(index + 1, 4 * (i * n + j + 1) + 3);
                }
                // 向下合并：2（当前）、0（下一行）
                if(i + 1 < n){
                    uf.union(index + 2, 4 * ((i + 1) * n + j));
                }
            }
        }
        return uf.getCount();
    }
    public class UnionFind{
        public int[] parent;
        private int count;
        public int getCount(){
            return count;
        }
        public UnionFind(int n){
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int x){
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return;
            }
            parent[rootX] = rootY;
            count--;
        }

    }
}
