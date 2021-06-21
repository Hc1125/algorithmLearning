package cn.zju.leetcode;

public class code17_803 {
    private int rows;
    private int cols;
    public static final int[][] directions = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    public int[] hitBricks(int[][] grid, int[][] hits){
        this.rows = grid.length;
        this.cols = grid[0].length;
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        for (int[] hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }
        //第二步：建图，把砖块和砖块的连接关系输入并查集
        int size = rows * cols;
        UnionFind unionFind = new UnionFind(size+1);
        for (int j = 0; j < cols; j++) {
            if(copy[0][j] == 1){
                unionFind.union(j, size);
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(copy[i][j] == 1){
                    if(copy[i-1][j] == 1){
                        unionFind.union(getIndex(i - 1,j),getIndex(i,j));
                    }
                    if(j > 0 && copy[i][j-1] == 1){
                        unionFind.union(getIndex(i,j - 1),getIndex(i,j));
                    }
                }
            }
        }
        //第三步：逆序补回砖块
        int hitsLen = hits.length;
        int[] res = new int[hitsLen];
        for(int i = hitsLen-1; i >= 0; i--){
            int x = hits[i][0];
            int y = hits[i][1];
            if(grid[x][y] == 0){
                continue;
            }
            int origin = unionFind.getSize(size);
            //补回砖块
            if(x==0){
                unionFind.union(y,size);
            }
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if(inArea(newX,newY) && copy[newX][newY] == 1){
                    unionFind.union(getIndex(x, y), getIndex(newX, newY));
                }
            }
            int current = unionFind.getSize(size);
            res[i] = Math.max(0,current-origin-1);
            copy[x][y] = 1;
        }
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }


    private int getIndex(int x, int y){
        return x * cols + y;
    }
    private class UnionFind{
        private int[] parent;
        private int[] size;
        public UnionFind(int n){
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
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
            size[rootY] += size[rootX];
        }
        public int getSize(int x){
            int root = find(x);
            return size[root];
        }
    }
}
