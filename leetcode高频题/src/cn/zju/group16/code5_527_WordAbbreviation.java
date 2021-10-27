package cn.zju.group16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// 单词缩写

/**
 * 给定一个由n个不重复非空字符串组成的数组，你需要按照以下规则为每个单词生成最小的缩写。
 *
 * 初始缩写由起始字母+省略字母的数量+结尾字母组成。
 * 若存在冲突，亦即多于一个单词有同样的缩写，则使用更长的前缀代替首字母，直到从单词到缩写的映射唯一。换而言之，最终的缩写必须只能映射到一个单词。
 * 若缩写并不比原单词更短，则保留原样。
 * 示例:
 * 输入: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * 输出: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 *
 * 注意:
 * n和每个单词的长度均不超过 400。
 * 每个单词的长度大于 1。
 * 单词只由英文小写字母组成。
 * 返回的答案需要和原数组保持同一顺序。
 */
public class code5_527_WordAbbreviation {

	public static List<String> wordsAbbreviation(List<String> words) {
		int len = words.size();
		List<String> res = new ArrayList<>();
		HashMap<String, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < len; i++) {
			res.add(makeAbbr(words.get(i), 1));
			List<Integer> list = map.getOrDefault(res.get(i), new ArrayList<>());
			list.add(i);
			map.put(res.get(i), list);
		}
		int[] prefix = new int[len];
		for (int i = 0; i < len; i++) {
			if (map.get(res.get(i)).size() > 1) {
				List<Integer> indexes = map.get(res.get(i));
				map.remove(res.get(i));
				for (int j : indexes) {
					prefix[j]++;
					res.set(j, makeAbbr(words.get(j), prefix[j]));
					List<Integer> list = map.getOrDefault(res.get(j), new ArrayList<>());
					list.add(j);
					map.put(res.get(j), list);
				}
				i--;
			}
		}
		return res;
	}

	public static String makeAbbr(String s, int k) {
		if (k >= s.length() - 2) {
			return s;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(s.substring(0, k));
		builder.append(s.length() - 1 - k);
		builder.append(s.charAt(s.length() - 1));
		return builder.toString();
	}

}
