package cn.zju.leetcode2;

public class code16_58_II {
    public String reverseLeftWords(String s, int n) {
        StringBuffer sb = new StringBuffer(s);
        sb.append(s);
        return sb.toString().substring(n, n + s.length());
    }
}
