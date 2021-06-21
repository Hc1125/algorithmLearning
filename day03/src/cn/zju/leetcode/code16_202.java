package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code16_202 {
    public boolean isHappy1(int n){
        Set<Integer> seen = new HashSet<Integer>();
        seen.add(n);
        while(n != 1){
            n = bitSquareSum(n);
            if(!seen.add(n)){
                return false;
            }
        }
        return true;
    }
    public int bitSquareSum(int x){
        int sum = 0, cur;
        while(x > 0){
            cur = x % 10;
            sum += cur * cur;
            x /= 10;
        }
        return sum;
    }
    public boolean isHappy2(int n){
        int slow = n, fast = n;
        do{
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        }while(slow != fast);
        return slow == 1;
    }
}
