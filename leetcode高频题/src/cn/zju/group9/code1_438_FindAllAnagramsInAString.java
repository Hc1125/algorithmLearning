package cn.zju.group9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class code1_438_FindAllAnagramsInAString {
	public List<Integer> findAnagrams1(String s, String p) {
		List<Integer> ans = new ArrayList<>();
		int m = s.length();
		int n = p.length();
		if (m < n) {
			return ans;
		}
		int[] cnt = new int[26];
		for (int i = 0; i < n; i++) {
			--cnt[p.charAt(i) - 'a'];
		}
		int l = 0;
		for (int i = 0; i < m; i++) {
			int x = s.charAt(i) - 'a';
			cnt[x]++;
			while (cnt[x] > 0) {
				cnt[s.charAt(l++) - 'a']--;
			}
			if (i - l + 1 == n) {
				ans.add(l);
			}
		}
		return ans;
	}

	public static List<Integer> findAnagrams2(String s, String p) {
		List<Integer> ans = new ArrayList<>();
		if (s == null || p == null || s.length() < p.length()) {
			return ans;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		char[] pst = p.toCharArray();
		int M = pst.length;
		HashMap<Character, Integer> map = new HashMap<>();
		for (char cha : pst) {
			map.put(cha, map.getOrDefault(cha, 0) + 1);
		}
		int all = M;
		for (int end = 0; end < M - 1; end++) {
			if (map.containsKey(str[end])) {
				int count = map.get(str[end]);
				if (count > 0) {
					all--;
				}
				map.put(str[end], count - 1);
			}
		}
		for (int end = M - 1, start = 0; end < N; end++, start++) {
			if (map.containsKey(str[end])) {
				int count = map.get(str[end]);
				if (count > 0) {
					all--;
				}
				map.put(str[end], count - 1);
			}
			if (all == 0) {
				ans.add(start);
			}
			if (map.containsKey(str[start])) {
				int count = map.get(str[start]);
				if (count >= 0) {
					all++;
				}
				map.put(str[start], count + 1);
			}
		}
		return ans;
	}

}
