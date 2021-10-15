package cn.zju.leetcode4;

public class code20_CountSubStrings {
    public int countSubstrings(String s) {
        char[] str = manacharString(s);
        int R = -1;
        int C = -1;
        int n = str.length;
        int[] pArr = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < n && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            ans += pArr[i] / 2;
        }
        return ans;
    }

    public char[] manacharString(String s) {
        char[] ans = new char[2 * s.length() + 1];
        int index = 0;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (i & 1) == 0 ? '#' : s.charAt(index++);
        }
        return ans;
    }
}
