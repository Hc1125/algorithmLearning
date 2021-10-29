package cn.zju.leetcode7;

import java.util.*;

public class code4_64 {
    class MagicDictionary {
        Set<String> set;
        Map<String, Integer> map;
        public MagicDictionary() {
            set = new HashSet();
            map = new HashMap();
        }

        private List<String> generateNeighbors(String word) {
            List<String> ans = new ArrayList<>();
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                char t = str[i];
                str[i] = '*';
                ans.add(new String(str));
                str[i] = t;
            }
            return ans;
        }
        public void buildDict(String[] words) {
            for (String word : words) {
                set.add(word);
                for (String neighbor : generateNeighbors(word)) {
                    map.put(neighbor, map.getOrDefault(neighbor, 0) + 1);
                }
            }
        }

        public boolean search(String word) {
            for (String neighbor : generateNeighbors(word)) {
                int c = map.getOrDefault(neighbor, 0);
                if (c > 1 || (c == 1 && !set.contains(word))) return true;
            }
            return false;
        }
    }
}
