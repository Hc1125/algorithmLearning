package cn.zju.group14;

import java.util.HashSet;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 *
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */
// 字符串模式匹配
public class code1_291_WordPatternII {

	public static boolean wordPatternMatch(String pattern, String str) {
		return match(str, pattern, 0, 0, new String[26], new HashSet<>());
	}

	public static boolean match(String s, String p, int si, int pi, String[] map, HashSet<String> set) {
		if (pi == p.length() && si == s.length()) {
			return true;
		}
		if (pi == p.length() || si == s.length()) {
			return false;
		}
		char ch = p.charAt(pi);
		String cur = map[ch - 'a'];
		if (cur != null) {
			return si + cur.length() <= s.length() && cur.equals(s.substring(si, si + cur.length()))
					&& match(s, p, si + cur.length(), pi + 1, map, set);
		}
		int end = s.length();
		for (int i = p.length() - 1; i > pi; i--) {
			end -= map[p.charAt(i) - 'a'] == null ? 1 : map[p.charAt(i) - 'a'].length();
		}
		for (int i = si; i < end; i++) {
			cur = s.substring(si, i + 1);
			if (!set.contains(cur)) {
				set.add(cur);
				map[ch - 'a'] = cur;
				if (match(s, p, i + 1, pi + 1, map, set)) {
					return true;
				}
				map[ch - 'a'] = null;
				set.remove(cur);
			}
		}
		return false;
	}

}
