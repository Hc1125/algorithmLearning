package cn.zju.leetcode;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code8_207 {
    List<List<Integer>> edges;//用于存储学习每门课程先要学习的储备知识课程
    int[] indeg;              //记录每个课程的深度（对应有向图中的箭头数）

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                indeg[v]--;
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return visited == numCourses;
    }
    int[] visited;
    boolean valid = true;
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if(visited[i] == 0){
                dfs(i);
            }
        }
        return valid;

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
    }
}
