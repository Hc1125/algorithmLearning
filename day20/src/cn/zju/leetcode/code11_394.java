package cn.zju.leetcode;


import java.util.Collections;
import java.util.LinkedList;

public class code11_394 {
    int i;
    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                String digit = getDigit(s);
                stack.addLast(digit);
            } else if (c == '[' || Character.isLetter(c)) {
                stack.addLast(String.valueOf(s.charAt(i++)));
            } else {
                i++;
                LinkedList<String> temp = new LinkedList<>();
                while (!"[".equals(stack.peekLast())) {
                    temp.addFirst(stack.removeLast());
                }
                stack.removeLast();
                int time = Integer.parseInt(stack.removeLast());
                String t = getString(temp);
                StringBuilder res = new StringBuilder();
                for (int j = 0; j < time; j++) {
                    res.append(t);
                }
                stack.addLast(res.toString());
            }
        }
        return getString(stack);
    }

    public String getDigit(String s) {
        StringBuilder ans = new StringBuilder();
        while (Character.isDigit(s.charAt(i))) {
            ans.append(s.charAt(i++));
        }
        return ans.toString();
    }

    public String getString(LinkedList<String> list) {
        StringBuilder ans = new StringBuilder();
        for (String s : list) {
            ans.append(s);
        }
        return ans.toString();
    }
}
