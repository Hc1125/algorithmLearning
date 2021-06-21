package cn.zju.leetcode2;

import java.util.Arrays;

public class code9_45 {
    public String minNumber(int[] nums) {
        String[] strings= new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        quickSort(strings, 0, strings.length - 1);
        StringBuffer sb = new StringBuffer();
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }
    public void quickSort(String[] strings, int l , int r) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = strings[i];
        while (i < j) {
            while ((strings[j] + strings[l]).compareTo(strings[l] + strings[j]) >= 0 && i < j) j--;
            while ((strings[i] + strings[l]).compareTo(strings[l] + strings[i]) <= 0 && i < j) i++;
            tmp = strings[i];
            strings[i] = strings[j];
            strings[j] = tmp;
        }
        strings[i] = strings[l];
        strings[l] = tmp;
        quickSort(strings, l, i - 1);
        quickSort(strings, i + 1, r);
    }
}
