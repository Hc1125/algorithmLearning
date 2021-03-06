package cn.zju.leetcode;

import java.util.*;

public class code4_212 {
    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass;
        public int end;
        public TrieNode() {
            nexts = new TrieNode[26];
            pass = 0;
            end = 0;
        }
    }

    public static void fillWord(TrieNode head, String word) {
        head.pass++;
        char[] chs = word.toCharArray();
        int index = 0;
        TrieNode node = head;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public static String generatePath(LinkedList<Character> path) {
        char[] str = new char[path.size()];
        int index = 0;
        for (Character character : path) {
            str[index++] = character;
        }
        return String.valueOf(str);
    }

    public static List<String> findWords(char[][] board, String[] words) {
        TrieNode head = new TrieNode();
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            if (!set.contains(word)) {
                fillWord(head, word);
                set.add(word);
            }
        }
        List<String> ans = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                process(board, row, col, path, head, ans);
            }
        }
        return ans;
    }

    public static int process(
            char[][] board,
            int row, int col,
            LinkedList<Character> path,
            TrieNode cur,
            List<String> res) {
        char cha = board[row][col];
        if (cha == 0) {
            return 0;
        }
        int index = cha - 'a';
        if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {
            return 0;
        }
        cur = cur.nexts[index];
        path.addLast(cha);
        int fix = 0;

        if (cur.end > 0) {
            res.add(generatePath(path));
            cur.end--;
            fix++;
        }
        board[row][col] = 0;
        if (row > 0) {
            fix += process(board, row - 1, col, path, cur, res);
        }
        if (row < board.length - 1) {
            fix += process(board, row + 1, col, path, cur, res);
        }
        if (col > 0) {
            fix += process(board, row, col - 1, path, cur, res);
        }
        if (col < board[0].length - 1) {
            fix += process(board, row, col + 1, path, cur, res);
        }
        board[row][col] = cha;
        path.pollLast();
        cur.pass -= fix;
        return fix;
    }
}
