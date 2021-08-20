package cn.zju.group5;

import java.util.*;

public class code9_269_AlienDictionary {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        int N = words.length;
        Map<Character, Integer> indegree = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (char c : words[i].toCharArray()) {
                indegree.put(c, 0);
            }
        }
        Map<Character, HashSet<Character>> graph = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            int len = Math.min(cur.length, next.length);
            int j = 0;
            for (j = 0; j < len; j++) {
                if (cur[j] != next[j]) {
                    if (!graph.containsKey(cur[j])) {
                        graph.put(cur[j], new HashSet<>());
                    }
                    if (!graph.get(cur[j]).contains(next[j])) {
                        graph.get(cur[j]).add(next[j]);
                        indegree.put(next[j], indegree.get(next[j]) + 1);
                    }
                    break;
                }
            }
            if (j < cur.length && j == next.length) {
                return "";
            }
        }
        StringBuilder ans = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        while (!queue.isEmpty()) {
            char cur = queue.poll();
            ans.append(cur);
            if (graph.containsKey(cur)) {
                for (Character next : graph.get(cur)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return ans.length() == indegree.size() ? ans.toString() : "";
    }
}
