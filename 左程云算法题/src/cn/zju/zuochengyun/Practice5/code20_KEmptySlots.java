package cn.zju.zuochengyun.Practice5;

/**
 * 花园里有 N 个花盆，每个花盆里都有一朵花。这 N 朵花会在 N 天内依次开放，每天有且仅有一朵花会开放并且会一直盛开下去。
 * 给定一个数组 flowers 包含从 1 到 N 的数字，每个数字表示在那一天开放的花所在的花盆编号。
 * 例如， flowers[i] = x 表示在第 i 天盛开的花在第 x 个花盆中，i 和 x 都在 1 到 N 的范围内。
 * 给你一个整数 k，请你输出在哪一天恰好有两朵盛开的花，他们中间间隔了 k 朵花并且都没有开放。
 * 如果不存在，输出 -1。
 *
 * 样例 1:
 * 输入:
 * flowers: [1,3,2]
 * k: 1
 * 输出: 2
 * 解释: 在第二天，第一朵和第三朵花都盛开了。
 *
 * 样例 2:
 * 输入:
 * flowers: [1,2,3]
 * k: 1
 * 输出: -1
 *
 * 注释 :
 * 给定的数组范围是 [1, 20000]。
 */
public class code20_KEmptySlots {

	public static int kEmptySlots(int[] bulbs, int k) {
		int n = bulbs.length;
		int[] days = new int[n];
		for (int i = 0; i < n; i++) {
			days[bulbs[i] - 1] = i + 1;
		}
		int left = 0, right = k + 1, res = Integer.MAX_VALUE;
		for (int mid = 1; right < n; mid++) {
			if (days[mid] <= Math.max(days[left], days[right])) {
				if (mid == right) {
					res = Math.min(res, Math.max(days[left], days[right]));
				}
				left = mid;
				right = mid + k + 1;
			}
		}
		return (res == Integer.MAX_VALUE) ? -1 : res;
	}

}
