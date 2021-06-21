package cn.zju.leetcode;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class code1_826 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker){
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < difficulty.length; i++) {
            if(map.containsKey(difficulty[i])){
                map.put(difficulty[i],profit[i] > profit[map.get(difficulty[i])] ? i : map.get(difficulty[i]));
            }else{
                map.put(difficulty[i], i);
            }
        }
        Arrays.sort(difficulty);
        Arrays.sort(worker);
        int best = 0 , j = 0;
        for (int i = 0; i < worker.length; i++) {
            while(j < difficulty.length && worker[i] >= difficulty[j]){
                best = Math.max(best,profit[map.get(difficulty[j++])]);
            }
            ans += best;
        }
        return ans;
    }
    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker){
        int N = difficulty.length;
        Point[] jobs = new Point[N];
        for (int i = 0; i < N; i++) {
            jobs[i] = new Point(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> a.x - b.x);
        Arrays.sort(worker);
        int ans = 0, i = 0, best = 0;
        for (int skill : worker) {
            while(i < N && skill >= jobs[i].x){
                best = Math.max(best, jobs[i++].y);
            }
            ans += best;
        }
        return ans;
    }
}
