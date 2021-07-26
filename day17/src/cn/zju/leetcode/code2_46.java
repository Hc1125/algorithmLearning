package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code2_46 {
    static List<List<Integer>> ans = new ArrayList<>();
    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        boolean[] isUsed = new boolean[nums.length];
        process(nums, isUsed, list, 0);
        return ans;
    }
    public static void process(int[] nums, boolean[] isUsed, List<Integer> list, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!isUsed[i]) {
                list.add(nums[i]);
                isUsed[i] = true;
                process(nums, isUsed, list, index + 1);
                list.remove(list.size() - 1);
                isUsed[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] num = new int[]{1,2,3};
        List<List<Integer>> lists = permute(num);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
