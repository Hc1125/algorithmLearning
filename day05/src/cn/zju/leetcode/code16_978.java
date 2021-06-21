package cn.zju.leetcode;

public class code16_978 {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int left = 0, right = 0;
        int ret = 0;
        while(right < n){
            if(left == right) {
                if(arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if(arr[right - 1] > arr[right] && arr[right] < arr[right + 1]){
                    right++;
                } else if(arr[right - 1] < arr[right] && arr[right] > arr[right + 1]){
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }
}
