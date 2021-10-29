package cn.zju.leetcode7;

import java.util.HashMap;
import java.util.Map;

public class code5_65 {
    public class Trie {
        int count;
        Trie[] next;
        public Trie() {
            count = 0;
            next = new Trie[26];
        }
        public Trie get(char c) {
            int index = c - 'a';
            if (next[index] == null) {
                next[index] = new Trie();
                count++;
            }
            return next[index];
        }
    }
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        Map<Trie, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            Trie cur = root;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                cur = cur.get(words[i].charAt(j));
            }
            map.put(cur, i);
        }
        int ans = 0;
        for (Map.Entry<Trie, Integer> entry : map.entrySet()) {
            if (entry.getKey().count == 0) {
                ans += words[entry.getValue()].length() + 1;
            }
        }
        return ans;
    }
}
