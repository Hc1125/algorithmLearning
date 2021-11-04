package cn.zju.group19;

import java.util.PriorityQueue;

// 本题测试链接 : https://leetcode.com/problems/trapping-rain-water-ii/
public class code2_407_TrappingRainWaterII {

	public static class Node {
		public int value;
		public int row;
		public int col;

		public Node(int v, int r, int c) {
			value = v;
			row = r;
			col = c;
		}

	}

	public int trapRainWater1(int[][] heightMap) {
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visit = new boolean[m][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
					pq.offer(new int[]{i * n + j, heightMap[i][j]});
					visit[i][j] = true;
				}
			}
		}
		int ans = 0;
		int[] dir = {-1, 0, 1, 0, -1};
		while (!pq.isEmpty()) {
			int[] node = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nx = node[0] / n + dir[i];
				int ny = node[0] % n + dir[i + 1];
				if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
					if (node[1] > heightMap[nx][ny]) {
						ans += node[1] - heightMap[nx][ny];
					}
					pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], node[1])});
					visit[nx][ny] = true;
				}
			}
		}
		return ans;
	}

	public static int trapRainWater2(int[][] heightMap) {
		if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
			return 0;
		}
		int N = heightMap.length;
		int M = heightMap[0].length;
		boolean[][] isEnter = new boolean[N][M];
		PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);
		for (int col = 0; col < M - 1; col++) {
			isEnter[0][col] = true;
			heap.add(new Node(heightMap[0][col], 0, col));
		}
		for (int row = 0; row < N - 1; row++) {
			isEnter[row][M - 1] = true;
			heap.add(new Node(heightMap[row][M - 1], row, M - 1));
		}
		for (int col = M - 1; col > 0; col--) {
			isEnter[N - 1][col] = true;
			heap.add(new Node(heightMap[N - 1][col], N - 1, col));
		}
		for (int row = N - 1; row > 0; row--) {
			isEnter[row][0] = true;
			heap.add(new Node(heightMap[row][0], row, 0));
		}
		int water = 0;
		int max = 0;
		while (!heap.isEmpty()) {
			Node cur = heap.poll();
			max = Math.max(max, cur.value);
			int r = cur.row;
			int c = cur.col;
			if (r > 0 && !isEnter[r - 1][c]) {
				water += Math.max(0, max - heightMap[r - 1][c]);
				isEnter[r - 1][c] = true;
				heap.add(new Node(heightMap[r - 1][c], r - 1, c));
			}
			if (r < N - 1 && !isEnter[r + 1][c]) {
				water += Math.max(0, max - heightMap[r + 1][c]);
				isEnter[r + 1][c] = true;
				heap.add(new Node(heightMap[r + 1][c], r + 1, c));
			}
			if (c > 0 && !isEnter[r][c - 1]) {
				water += Math.max(0, max - heightMap[r][c - 1]);
				isEnter[r][c - 1] = true;
				heap.add(new Node(heightMap[r][c - 1], r, c - 1));
			}
			if (c < M - 1 && !isEnter[r][c + 1]) {
				water += Math.max(0, max - heightMap[r][c + 1]);
				isEnter[r][c + 1] = true;
				heap.add(new Node(heightMap[r][c + 1], r, c + 1));
			}
		}
		return water;
	}

}
