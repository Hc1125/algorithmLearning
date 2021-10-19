package cn.zju.leetcode;

public class code8_211 {
    class WordDictionary {
        public class Node {
            Node[] nexts;
            boolean end;

            public Node() {
                nexts = new Node[26];
                end = false;
            }
        }
        Node head;
        public WordDictionary() {
            head = new Node();
        }

        public void addWord(String word) {
            Node cur = head;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = true;
        }

        public boolean search(String word) {
            Node cur = head;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == '.') {
                    for (int j = 0; j < 26; j++) {
                        if (cur.nexts[j] != null && process(cur.nexts[j], word, i + 1)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    int index = word.charAt(i) - 'a';
                    if (cur.nexts[index] == null) {
                        return false;
                    }
                    cur = cur.nexts[index];
                }
            }
            return cur.end;
        }

        public boolean process(Node node, String word, int index) {
            if (index == word.length()) {
                return node.end;
            }
            for (int i = index; i < word.length(); i++) {
                if (word.charAt(i) == '.') {
                    for (int j = 0; j < 26; j++) {
                        if (node.nexts[j] != null && process(node.nexts[j], word, i + 1)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    int c = word.charAt(i) - 'a';
                    if (node.nexts[c] == null) {
                        return false;
                    }
                    node = node.nexts[c];
                }
            }
            return node.end;
        }

    }
}
