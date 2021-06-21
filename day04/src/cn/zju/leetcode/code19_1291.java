package cn.zju.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class code19_1291 {
    public List<Integer> sequentialDigits(int low, int high){
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int num = i;
            for (int j = i + 1; j <= 9; j++) {
                num = num * 10 + j;
                if(low <= num && num <= high){
                    ans.add(num);
                }
                if(num > high){
                    break;
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
