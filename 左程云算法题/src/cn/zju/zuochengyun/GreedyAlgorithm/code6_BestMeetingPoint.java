package cn.zju.zuochengyun.GreedyAlgorithm;

/**
 * 有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。
 * 给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。
 * 1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。
 * 示例：
 * 输入:
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * 输出: 6
 * 解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
 *      (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
 */
public class code6_BestMeetingPoint {

	public static int minTotalDistance(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[] iOnes = new int[N];
		int[] jOnes = new int[M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1) {
					iOnes[i]++;
					jOnes[j]++;
				}
			}
		}
		int total = 0;
		int i = 0;
		int j = N - 1;
		int iRest = 0;
		int jRest = 0;
		while (i < j) {
			if (iOnes[i] + iRest <= iOnes[j] + jRest) {
				total += iOnes[i] + iRest;
				iRest += iOnes[i++];
			} else {
				total += iOnes[j] + jRest;
				jRest += iOnes[j--];
			}
		}
		i = 0;
		j = M - 1;
		iRest = 0;
		jRest = 0;
		while (i < j) {
			if (jOnes[i] + iRest <= jOnes[j] + jRest) {
				total += jOnes[i] + iRest;
				iRest += jOnes[i++];
			} else {
				total += jOnes[j] + jRest;
				jRest += jOnes[j--];
			}
		}
		return total;
	}

}
