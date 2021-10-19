package cn.zju.leetcode5;

public class code14_34 {
    public boolean isAlienSorted(String[] words, String order) {
        int[] count = new int[26];
        for (int i = 0; i < order.length(); i++) {
            count[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            char[] str1 = words[i - 1].toCharArray();
            char[] str2 = words[i].toCharArray();
            int j = 0;
            while (j < str1.length || j < str2.length) {
                int a = j < str1.length ? count[str1[j] - 'a'] : -1;
                int b = j < str2.length ? count[str2[j] - 'a'] : -1;
                if (a > b) {
                    return false;
                } else if (a == b) {
                    j++;
                } else {
                    break;
                }
            }
        }
        return true;
    }
}
