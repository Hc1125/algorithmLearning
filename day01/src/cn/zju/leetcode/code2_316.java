package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class code2_316 {
    public String removeDuplicateLetters(String s){
        char[] chars = s.toCharArray();
        int[] lastInx = new int[26];
        for (int i = 0; i < chars.length; i++) {
            lastInx[chars[i]-'a']=i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[26];
        for (int i = 0; i < chars.length; i++) {
            if(visited[chars[i]-'a']){
                continue;
            }
            while(!stack.isEmpty() && stack.peekLast() > chars[i] && lastInx[stack.peekLast() - 'a'] > i){
                Character c = stack.removeLast();
                visited[c - 'a'] = false;
            }
            stack.addLast(chars[i]);
            visited[chars[i] - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
