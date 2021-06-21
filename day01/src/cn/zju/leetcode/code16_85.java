package cn.zju.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class code16_85 {
    public static void main(String[] args) {
        int[][] matrix = new int[3][2];
        System.out.println(matrix[0][1]);
    }
    private static int maximalRectangle1(char[][] matrix){
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == '1'){
                    left[i][j] = (j==0 ? 0 : left[i][j-1])+1; 
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == '0'){
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for(int k = i-1; k>=0; k--){
                    width = Math.min(width,left[k][j]);
                    area = Math.max(area,(i - k + 1) * width);
                }
                ret = Math.max(ret,area);
            }
        }
        return ret;
    }
    private static int maximalRectangle2(char[][] matrix){
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }
    private static int maximalRectangle3(char[][] matrix){
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length + 1]; //小技巧后边讲
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            Stack<Integer> stack = new Stack<Integer>();
            heights[matrix[0].length] = 0;
            //每求一个高度就进行栈的操作
            for (int col = 0; col <= matrix[0].length; col++) {
                if (col < matrix[0].length) { //多申请了 1 个元素，所以要判断
                    if (matrix[row][col] == '1') {
                        heights[col] += 1;
                    } else {
                        heights[col] = 0;
                    }
                }
                if (stack.isEmpty() || heights[col] >= heights[stack.peek()]) {
                    stack.push(col);
                } else {
                    //每次要判断新的栈顶是否高于当前元素
                    while (!stack.isEmpty() && heights[col] < heights[stack.peek()]) {
                        int height = heights[stack.pop()];
                        int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                        int RightLessMin = col;
                        int area = (RightLessMin - leftLessMin - 1) * height;
                        maxArea = Math.max(area, maxArea);
                    }
                    stack.push(col);
                }
            }

        }
        return maxArea;

    }
    private static int maximalRectangle4(char[][] matrix){
        if(matrix.length == 0){
            return 0;
        }
        int maxArea = 0;
        int cols = matrix[0].length;
        int[] leftLessMin = new int[cols];
        int[] rightLessMin = new int[cols];
        Arrays.fill(leftLessMin,-1);
        Arrays.fill(rightLessMin,cols);
        int[] heights = new int[cols];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < cols; col++) {
                if(matrix[row][col] == '1'){
                    heights[col] += 1;
                }else{
                    heights[col] = 0;
                }
            }
            int boundary = -1;
            for (int col = 0; col < cols; col++) {
                if(matrix[row][col] == '1'){
                    leftLessMin[col] = Math.max(leftLessMin[col],boundary);
                }else{
                    leftLessMin[col] = -1;
                    boundary = col;
                }
            }
            boundary = cols;
            for(int col = cols - 1; col >= 0; col--){
                if(matrix[row][col] == '1'){
                    rightLessMin[col] = Math.min(rightLessMin[col],boundary);
                }else{
                    rightLessMin[col] = cols;
                    boundary = col;
                }
            }
            for(int col = cols - 1; col >= 0; col--){
                int area = (rightLessMin[col] - leftLessMin[col] - 1) * heights[col];
                maxArea = Math.max(area,maxArea);
            }
        }
        return maxArea;
    }
}
