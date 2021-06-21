package cn.zju.leetcode;

public class code12_424 {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        if(len < 2) {
            return len;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = 0;
        int maxCount = 0;
        int[] freq = new int[26];
        while(right < len) {
            freq[chars[right] - 'A']++;
            maxCount = Math.max(maxCount, freq[chars[right] - 'A']);
            right++;
            if(right - left > maxCount + k) {
                freq[chars[left] - 'A']--;
                left++;
            }
        }
        return right - left;
    }
}
