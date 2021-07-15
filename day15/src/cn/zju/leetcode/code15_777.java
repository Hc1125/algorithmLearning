package cn.zju.leetcode;

public class code15_777 {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        char[] s = start.toCharArray(), e = end.toCharArray();
        int i = 0, j = 0;
        while (i < n || j < n) {
            while (i < n && s[i] == 'X') i++;
            while (j < n && e[j] == 'X') j++;
            if ((i < n) ^ (j < n)) return false;
            if (i < n && j < n) {
                if (s[i] != e[j] || (s[i] == 'L' && i < j) || (s[i] == 'R' && i > j)) {
                    return false;
                }
            }
            i++;
            j++;
        }
        return true;
    }
}
