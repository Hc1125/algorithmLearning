package cn.zju.group7;

import java.util.ArrayList;
import java.util.TreeMap;

public class code3_673_NumberOfLongestIncreasingSubsequence {

    // 优化后的最优解，时间复杂度O(N*logN)
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i]代表长度为i的所有结尾以及对应个数
        // treeMap中key为结尾元素，而val代表大于等于key的结尾对应的总个数
        ArrayList<TreeMap<Integer, Integer>> dp = new ArrayList<>();
        int len = 0;
        int cnt = 0;
        for (int num : nums) {
            // num之前的长度，num到哪个长度len+1
            len = search(dp, num);
            // cnt : 最终要去加底下的记录，才是应该填入的value
            if (len == 0) {
                cnt = 1;
            } else {
                TreeMap<Integer, Integer> p = dp.get(len - 1);
                cnt = p.firstEntry().getValue() - (p.ceilingKey(num) != null ? p.get(p.ceilingKey(num)) : 0);
            }
            if (len == dp.size()) {
                dp.add(new TreeMap<>());
                dp.get(len).put(num, cnt);
            } else {
                dp.get(len).put(num, dp.get(len).firstEntry().getValue() + cnt);
            }
        }
        return dp.get(dp.size() - 1).firstEntry().getValue();
    }


    // 二分查找，返回>=num最左的位置
    public int search(ArrayList<TreeMap<Integer, Integer>> dp, int num) {
        int l = 0, r = dp.size() - 1, m = 0;
        int ans = dp.size();
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (dp.get(m).firstKey() >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
