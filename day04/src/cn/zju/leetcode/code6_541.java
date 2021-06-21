package cn.zju.leetcode;


import java.util.Arrays;

public class code6_541 {
    public String reverseStr(String s, int k){
        char[] chars = s.toCharArray();
        int n = chars.length;
        int i = 0;
        while(n - i >= 2 * k){
            int left = i;
            int right = i + k - 1;
            while(left < right){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
            i += 2 * k;
        }
        if(n - i  > 0 && n - i  < k){
            int left = i;
            int right = n - 1;
            while(left < right){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        if(n - i  >= k && n - i  < 2 * k){
            int left = i;
            int right = i + k - 1;
            while(left < right){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    public String reverseStr1(String s, int k){
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while(i < j){
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }
}
