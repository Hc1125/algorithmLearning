package cn.zju.zuochengyun.DynamicProgramming;

// leetcode 600
// 数位dp
public class code23_NonnegativeIntegersWithoutConsecutiveOnes {

//	// f(0, false, 5,n)
//	// 6 5 ... 0 -1 n
//	// 0 ? 停
//
//	//     5 4 3 2 1 0 -1
//	// n : 1 0 1 1 1 0
//	// 0 1 i
//	// pre : 第i+1位做的决定，0还是1
//	// 在 第i+1位做的决定 是pre的情况下，从index位开始，往后都做决定！
//	// 但是，不能有相邻的1，请问有多少决定！返回！
//	// alreadyLess : 之前的决定，是不是已经导致你到index的时候，已经比n小了！
//	// pre -> 0 1
//	// alreadyLess -> true false
//	// index -> n的位数，（logN）
//	// 2 * 2 * logN
//	// dp[2][]
//	// int alreadyLess  0  1
//	public int f(int pre, boolean alreadyLess, int index, int n) {
//		if (index == -1) {
//			return 1;
//		}
//		if (pre == 1) {
//			// 只能做0的决定，然后去往i-1位置
//			boolean curLessOrMore = alreadyLess | ((n & 1 << index) != 0);
//			return f(0, curLessOrMore, index - 1, n);
//		} else { // pre == 0的决定
//			// 可能性1，继续做0的决定
//			boolean curLessOrMore = alreadyLess | ((n & 1 << index) != 0);
//			int p1 = f(0, curLessOrMore, index - 1, n);
//			// 可能性2，做1的决定
//			// 1)pre == 0的决定, 2)
//			int p2 = 0;
//			if (alreadyLess || (n & 1 << index) != 0) {
//				p2 = f(1, alreadyLess, index - 1, n);
//			}
//			return p1 + p2;
//		}
//	}
	public int findIntegers1(int n) {
		int[] dp = new int[31];
		dp[0] = dp[1] = 1;
		for (int i = 2; i < 31; ++i) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		int pre = 0, res = 0;
		for (int i = 29; i >= 0; --i) {
			int val = 1 << i;
			if ((n & val) != 0) {
				res += dp[i + 1];
				if (pre == 1) {
					break;
				}
				pre = 1;
			} else {
				pre = 0;
			}

			if (i == 0) {
				++res;
			}
		}
		return res;
	}

	public static int findIntegers2(int n) {
		int i = 31;
		for (; i >= 0; i--) {
			if ((n & (1 << i)) != 0) {
				break;
			}
		}
		// for循环出来之后，i表示，n最高位的1，在哪？
		// 从这个位置，往右边低位上走！
		int[][][] dp = new int[2][2][i + 1];
		return f(0, 0, i, n, dp);
	}

	
	public static int f(int pre, int alreadyLess, int index, int num, int[][][] dp) {
		if (index == -1) {
			return 1;
		}
		if (dp[pre][alreadyLess][index] != 0) {
			return dp[pre][alreadyLess][index];
		}
		int ans = 0;
		if (pre == 1) {
			ans = f(0, Math.max(alreadyLess, (num & (1 << index)) != 0 ? 1 : 0), index - 1, num, dp);
		} else {
			if ((num & (1 << index)) == 0 && alreadyLess == 0) {
				ans = f(0, alreadyLess, index - 1, num, dp);
			} else {
				ans = f(1, alreadyLess, index - 1, num, dp)
						+ f(0, Math.max(alreadyLess, (num & (1 << index)) != 0 ? 1 : 0), index - 1, num, dp);
			}
		}
		dp[pre][alreadyLess][index] = ans;
		return ans;
	}

	static int N = 50;
	// f[i][j] 为考虑二进制长度为 i，而且最高位为 j（0 or 1）时的合法数个数（值不超过）
	// 如 f[2][1] 代表二进制长度为 2，且（值不超过）最高位为 1 的合法数的个数为 3 个：10、01、00
	static int[][] f = new int[N][2];
	static {
		f[1][0] = 1; f[1][1] = 2;
		for (int i = 1; i < N - 1; i++) {
			f[i + 1][0] = f[i][1];
			f[i + 1][1] = f[i][0] + f[i][1];
		}
	}
	int getLen(int n) {
		for (int i = 31; i >= 0; i--) {
			if (((n >> i) & 1) == 1) return i;
		}
		return 0;
	}
	public int findIntegers3(int n) {
		int len = getLen(n);
		int ans = 0, prev = 0;
		for (int i = len; i >= 0; i--) {
			// 当前位是 0 还是 1
			int cur = ((n >> i) & 1);
			// 由于始终要满足小于等于的要求，如果当前位本来为 1 的话，填成 0 的话，后面的低位无论怎么填，都是满足小于等于的要求的，因此将 f[i + 1][0] 累加到答案
			if (cur == 1) ans += f[i + 1][0];
			// 出现连续位为 1，分支结束，方案数被计算完
			if (prev == 1 && cur == 1) break;
			prev = cur;
			if (i == 0) ans++;
		}
		return ans;
	}

}
