package cn.zju.leetcode7;

import java.util.List;

public class code3_63 {
    public class Trie {
        boolean end;
        Trie[] next;
        public Trie() {
            end = false;
            next = new Trie[26];
        }

        public void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.next[c] == null) {
                    cur.next[c] = new Trie();
                }
                cur = cur.next[c];
            }
            cur.end = true;
        }

        public String search(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.next[c] == null) {
                    return word;
                }
                cur = cur.next[c];
                if (cur.end) {
                    return word.substring(0, i + 1);
                }
            }
            return word;
        }
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for (String s : dictionary) {
            root.insert(s);
        }
        String[] arr = sentence.split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = root.search(arr[i]);
        }
        StringBuilder ans = new StringBuilder();
        for (String s : arr) {
            ans.append(s).append(" ");
        }
        return ans.deleteCharAt(ans.length() - 1).toString();
    }
}
