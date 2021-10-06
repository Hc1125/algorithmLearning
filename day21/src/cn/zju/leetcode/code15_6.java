package cn.zju.leetcode;

public class code15_6 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        int cycleLen  = 2 * numRows - 2;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ans.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    ans.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return ans.toString();
    }
}
