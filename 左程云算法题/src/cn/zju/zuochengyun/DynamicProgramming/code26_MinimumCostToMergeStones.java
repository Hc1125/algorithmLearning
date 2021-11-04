package cn.zju.zuochengyun.DynamicProgramming;

// 合并石头的最低代价
// 本题测试链接 : https://leetcode.com/problems/minimum-cost-to-merge-stones/
public class code26_MinimumCostToMergeStones {

//	// arr[L...R]一定要整出P份，合并的最小代价，返回！
//	public static int f(int[] arr, int K, int L, int R, int P) {
//		if(从L到R根本不可能弄出P份) {
//			return Integer.MAX_VALUE;
//		}
//		// 真的有可能的！
//		if(P == 1) {
//			return L == R ? 0 : (f(arr, K, L, R, K) + 最后一次大合并的代价);
//		}
//		int ans = Integer.MAX_VALUE;
//		// 真的有可能，P > 1
//		for(int i = L; i < R;i++) {
//			// L..i(1份)  i+1...R(P-1份)
//			int left = f(arr, K, L, i, 1);
//			if(left == Integer.MAX_VALUE) {
//				continue;
//			}
//			int right = f(arr, K, i+1,R,P-1);
//			if(right == Integer.MAX_VALUE) {
//				continue;
//			}
//			int all = left + right;
//			ans = Math.min(ans, all);
//		}
//		return ans;
//	}

	// 定义dp[i][j]为尽可能多的合并区间[i, j] 所需的成本，不一定能合并成一堆，
	// 但合并完成后剩下的堆数一定小于k，更具体地，剩余的堆数一定是(n - 1) % (k - 1) + 1。

	public int mergeStones(int[] stones, int k) {
		int n = stones.length;
		if ((n - 1) % (k - 1) != 0) return -1;
		int[][] dp = new int[n + 1][n + 1];
		int[] sum = new int[n + 1];
		for (int i = 1; i <= n; ++i) sum[i] = sum[i - 1] + stones[i - 1];
		for (int len = k; len <= n; ++len) { // 枚举区间长度
			for (int i = 1; i + len - 1 <= n; ++i) { // 枚举区间起点
				int j = i + len - 1;
				dp[i][j] = Integer.MAX_VALUE;
				for (int p = i; p < j; p += k - 1) {  // 枚举分界点
					dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j]);
				}
				if ((j - i) % (k - 1) == 0) dp[i][j] += sum[j] - sum[i - 1];
			}
		}
		return dp[1][n];
	}

	public static int mergeStones1(int[] stones, int K) {
		int n = stones.length;
		if ((n - 1) % (K - 1) > 0) {
			return -1;
		}
		int[] presum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			presum[i + 1] = presum[i] + stones[i];
		}
		return process1(0, n - 1, 1, stones, K, presum);
	}

	// part >= 1
	// arr[L..R] 一定要弄出part份，返回最低代价
	// arr、K、presum（前缀累加和数组，求i..j的累加和，就是O(1)了）
	public static int process1(int L, int R, int P, int[] arr, int K, int[] presum) {
		if (L == R) { // arr[L..R]
			return P == 1 ? 0 : -1;
		}
		// L ... R 不只一个数
		if (P == 1) {
			int next = process1(L, R, K, arr, K, presum);
			if (next == -1) {
				return -1;
			} else {
				return next + presum[R + 1] - presum[L];
			}
		} else { // P > 1
			int ans = Integer.MAX_VALUE;
			// L...mid是第1块，剩下的是part-1块
			for (int mid = L; mid < R; mid += K - 1) {
				// L..mid(一份) mid+1...R(part - 1)
				int next1 = process1(L, mid, 1, arr, K, presum);
				int next2 = process1(mid + 1, R, P - 1, arr, K, presum);
				if (next1 != -1 && next2 != -1) {
					ans = Math.min(ans, next1 + next2);
				}
			}
			return ans;
		}
	}

	public static int mergeStones2(int[] stones, int K) {
		int n = stones.length;
		if ((n - 1) % (K - 1) > 0) { // n个数，到底能不能K个相邻的数合并，最终变成1个数！
			return -1;
		}
		int[] presum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			presum[i + 1] = presum[i] + stones[i];
		}
		int[][][] dp = new int[n][n][K + 1];
		return process2(0, n - 1, 1, stones, K, presum, dp);
	}

	public static int process2(int L, int R, int P, int[] arr, int K, int[] presum, int[][][] dp) {
		if (dp[L][R][P] != 0) {
			return dp[L][R][P];
		}
		if (L == R) {
			return P == 1 ? 0 : -1;
		}
		if (P == 1) {
			int next = process2(L, R, K, arr, K, presum, dp);
			if (next == -1) {
				dp[L][R][P] = -1;
				return -1;
			} else {
				dp[L][R][P] = next + presum[R + 1] - presum[L];
				return next + presum[R + 1] - presum[L];
			}
		} else {
			int ans = Integer.MAX_VALUE;
			// i...mid是第1块，剩下的是part-1块
			for (int mid = L; mid < R; mid += K - 1) {
				int next1 = process2(L, mid, 1, arr, K, presum, dp);
				int next2 = process2(mid + 1, R, P - 1, arr, K, presum, dp);
				if (next1 == -1 || next2 == -1) {
					dp[L][R][P] = -1;
					return -1;
				} else {
					ans = Math.min(ans, next1 + next2);
				}
			}
			dp[L][R][P] = ans;
			return ans;
		}
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (maxSize * Math.random()) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int maxSize = 12;
		int maxValue = 100;
		System.out.println("Test begin");
		for (int testTime = 0; testTime < 100000; testTime++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int K = (int) (Math.random() * 7) + 2;
			int ans1 = mergeStones1(arr, K);
			int ans2 = mergeStones2(arr, K);
			if (ans1 != ans2) {
				System.out.println(ans1);
				System.out.println(ans2);
			}
		}

	}

}
