package cn.zju.group1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class code16_49_GroupAnagrams {

	public static List<List<String>> groupAnagrams1(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			int[] record = new int[26];
			for (char cha : str.toCharArray()) {
				record[cha - 'a']++;
			}
			StringBuilder builder = new StringBuilder();
			for (int value : record) {
				builder.append(value).append("_");
			}
			String key = builder.toString();
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(str);
		}
		List<List<String>> res = new ArrayList<List<String>>();
		res.addAll(map.values());
		return res;
	}

	public static List<List<String>> groupAnagrams2(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			char[] chs = str.toCharArray();
			Arrays.sort(chs);
			String key = String.valueOf(chs);
			List<String> list = map.getOrDefault(key, new ArrayList<String>());
			map.get(key).add(str);
		}
		List<List<String>> res = new ArrayList<List<String>>();
		res.addAll(map.values());
		return res;
	}

}
