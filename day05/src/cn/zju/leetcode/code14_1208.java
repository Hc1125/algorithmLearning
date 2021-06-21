package cn.zju.leetcode;

public class code14_1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int left = 0, right = 0;
        int cost = 0;
        int maxLen = 0;
        while(right < n){
            cost += diff[right++];
            while(cost > maxCost){
                cost -= diff[left++];
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}
