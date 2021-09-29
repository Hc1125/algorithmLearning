package cn.zju.group12;

// 本题测试链接 : https://leetcode.com/problems/bricks-falling-when-hit/
public class code3_803_BricksFallingWhenHit {

	private int rows;
	private int cols;
	public static final int[][] directions = {{0,1}, {1,0}, {-1,0}, {0,-1}};
	public int[] hitBricks1(int[][] grid, int[][] hits){
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
		UnionFind1 unionFind = new UnionFind1(size+1);
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
	private class UnionFind1 {
		private int[] parent;
		private int[] size;
		public UnionFind1(int n){
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



	public static int[] hitBricks2(int[][] grid, int[][] hits) {
		for (int i = 0; i < hits.length; i++) {
			if (grid[hits[i][0]][hits[i][1]] == 1) {
				grid[hits[i][0]][hits[i][1]] = 2;
			}
		}
		UnionFind2 unionFind = new UnionFind2(grid);
		int[] ans = new int[hits.length];
		for (int i = hits.length - 1; i >= 0; i--) {
			if (grid[hits[i][0]][hits[i][1]] == 2) {
				ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
			}
		}
		return ans;
	}

	// 并查集
	public static class UnionFind2 {
		private int N;
		private int M;
		// 有多少块砖，连到了天花板上
		private int cellingAll;
		// 原始矩阵，因为炮弹的影响，1 -> 2
		private int[][] grid;
		// cellingSet[i] = true; i 是头节点，所在的集合是天花板集合
		private boolean[] cellingSet;
		private int[] fatherMap;
		private int[] sizeMap;
		private int[] stack;

		public UnionFind2(int[][] matrix) {
			initSpace(matrix);
			initConnect();
		}

		private void initSpace(int[][] matrix) {
			grid = matrix;
			N = grid.length;
			M = grid[0].length;
			int all = N * M;
			cellingAll = 0;
			cellingSet = new boolean[all];
			fatherMap = new int[all];
			sizeMap = new int[all];
			stack = new int[all];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (grid[row][col] == 1) {
						int index = row * M + col;
						fatherMap[index] = index;
						sizeMap[index] = 1;
						if (row == 0) {
							cellingSet[index] = true;
							cellingAll++;
						}
					}
				}
			}
		}

		private void initConnect() {
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					union(row, col, row - 1, col);
					union(row, col, row + 1, col);
					union(row, col, row, col - 1);
					union(row, col, row, col + 1);
				}
			}
		}

		private int find(int row, int col) {
			int stackSize = 0;
			int index = row * M + col;
			while (index != fatherMap[index]) {
				stack[stackSize++] = index;
				index = fatherMap[index];
			}
			while (stackSize != 0) {
				fatherMap[stack[--stackSize]] = index;
			}
			return index;
		}

		private void union(int r1, int c1, int r2, int c2) {
			if (valid(r1, c1) && valid(r2, c2)) {
				int father1 = find(r1, c1);
				int father2 = find(r2, c2);
				if (father1 != father2) {
					int size1 = sizeMap[father1];
					int size2 = sizeMap[father2];
					boolean status1 = cellingSet[father1];
					boolean status2 = cellingSet[father2];
					if (size1 <= size2) {
						fatherMap[father1] = father2;
						sizeMap[father2] = size1 + size2;
						if (status1 ^ status2) {
							cellingSet[father2] = true;
							cellingAll += status1 ? size2 : size1;
						}
					} else {
						fatherMap[father2] = father1;
						sizeMap[father1] = size1 + size2;
						if (status1 ^ status2) {
							cellingSet[father1] = true;
							cellingAll += status1 ? size2 : size1;
						}
					}
				}
			}
		}

		private boolean valid(int row, int col) {
			return row >= 0 && row < N && col >= 0 && col < M && grid[row][col] == 1;
		}

		public int cellingNum() {
			return cellingAll;
		}

		public int finger(int row, int col) {
			grid[row][col] = 1;
			int cur = row * M + col;
			if (row == 0) {
				cellingSet[cur] = true;
				cellingAll++;
			}
			fatherMap[cur] = cur;
			sizeMap[cur] = 1;
			int pre = cellingAll;
			union(row, col, row - 1, col);
			union(row, col, row + 1, col);
			union(row, col, row, col - 1);
			union(row, col, row, col + 1);
			int now = cellingAll;
			if (row == 0) {
				return now - pre;
			} else {
				return now == pre ? 0 : now - pre - 1;
			}
		}
	}

}
