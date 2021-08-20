package cn.zju.group5;

import java.util.ArrayList;
import java.util.List;

public class code3_210_CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            int[] ans = new int[numCourses];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = i;
            }
            return ans;
        }
        List<List<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            nexts.add(new ArrayList<>());
        }
        int[] in = new int[numCourses];
        for (int[] arr : prerequisites) {
            nexts.get(arr[1]).add(arr[0]);
            in[arr[0]]++;
        }
        // 队列
        int[] zero = new int[numCourses];
        // 该队列有效范围是[l,r)
        // 新来的数，放哪？r位置，r++
        // 出队列的数，从哪拿？l位置，l++
        // l == r队列无元素 l < r 队列有元素
        int l = 0;
        int r = 0;
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                zero[r++] = i;
            }
        }
        int count = 0;
        while (l != r) {
            count++;
            for (int next : nexts.get(zero[l++])) {
                if (--in[next] == 0) {
                    zero[r++] = next;
                }
            }
        }
        return count == numCourses ? zero : new int[]{};
    }
}
