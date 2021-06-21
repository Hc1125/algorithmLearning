package cn.zju.leetcode;

import java.util.*;

public class code19_721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts){
        Map<String,Integer> emailToIndex = new HashMap<>();
        Map<String,String> emailToName = new HashMap<>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email,emailsCount++);
                    emailToName.put(email,name);
                }
            }
        }
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.union(firstIndex,nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index,new ArrayList<String>());
            account.add(email);
            indexToEmails.put(index,account);
        }
        List<List<String>> merged = new ArrayList<List<String>>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }
    class UnionFind{
        int[] parent;
        public UnionFind(int n){
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public void union(int index1, int index2){
            parent[find(index2)] = find(index1);
        }
        public int find(int index){
            return parent[index] == index ? index : (parent[index] = find(parent[index]));
        }
    }
    public List<List<String>> accountsMerge1(List<List<String>> accounts){
        Map<String,List<String>> graph = new HashMap<>();
        for (List<String> account : accounts) {
            String master = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                String email = account.get(i);
                List<String> list1 = graph.getOrDefault(master,new LinkedList<String>());
                list1.add(email);
                graph.put(master,list1);
                List<String> list2 = graph.getOrDefault(email,new LinkedList<String>());
                list2.add(master);
                graph.put(email,list2);
            }
        }
        List<List<String>> ans = new LinkedList<List<String>>();
        Set<String> visited = new HashSet<>();
        for (List<String> account : accounts) {
            List<String> emails = new LinkedList<String>();
            dfs(graph, visited, account.get(1),emails);
            if(emails.isEmpty()){
                continue;
            }
            Collections.sort(emails);
            emails.add(0,account.get(0));
            ans.add(emails);
        }
        return ans;
    }
    public void dfs(Map<String,List<String>> graph, Set<String> visited, String email, List<String> emails){
        if(visited.contains(email)){
            return;
        }
        visited.add(email);
        emails.add(email);
        for (String neighbor : graph.get(email)) {
            dfs(graph,visited,neighbor,emails);
        }
    }

}
