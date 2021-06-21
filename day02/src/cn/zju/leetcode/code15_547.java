package cn.zju.leetcode;

import org.w3c.dom.xpath.XPathResult;

import java.util.*;

public class code15_547 {
    public static void main(String[] args) {

    }
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(isConnected[i][j]==1){
                    union(parent,i,j);
                }
            }
        }
        int circle = 0;
        for (int i = 0; i < n; i++) {
            if(parent[i] == i){
                circle++;
            }
        }
        return circle;
    }
    public void union(int[] parent, int index1, int index2){
        parent[find(parent,index1)] = find(parent,index2);
    }
    public int find(int[] parent, int index){
        if(parent[index] != index){
            parent[index] = find(parent,parent[index]);
        }
        return parent[index];
    }
    public int findCircleNum1(int[][] isConnected){
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int circle = 0;
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                dfs(isConnected,visited,n,i);
                circle++;
            }
        }
        return circle;
    }
    public void dfs(int[][] isConnected, boolean[] visited, int n, int i){
        for (int j = 0; j < n; j++) {
            if(isConnected[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(isConnected,visited,n,j);
            }
        }
    }
    public int findCircleNum2(int[][] isConnected){
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                queue.offer(i);
                while(!queue.isEmpty()){
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < n; k++) {
                        if(isConnected[j][k] == 1 && !visited[k]){
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }
    public int findCircleNum3(int[][] isConnected){
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int circles = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                stack.addLast(i);
                while(!stack.isEmpty()){
                    int j = stack.pollLast();
                    visited[j] = true;
                    for (int k = 0; k < n; k++) {
                        if(isConnected[j][k] == 1 && !visited[k]){
                            stack.addLast(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }
}
