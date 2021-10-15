package cn.zju.group14;

import java.util.HashMap;
import java.util.HashSet;

public class code5_403_FrogJump {

	public static boolean canCross1(int[] stones) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : stones) {
			set.add(num);
		}
		HashMap<Integer, HashMap<Integer, Boolean>> dp = new HashMap<>();
		return jump(1, 1, stones[stones.length - 1], set, dp);
	}

	public static boolean jump(int cur, int pre, int end, HashSet<Integer> set,
			HashMap<Integer, HashMap<Integer, Boolean>> dp) {
		if (cur == end) {
			return true;
		}
		if (!set.contains(cur)) {
			return false;
		}
		if (dp.containsKey(cur) && dp.get(cur).containsKey(pre)) {
			return dp.get(cur).get(pre);
		}
		boolean ans = (pre > 1 && jump(cur + pre - 1, pre - 1, end, set, dp)) 
				|| jump(cur + pre, pre, end, set, dp)
				|| jump(cur + pre + 1, pre + 1, end, set, dp);
		if (!dp.containsKey(cur)) {
			dp.put(cur, new HashMap<>());
		}
		if (!dp.get(cur).containsKey(pre)) {
			dp.get(cur).put(pre, ans);
		}
		return ans;
	}

	public static boolean canCross2(int[] stones) {
		int n = stones.length;
		boolean[][] dp = new boolean[n][n];
		dp[0][0] = true;
		for (int i = 1; i < n; i++) {
			if (stones[i] - stones[i - 1] > i) {
				return false;
			}
		}
		for (int i = 1; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				int k = stones[i] - stones[j];
				if (k > j + 1) {
					break;
				}
				dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
				if (i == n - 1 && dp[i][k]) {
					return true;
				}
			}
		}
		return false;
	}

}
