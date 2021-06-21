package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code16_1128 {
    public int numEquivDominoPairs(int[][] dominoes){
        int n = dominoes.length;
        if(dominoes.length == 1){
            return 0;
        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] dominoe : dominoes) {
            int num1 = dominoe[0];
            int num2 = dominoe[1];
            int num = num1 <= num2 ? num1 * 9 + num2 : num2 * 9 + num1;
            int ret = map.getOrDefault(num,0);
            ans += ret;
            map.put(num, ret + 1);
        }
        return ans;
    }
    public int numEquivDominoPairs1(int[][] dominoes){
        int[] num = new int[100];
        int ret = 0;
        for (int[] dominoe : dominoes) {
            int val = dominoe[0] < dominoe[1] ? dominoe[0] * 10 + dominoe[1] : dominoe[1] * 10 + dominoe[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }
}
