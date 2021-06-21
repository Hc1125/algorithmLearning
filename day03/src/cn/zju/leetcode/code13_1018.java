package cn.zju.leetcode;


import java.util.LinkedList;
import java.util.List;

public class code13_1018 {
    public List<Boolean> prefixesDivby5(int[] A){
        int num = 0;
        int n = A.length;
        List<Boolean> ans = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            num = (num * 2 + A[i]) % 5;
            ans.add(num==5);
        }
        return ans;
    }
}
