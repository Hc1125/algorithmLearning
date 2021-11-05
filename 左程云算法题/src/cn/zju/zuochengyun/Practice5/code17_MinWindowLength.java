package cn.zju.zuochengyun.Practice5;

/**
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 *
 * (1) 返回最短子字符串的长度
 * (2) 如果s中存在多个符合条件的子字符串，返回任意一个。
 *
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 */
public class code17_MinWindowLength {

	public static int minLength(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return Integer.MAX_VALUE;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] map = new int[256]; // map[37] = 4  37  4次
		for (int i = 0; i != str2.length; i++) {
			map[str2[i]]++;
		}
		int all = str2.length;
		
		// [L,R-1]  R
		// [L,R)  -> [0,0)
		int L = 0;
		int R = 0;
		int minLen = Integer.MAX_VALUE;
		while (R != str1.length) {
			map[str1[R]]--;
			if (map[str1[R]] >= 0) {
				all--;
			}
			if (all == 0) { // 还完了
				while (map[str1[L]] < 0) {
					map[str1[L++]]++;
				}
				// [L..R]
				minLen = Math.min(minLen, R - L + 1);
				all++;
				map[str1[L++]]++;
			}
			R++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	public String minWindow(String s, String t) {
		int m = s.length();
		int n = t.length();
		if (m < n) {
			return "";
		}
		int[] cnt = new int[58];
		for (int i = 0; i < n; i++) {
			cnt[t.charAt(i) - 'A']--;
		}
		int l = 0;
		int ans = Integer.MAX_VALUE, ansL = -1, ansR = -1;
		for (int i = 0; i < m; i++) {
			cnt[s.charAt(i) - 'A']++;
			while (check(cnt)) {
				if (i - l < ans) {
					ans = i - l;
					ansL = l;
					ansR = i + 1;
				}
				cnt[s.charAt(l++) - 'A']--;
			}
		}
		return ans == Integer.MAX_VALUE ? "" : s.substring(ansL, ansR);
	}

	public boolean check(int[] cnt) {
		for (int i : cnt) {
			if (i < 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String str1 = "adabbca";
		String str2 = "acba";
		System.out.println(minLength(str1, str2));
	}

}
