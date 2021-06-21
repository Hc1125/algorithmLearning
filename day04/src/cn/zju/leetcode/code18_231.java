package cn.zju.leetcode;

public class code18_231 {
    public boolean isPowerOfTwo(int n){
        double temp = n;
        while(temp > 0){
            if(temp == 1){
                return true;
            }
            if(temp < 1){
                return false;
            }
            temp /= 2;
        }
        return false;
    }
    public boolean isPowerOfTwo1(int n){
        if(n <= 0){
            return false;
        }
        long x = (long)n;
        return (x & (x - 1)) == 0;
    }
}
