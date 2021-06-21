package cn.zju.leetcode;

public class code6_11 {
    public int minArray(int[] numbers) {
        int n = numbers.length;
        if (n == 1) {
            return numbers[0];
        }
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]){
                left = mid + 1;
            } else {
                right--;
            }
        }
        return numbers[left];
    }
}
