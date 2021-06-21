package cn.zju.leetcode;

public class code14_680 {
    public boolean validPalindrome(String s){
        int low = 0, high = s.length() - 1;
        char[] chars = s.toCharArray();
        while (low < high) {
            if (chars[low] == chars[high]) {
                low++;
                high--;
            } else {
                boolean flag1 = true, flag2 = true;
                for (int i = low, j = high - 1; i < j; i++, j--) {
                    if (chars[i] != chars[j]) {
                        flag1 = false;
                        break;
                    }
                }
                for (int i = low + 1, j = high; i < j; i++, j--) {
                    if (chars[i] != chars[j]) {
                        flag2 = false;
                        break;
                    }
                }
                return flag1 || flag2;
            }
        }
        return true;
    }

}
