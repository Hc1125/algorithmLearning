package cn.zju.group4;

import java.util.List;

public class code4_139 {
    public class Node {
        boolean end;
        Node[] nexts;
        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Node root = new Node();
        for (String str : wordDict) {
            char[] chars = str.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.nexts[str[end] - 'a'];
                if (cur == null) {
                    break;
                }
                if (cur.end) {
                    dp[i] |= dp[end + 1];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }


}
