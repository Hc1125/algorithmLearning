package cn.zju.leetcode;

public class code20_22 {
    public int factorial(int x, int y){
        if(y==0) return 1;
        if(y < 0) return 0;
        int m = 1, n = 1, z = x - y + 1;
        while(x >= z){
            m *= x;
            x--;
        }
        while(y > 0){
            n *= y;
            y--;
        }
        return m/n;
    }

    public int paintingPlan(int n, int k){
        if(k==0 || k == n*n){
            return 1;
        }
        if(k < n){
            return 0;
        }
        float i;
        float j;
        int res = 0;
        for (i = 0; i < n; i++) {
            float x = (k - n*i)/(n-i);
            if(x != (int)x)
                continue;
            j = (int)x;
            res += factorial(n,(int)i) * factorial(n,(int)j);
        }
        return res;
    }

}
