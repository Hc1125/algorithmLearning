package cn.zju.group14;

// 最短独占单词缩写

/**
 * A string such as "word" contains the following abbreviations:
 *
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Given a target string and a set of strings in a dictionary,
 * find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.
 *
 * Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.
 *
 * Note:
 * In the case of multiple answers as shown in the second example below, you may return any one of them.
 * Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
 * Examples:
 * "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
 *
 * "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 */
public class code6_411_MinimumUniqueWordAbbreviation {

	// 区分出来之后，缩写的长度，最短是多少？
	public static int min = Integer.MAX_VALUE;

	// 取得缩写的长度最短的时候，决定是什么(fix)
	public static int best = 0;

	public static int abbrLen(int fix, int len) {
		int ans = 0;
		int cnt = 0;
		for (int i = 0; i < len; i++) {
			if ((fix & (1 << i)) != 0) {
				ans++;
				if (cnt != 0) {
					ans += (cnt > 9 ? 2 : 1);
				}
				cnt = 0;
			} else {
				cnt++;
			}
		}
		if (cnt != 0) {
			ans += (cnt > 9 ? 2 : 1);
		}
		return ans;
	}

	// 原始的字典，被改了
	// target : abc  字典中的词 : bbb   ->  101 -> int -> 
	// fix -> int -> 根本不用值，用状态 -> 每一位保留还是不保留的决定
	public static boolean canFix(int[] words, int fix) {
		for (int word : words) {
			if ((fix & word) == 0) {
				return false;
			}
		}
		return true;
	}

	// 利用位运算加速
	public static String minAbbreviation1(String target, String[] dictionary) {
		min = Integer.MAX_VALUE;
		best = 0;
		char[] t = target.toCharArray();
		int len = t.length;
		int siz = 0;
		for (String word : dictionary) {
			if (word.length() == len) {
				siz++;
			}
		}
		int[] words = new int[siz];
		int index = 0;
		for (String word : dictionary) {
			if (word.length() == len) {
				char[] w = word.toCharArray();
				int status = 0;
				for (int j = 0; j < len; j++) {
					if (t[j] != w[j]) {
						status |= 1 << j;
					}
				}
				words[index++] = status;
			}
		}
		dfs1(words, len, 0, 0);
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (int i = 0; i < len; i++) {
			if ((best & (1 << i)) != 0) {
				if (count > 0) {
					builder.append(count);
				}
				builder.append(t[i]);
				count = 0;
			} else {
				count++;
			}
		}
		if (count > 0) {
			builder.append(count);
		}
		return builder.toString();
	}

	// 所有字典中的单词现在都变成了int，放在words里
	// 0....len-1 位去决定保留还是不保留！当前来到index位
	// 之前做出的决定!
	public static void dfs1(int[] words, int len, int fix, int index) {
		if (!canFix(words, fix)) {
			if (index < len) {
				dfs1(words, len, fix, index + 1);
				dfs1(words, len, fix | (1 << index), index + 1);
			}
		} else {
			// 决定是fix，一共的长度是len，求出缩写是多长？
			int ans = abbrLen(fix, len);
			if (ans < min) {
				min = ans;
				best = fix;
			}
		}
	}

	// 进一步设计剪枝，注意diff的用法
	public static String minAbbreviation2(String target, String[] dictionary) {
		min = Integer.MAX_VALUE;
		best = 0;
		char[] t = target.toCharArray();
		int len = t.length;
		int siz = 0;
		for (String word : dictionary) {
			if (word.length() == len) {
				siz++;
			}
		}
		int[] words = new int[siz];
		int index = 0;
		// 用来剪枝
		int diff = 0;
		for (String word : dictionary) {
			if (word.length() == len) {
				char[] w = word.toCharArray();
				int status = 0;
				for (int j = 0; j < len; j++) {
					if (t[j] != w[j]) {
						status |= 1 << j;
					}
				}
				words[index++] = status;
				diff |= status;
			}
		}
		dfs2(words, len, diff, 0, 0);
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (int i = 0; i < len; i++) {
			if ((best & (1 << i)) != 0) {
				if (count > 0) {
					builder.append(count);
				}
				builder.append(t[i]);
				count = 0;
			} else {
				count++;
			}
		}
		if (count > 0) {
			builder.append(count);
		}
		return builder.toString();
	}

	public static void dfs2(int[] words, int len, int diff, int fix, int index) {
		if (!canFix(words, fix)) {
			if (index < len) {
				dfs2(words, len, diff, fix, index + 1);
				if ((diff & (1 << index)) != 0) {
					dfs2(words, len, diff, fix | (1 << index), index + 1);
				}
			}
		} else {
			int ans = abbrLen(fix, len);
			if (ans < min) {
				min = ans;
				best = fix;
			}
		}
	}

}
