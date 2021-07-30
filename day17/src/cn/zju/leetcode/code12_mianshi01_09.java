package cn.zju.leetcode;

public class code12_mianshi01_09 {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() ?
                new StringBuilder(s1).append(s1).toString().indexOf(s2) != -1 : false;
    }
}
