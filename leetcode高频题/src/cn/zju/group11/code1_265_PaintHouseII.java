package cn.zju.group11;

/**
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 *
 * 所有花费均为正整数。
 *
 * 示例：
 *
 * 输入: [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
 * 或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
 */
public class code1_265_PaintHouseII {

	// costs[i][k] i号房子用k颜色刷的花费
	// 要让0...N-1的房子相邻不同色
	// 返回最小花费
	public static int minCostII(int[][] costs) {
		int N = costs.length;
		if (N == 0) {
			return 0;
		}
		int K = costs[0].length;
		int preMin1 = 0;
		int preEnd1 = -1;
		int preMin2 = 0;
		int preEnd2 = -1;
		for (int i = 0; i < N; i++) {
			int curMin1 = Integer.MAX_VALUE;
			int curEnd1 = -1;
			int curMin2 = Integer.MAX_VALUE;
			int curEnd2 = -1;
			for (int j = 0; j < K; j++) {
				if (j != preEnd1) {
					if (preMin1 + costs[i][j] < curMin1) {
						curMin2 = curMin1;
						curEnd2 = curEnd1;
						curMin1 = preMin1 + costs[i][j];
						curEnd1 = j;
					} else if (preMin1 + costs[i][j] < curMin2) {
						curMin2 = preMin1 + costs[i][j];
						curEnd2 = j;
					}
				} else if (j != preEnd2) {
					if (preMin2 + costs[i][j] < curMin1) {
						curMin2 = curMin1;
						curEnd2 = curEnd1;
						curMin1 = preMin2 + costs[i][j];
						curEnd1 = j;
					} else if (preMin2 + costs[i][j] < curMin2) {
						curMin2 = preMin2 + costs[i][j];
						curEnd2 = j;
					}
				}
			}
			preMin1 = curMin1;
			preEnd1 = curEnd1;
			preMin2 = curMin2;
			preEnd2 = curEnd2;
		}
		return preMin1;
	}

}
