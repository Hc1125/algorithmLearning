package cn.zju.leetcode7;

public class code2_62 {
    class Trie {
        boolean end;
        Trie[] next;
        /** Initialize your data structure here. */
        public Trie() {
            end = false;
            next = new Trie[26];
        }

        /** Inserts a word into the trie. */
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

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.next[c] == null) {
                    return false;
                }
                cur = cur.next[c];
            }
            return cur.end;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie cur = this;
            for (int i = 0; i < prefix.length(); i++) {
                int c = prefix.charAt(i) - 'a';
                if (cur.next[c] == null) {
                    return false;
                }
                cur = cur.next[c];
            }
            return true;
        }
    }
}
