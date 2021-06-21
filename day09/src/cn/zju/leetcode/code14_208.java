package cn.zju.leetcode;

public class code14_208 {
    class Trie {
        private Trie[] nexts;
        private boolean isEnd;
        /** Initialize your data structure here. */
        public Trie() {
            nexts = new Trie[26];
            isEnd = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] chars = word.toCharArray();
            Trie node = this;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Trie();
                }
                node = node.nexts[index];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            Trie node = this;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return false;
                }
                node = node.nexts[index];
            }
            return node.isEnd == true;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            Trie node = this;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return false;
                }
                node = node.nexts[index];
            }
            return true;
        }
    }
}
