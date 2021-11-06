package cn.zju.zuochengyun.Practice5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 A（下标从 1 开始）包含 N 个整数：A1，A2，……，AN 和一个整数 B。
 * 你可以从数组 A 中的任何一个位置（下标为 i）跳到下标 i+1，i+2，……，i+B 的任意一个可以跳到的位置上。
 * 如果你在下标为 i 的位置上，你需要支付 Ai 个金币。
 * 如果 Ai 是 -1，意味着下标为 i 的位置是不可以跳到的。
 *
 * 现在，你希望花费最少的金币从数组 A 的 1 位置跳到 N 位置，你需要输出花费最少的路径，依次输出所有经过的下标（从 1 到 N）。
 * 如果有多种花费最少的方案，输出字典顺序最小的路径。
 *
 * 如果无法到达 N 位置，请返回一个空数组。
 *
 * 样例 1 :
 * 输入: [1,2,4,-1,2], 2
 * 输出: [1,3,5]
 *
 * 样例 2 :
 * 输入: [1,2,4,-1,2], 1
 * 输出: []
 *
 * 注释 :
 * 路径 Pa1，Pa2，……，Pan 是字典序小于 Pb1，Pb2，……，Pbm 的，
 * 	当且仅当第一个 Pai 和 Pbi 不同的 i 满足 Pai < Pbi，
 * 	如果不存在这样的 i 那么满足 n < m。
 *  A1 >= 0。
 *  A2, ..., AN （如果存在） 的范围是 [-1, 100]。
 *  A 数组的长度范围 [1, 1000].
 *  B 的范围 [1, 100].
 */
public class code19_CoinPath {
	public static List<Integer> cheapestJump(int[] arr, int jump) {
		int n = arr.length;
		int[] best = new int[n];
		int[] last = new int[n];
		int[] size = new int[n];
		Arrays.fill(best, Integer.MAX_VALUE);
		Arrays.fill(last, -1);
		best[0] = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] != -1) {
				for (int j = Math.max(0, i - jump); j < i; j++) {
					if (arr[j] != -1) {
						int cur = best[j] + arr[i];
						if (cur < best[i] || (cur == best[i] && size[i] - 1 < size[j])) {
							best[i] = cur;
							last[i] = j;
							size[i] = size[j] + 1;
						}
					}
				}
			}
		}
		List<Integer> path = new LinkedList<>();
		for (int cur = n - 1; cur >= 0; cur = last[cur]) {
			path.add(0, cur + 1);
		}
		return path.get(0) != 1 ? new LinkedList<>() : path;
	}
}
