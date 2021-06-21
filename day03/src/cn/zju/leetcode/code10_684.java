package cn.zju.leetcode;

public class code10_684 {
    public int[] findRedundantConnection(int[][] edges){
        int N = edges.length;
        int[] parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < N; i++) {
            int[] edge = edges[i];
            int index1 = edge[0];
            int index2 = edge[1];
            if(find(parent,index1) != find(parent,index2)){
                union(parent,index1,index2);
            }else {
                return edge;
            }
        }
        return new int[0];
    }
    public void union(int[] parent, int index1, int index2){
        parent[find(parent,index1)] = find(parent,index2);
    }
    public int find(int[] parent, int index){
        return parent[index] == index ? index : (parent[index] = find(parent,parent[index]));
    }
}
