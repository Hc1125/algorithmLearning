package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class code7_989 {
    public static List<Integer> addToArrayForm(int[] A, int K){
        int m = A.length;
        String s = K + "";
        int n = s.length();
        n = m > n ? m : n;
        int[] B = new int[n];
        int[] C = new int[n];
        int up = n - 1;
        int down = m - 1;
        while(up >= 0 && down >= 0){
            C[up--] = A[down--];
        }
        for (int i = 0; i < n; i++) {
            int b = K % 10;
            B[n - 1 - i] = b;
            K /= 10;
            if(K == 0){
                break;
            }
        }
        boolean isAdd = false;
        List<Integer> ans = new ArrayList<Integer>();
        for(int i = n - 1; i >= 0; i--){
            int num = 0;
            if(isAdd){
                num = C[i] + B[i] + 1;
                isAdd = false;
            }else {
                num = C[i] + B[i];
            }
            if(num >= 10){
                num = num % 10;
                isAdd = true;
            }
            ans.add(num);
        }
        if(isAdd){
            ans.add(1);
        }
        Collections.reverse(ans);
        return ans;
    }
    public List<Integer> addToArrayForm1(int[] A, int K){
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for(int i = n - 1; i >= 0; i--){
            int sum = A[i] + K % 10;
            K /= 10;
            if(sum >= 10){
                K++;
                sum -= 10;
            }
            res.add(sum);
        }
        for(; K > 0; K /= 10){
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }
    public List<Integer> addToArrayForm2(int[] A, int K){
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for(int i = n - 1; i >= 0 || K > 0; --i, K /= 10){
            if(i >= 0){
                K += A[i];
            }
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }
}
