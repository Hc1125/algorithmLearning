package cn.zju.leetcode;


import java.util.Arrays;

public class code2_881 {

    public int numRescueBoats1(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int light = 0, hevay = people.length - 1;
        while (light <= hevay) {
            if (people[light] + people[hevay] <= limit) {
                light++;
            }
            hevay--;
            ans++;
        }
        return ans;
    }

    public int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        int lessR = -1;
        for (int i = people.length - 1; i >= 0; i--) {
            if (people[i] <= limit / 2) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return people.length;
        }
        int noUsed = 0;
        int L = lessR, R = lessR + 1;
        while (L >= 0) {
            int solved = 0;
            while (R < people.length && people[L] + people[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = people.length - all - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }
}
