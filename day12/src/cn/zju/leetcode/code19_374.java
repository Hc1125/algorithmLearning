package cn.zju.leetcode;

public class code19_374 {
    public int guess(int num) {
        return 1;
    }
    public int guessNumber(int n) {
        int left = 1, right = n;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
