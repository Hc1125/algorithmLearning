package cn.zju.leetcode;


import java.util.*;

public class code7_1203 {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 第 1 步：数据预处理，给没有归属于一个组的项目编上组号
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m;
                m++;
            }
        }

        // 第 2 步：实例化组和项目的邻接表
        List<Integer>[] groupAdj = new ArrayList[m];
        List<Integer>[] itemAdj = new ArrayList[n];
        for (int i = 0; i < m; i++) {
            groupAdj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            itemAdj[i] = new ArrayList<>();
        }

        // 第 3 步：建图和统计入度数组
        int[] groupsIndegree = new int[m];
        int[] itemsIndegree = new int[n];

        int len = group.length;
        for (int i = 0; i < len; i++) {
            int currentGroup = group[i];
            for (int beforeItem : beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                if (beforeGroup != currentGroup) {
                    groupAdj[beforeGroup].add(currentGroup);
                    groupsIndegree[currentGroup]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Integer item : beforeItems.get(i)) {
                itemAdj[item].add(i);
                itemsIndegree[i]++;
            }
        }

        // 第 4 步：得到组和项目的拓扑排序结果
        List<Integer> groupsList = topologicalSort(groupAdj, groupsIndegree, m);
        if (groupsList.size() == 0) {
            return new int[0];
        }
        List<Integer> itemsList = topologicalSort(itemAdj, itemsIndegree, n);
        if (itemsList.size() == 0) {
            return new int[0];
        }

        // 第 5 步：根据项目的拓扑排序结果，项目到组的多对一关系，建立组到项目的一对多关系
        // key：组，value：在同一组的项目列表
        Map<Integer, List<Integer>> groups2Items = new HashMap<>();
        for (Integer item : itemsList) {
            groups2Items.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
        }

        // 第 6 步：把组的拓扑排序结果替换成为项目的拓扑排序结果
        List<Integer> res = new ArrayList<>();
        for (Integer groupId : groupsList) {
            List<Integer> items = groups2Items.getOrDefault(groupId, new ArrayList<>());
            res.addAll(items);
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private List<Integer> topologicalSort(List<Integer>[] adj, int[] inDegree, int n) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer front = queue.poll();
            res.add(front);
            for (int successor : adj[front]) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.offer(successor);
                }
            }
        }

        if (res.size() == n) {
            return res;
        }
        return new ArrayList<>();
    }
    public int[] sortItems1(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<List<Integer>> groupItem = new ArrayList<List<Integer>>();
        for (int i = 0; i < n + m; ++i) {
            groupItem.add(new ArrayList<Integer>());
        }

        // 组间和组内依赖图
        List<List<Integer>> groupGraph = new ArrayList<List<Integer>>();
        for (int i = 0; i < n + m; ++i) {
            groupGraph.add(new ArrayList<Integer>());
        }
        List<List<Integer>> itemGraph = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i) {
            itemGraph.add(new ArrayList<Integer>());
        }

        // 组间和组内入度数组
        int[] groupDegree = new int[n + m];
        int[] itemDegree = new int[n];

        List<Integer> id = new ArrayList<Integer>();
        for (int i = 0; i < n + m; ++i) {
            id.add(i);
        }

        int leftId = m;
        // 给未分配的 item 分配一个 groupId
        for (int i = 0; i < n; ++i) {
            if (group[i] == -1) {
                group[i] = leftId;
                leftId += 1;
            }
            groupItem.get(group[i]).add(i);
        }
        // 依赖关系建图
        for (int i = 0; i < n; ++i) {
            int curGroupId = group[i];
            for (int item : beforeItems.get(i)) {
                int beforeGroupId = group[item];
                if (beforeGroupId == curGroupId) {
                    itemDegree[i] += 1;
                    itemGraph.get(item).add(i);
                } else {
                    groupDegree[curGroupId] += 1;
                    groupGraph.get(beforeGroupId).add(curGroupId);
                }
            }
        }

        // 组间拓扑关系排序
        List<Integer> groupTopSort = topSort(groupDegree, groupGraph, id);
        if (groupTopSort.size() == 0) {
            return new int[0];
        }
        int[] ans = new int[n];
        int index = 0;
        // 组内拓扑关系排序
        for (int curGroupId : groupTopSort) {
            int size = groupItem.get(curGroupId).size();
            if (size == 0) {
                continue;
            }
            List<Integer> res = topSort(itemDegree, itemGraph, groupItem.get(curGroupId));
            if (res.size() == 0) {
                return new int[0];
            }
            for (int item : res) {
                ans[index++] = item;
            }
        }
        return ans;
    }

    public List<Integer> topSort(int[] deg, List<List<Integer>> graph, List<Integer> items) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int item : items) {
            if (deg[item] == 0) {
                queue.offer(item);
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for (int v : graph.get(u)) {
                if (--deg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return res.size() == items.size() ? res : new ArrayList<Integer>();
    }
    public int[] sortItems2(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int pos = m;
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = pos++;
            }
        }

        ArrayList<Integer>[] groupMember = new ArrayList[pos];
        ArrayList<Integer>[] groupBefore = new ArrayList[pos];
        ArrayList<Integer>[] taskBefore = new ArrayList[n];

        for (int i = 0; i < pos; i++) {
            groupMember[i] = new ArrayList<>();
            groupBefore[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            taskBefore[i] = new ArrayList<>();
        }

        for (int i = 0; i < group.length; i++) {
            groupMember[group[i]].add(i);
        }

        int[] groupDegree = new int[pos];
        int[] taskDegree = new int[n];

        int cur = 0;
        for (List<Integer> list : beforeItems) {
            for (int x : list) {
                taskBefore[x].add(cur);
                taskDegree[cur]++;

                if (group[x] != group[cur]) {
                    groupBefore[group[x]].add(group[cur]);
                    groupDegree[group[cur]]++;
                }
            }
            cur++;
        }

        int[] ans = new int[n];
        cur = 0;

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < groupDegree.length; i++) {
            if (groupDegree[i] == 0) {
                deque.addLast(i);
            }
        }

        while (!deque.isEmpty()) {
            int groupIndex = deque.pollFirst();
            ArrayList<Integer> groupItem = groupMember[groupIndex];
            int expEnd = cur + groupItem.size();

            int s = cur;
            for (int x : groupItem) {
                if (taskDegree[x] == 0) {
                    ans[cur++] = x;
                }
            }

            while (s < cur) {
                ArrayList<Integer> childTask = taskBefore[ans[s]];
                for (int c : childTask) {
                    taskDegree[c]--;
                    if (taskDegree[c] == 0 && group[c] == groupIndex) {
                        ans[cur++] = c;
                    }
                }
                s++;
            }
            if (cur != expEnd) {
                break;
            }

            ArrayList<Integer> groupChild = groupBefore[groupIndex];
            for (int g : groupChild) {
                groupDegree[g]--;
                if (groupDegree[g] == 0) {
                    deque.offerLast(g);
                }
            }
        }

        if (cur < n) {
            return new int[0];
        } else {
            return ans;
        }

    }
}
