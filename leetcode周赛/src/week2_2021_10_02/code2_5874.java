package week2_2021_10_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个下标从 0 开始且长度为 n 的整数数组 nums 。分割 数组 nums 的方案数定义为符合以下两个条件的 pivot 数目：
 *
 * 1 <= pivot < n
 * nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]
 * 同时给你一个整数 k 。你可以将 nums 中 一个 元素变为 k 或 不改变 数组。
 *
 * 请你返回在 至多 改变一个元素的前提下，最多 有多少种方法 分割 nums 使得上述两个条件都满足。
 *
 * 示例 1：
 *
 * 输入：nums = [2,-1,2], k = 3
 * 输出：1
 * 解释：一个最优的方案是将 nums[0] 改为 k 。数组变为 [3,-1,2] 。
 * 有一种方法分割数组：
 * - pivot = 2 ，我们有分割 [3,-1 | 2]：3 + -1 == 2 。
 * 示例 2：
 *
 * 输入：nums = [0,0,0], k = 1
 * 输出：2
 * 解释：一个最优的方案是不改动数组。
 * 有两种方法分割数组：
 * - pivot = 1 ，我们有分割 [0 | 0,0]：0 == 0 + 0 。
 * - pivot = 2 ，我们有分割 [0,0 | 0]: 0 + 0 == 0 。
 * 示例 3：
 *
 * 输入：nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
 * 输出：4
 * 解释：一个最优的方案是将 nums[2] 改为 k 。数组变为 [22,4,-33,-20,-15,15,-16,7,19,-10,0,-13,-14] 。
 * 有四种方法分割数组。
 */
public class code2_5874 {
    // 在下标为i的元素变为k时，其对应的分割方法=（该元素左边的前缀和=target的数目）+（该元素右边的后缀和=target的数目）
    public int waysToPartition1(int[] nums, int k) {
        long sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        Map<Long, Integer> leftSumCnt = new HashMap<>();
        Map<Long, Integer> rightSumCnt = new HashMap<>();
        long rSum = 0;
        for (int i = n - 1; i >= 1; i--) {
            rSum += nums[i];
            rightSumCnt.put(rSum, rightSumCnt.getOrDefault(rSum, 0) + 1);
        }
        int ans = (sum % 2 == 0) ? rightSumCnt.getOrDefault(sum / 2, 0) : 0;
        long leftSum = 0;
        for (int i = 0; i < n; i++) {
            int d = k - nums[i];
            long newSum = sum + d;
            if (newSum % 2 == 0) {
                long target = newSum / 2;
                int cur = leftSumCnt.getOrDefault(target, 0) + rightSumCnt.getOrDefault(target, 0);
                ans = Math.max(ans, cur);
            }

            if (i < n - 1) {
                leftSum += nums[i];
                rightSumCnt.put(sum - leftSum, rightSumCnt.get(sum - leftSum) - 1);
                leftSumCnt.put(leftSum, leftSumCnt.getOrDefault(leftSum, 0) + 1);
            }
        }
        return ans;
    }


    public static int waysToPartition2(int[] nums, int k) {
        long sum = 0;
        Map<Long, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            sum += nums[i];
            if (!map.containsKey(sum)) {
                map.put(sum, new ArrayList<>());
            }
            map.get(sum).add(i);
        }
        sum += nums[n - 1];
        int ans = 0;
        if (sum % 2 == 0 && map.containsKey(sum / 2)) {
            ans = map.get(sum / 2).size();
        }
        for (int i = 0; i < n - 1; i++) {
            int res = 0;
            if ((sum + k - nums[i]) % 2 == 0  && k != nums[i]) {
                if (map.containsKey((sum + k - nums[i]) / 2 + nums[i] - k)) {
                    res += isVaild1(i, map.get((sum + k - nums[i]) / 2 + nums[i] - k));
                }
                if (map.containsKey((sum + k - nums[i]) / 2)) {
                    res += isVaild2(i, map.get((sum + k - nums[i]) / 2));
                }
                ans = Math.max(res, ans);
            }
        }
        if ((sum + k - nums[n - 1]) % 2 == 0  && k != nums[n - 1] && map.containsKey((sum + k - nums[n - 1]) / 2)) {
            int res = isVaild2(n - 1, map.get((sum + k - nums[n - 1]) / 2));
            ans = Math.max(ans, res);
        }
        return ans;
    }

    public static int isVaild1(int i, List<Integer> list) {
        if (list.size() == 1) {
            return list.get(0) >= i ? 1 : 0;
        }
        int l = 0, r = list.size() - 1;
        int mid = 0;
        int ans = -1;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (list.get(mid) < i) {
                l = mid + 1;
            } else {
                ans = mid;
                r = mid;
            }
        }
        return ans == -1 ? 0 : list.size() - ans;
    }
    public static int isVaild2(int i, List<Integer> list) {
        if (list.size() == 1) {
            return list.get(0) < i ? 1 : 0;
        }
        int l = 0, r = list.size() - 1;
        int mid = 0;
        int ans = -1;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (list.get(mid) < i) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (list.get(l) < i) ans++;
        return ans == -1 ? 0 : ans + 1;
    }
}
