package cn.zju.group5;

public class code7_242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int[] count = new int[26];
        for (char c : str1) {
            count[c - 'a']++;
        }
        for (char c : str2) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
