package cn.zju.leetcode;

public class code18_1047 {
    public String removeDuplicates(String S) {
        StringBuffer sb = new StringBuffer(S);
        int i = 0;
        while (i < sb.length() - 1) {
            while (i >= 0 && i < (sb.length() - 1) && sb.charAt(i) == sb.charAt(i + 1)) {
                sb.delete(i, i + 2);
                i--;
            }
            i++;
        }
        return sb.toString();

    }
    public String removeDuplicates1(String S) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }
}
