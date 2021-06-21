package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code9_210 {
    public int[] findOrder(int numCourses, int[][] prerequisites){
        int[] indeg = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < indeg.length; i++) {
            if(indeg[i] == 0){
                queue.offer(i);
            }
        }
        int[] result = new int[numCourses];
        int i = 0;
        while(!queue.isEmpty()){
            int u = queue.poll();
            result[i++] = u;
            for (int v : edges.get(u)) {
                indeg[v]--;
                if(indeg[v] == 0){
                    queue.offer(v);
                }
            }
        }
        return i == numCourses ? result : new int[0];
    }
    List<List<Integer>> edges;
    int[] visited;
    int[] result;
    boolean valid = true;
    int index;
    public int[] findOrder1(int numCourses, int[][] prerequisites){
        index = numCourses - 1;
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(visited[i] == 0){
                dfs(i);
            }
        }
        if(!valid){
            return new int[0];
        }
        return result;
    }
    public void dfs(int u){
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if(visited[v] == 0){
                dfs(v);
                if(!valid){
                    return;
                }
            }else if(visited[v] == 1){
                valid = false;
                return;
            }
        }
        visited[u] = 2;
        result[index--] = u;
    }
}
