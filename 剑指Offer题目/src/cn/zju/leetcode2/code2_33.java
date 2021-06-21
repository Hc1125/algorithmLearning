package cn.zju.leetcode2;

import java.util.ArrayDeque;
import java.util.Deque;

public class code2_33 {
    public boolean verifyPostorder(int[] postorder) {
        return process(postorder, 0, postorder.length - 1);
    }
    public boolean process(int[] postorder, int begin, int end) {
        if (begin >= end) {
            return true;
        }
        int i = begin;
        while (i < end && postorder[i] < postorder[end]) {
            i++;
        }
        int j = i;
        while (j < end && postorder[j] > postorder[end]) {
            j++;
        }
        return j == end && process(postorder, begin, i - 1) && process(postorder, i, end - 1);
    }
    public boolean verifyPostorder1(int[] postorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.push(postorder[i]);
        }
        return true;
    }
}
