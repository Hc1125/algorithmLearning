package cn.zju.leetcode;

public class code5_746 {
    public static void main(String[] args) {


    }
    public int mitCostClimbingStairs(int[] cost){
        int n = cost.length;
        int prev = 0,curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr+cost[i-1],prev+cost[i-2]);
            prev=curr;
            curr=next;
        }
        return curr;
    }
}
