package cn.zju.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class code1_786 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] * o1[1] - o1[0] * o2[1];
            }
        });
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.offer(new int[]{arr[i], arr[j]});
            }
            while(pq.size() > k){
                pq.poll();
            }
        }
        return pq.peek();
    }
    public int[] kthSmallestPrimeFraction1(int[] primes, int k) {
        double lo = 0, hi = 1;
        int[] ans = new int[]{0, 1};
        while (hi - lo > 1e-9) {
            double mi = lo + (hi - lo) / 2.0;
            int[] res = under(mi, primes);
            if (res[0] < k) {
                lo = mi;
            } else {
                ans[0] = res[1];
                ans[1] = res[2];
                hi = mi;
            }
        }
        return ans;
    }
    public int[] under(double x, int[] primes) {
        int numer = 0, denom = 1, count = 0, i = -1;
        for (int j = 1; j < primes.length; j++) {
            while (primes[i + 1] < primes[j] * x) ++i;
            count += i + 1;
            if (i >= 0 && numer * primes[j] < denom * primes[i]) {
                numer = primes[i];
                denom = primes[j];
            }
        }
        return new int[]{count, numer, denom};
    }
}
