package cn.zju.zuochengyun.Practice4;

import java.util.Arrays;

public class code18_CopyBooks {
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int start = Arrays.stream(pages).max().getAsInt();
        int end = Arrays.stream(pages).sum();
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (numberOfWorkers(pages, mid) <= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private int numberOfWorkers(int[] pages, int t) {
        int count = 0;
        int sum = 0;
        for (int page : pages) {
            if (sum + page > t) {
                sum = page;
                count++;
            } else {
                sum += page;
            }
        }
        count++;
        return count;
    }
}
