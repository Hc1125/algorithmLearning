package cn.zju.group5;

public class code12_283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        int to = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, to++, i);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
