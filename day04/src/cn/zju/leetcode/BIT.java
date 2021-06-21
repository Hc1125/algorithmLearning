package cn.zju.leetcode;

public class BIT {
    int n;
    int[] tree;
    public BIT(int n){
        this.n = n;
        this.tree = new int[n+1];
    }
    public static int lowbit(int x){
        return x & (-x);
    }
    public void update(int x){
        while(x <= n){
            tree[x]++;
            x += lowbit(x);
        }
    }
    public int query(int x){
        int ans = 0;
        while(x > 0){
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }
    public int query(int x, int y){
        return query(y) - query(x - 1);
    }
}
