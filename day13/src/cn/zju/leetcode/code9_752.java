package cn.zju.leetcode;

import javax.management.remote.NotificationResult;
import java.util.*;

public class code9_752 {
    public int openLock1(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String nextStatus : get(status)) {
                    if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    public List<String> get(String status) {
        List<String> list = new ArrayList<>();
        char[] chars = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char num = chars[i];
            chars[i] = numPrev(num);
            list.add(new String(chars));
            chars[i] = numSucc(num);
            list.add(new String(chars));
            chars[i] = num;
        }
        return list;
    }
    String t, s;
    Set<String> set = new HashSet<>();
    public int openLock2(String[] deadends, String target) {
        s = "0000";
        t = target;
        if (s.equals(t)) return 0;
        for (String d : deadends) {
            set.add(d);
        }
        if (set.contains(s)) return -1;
        int ans = bfs();
        return ans;
    }

    public int bfs() {
        // d1 代表从起点 s 开始搜索（正向）
        // d2 代表从结尾 t 开始搜索（反向）

        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        /*
         * m1 和 m2 分别记录两个方向出现的状态是经过多少次转换而来
         * e.g.
         * m1 = {"1000":1} 代表 "1000" 由 s="0000" 旋转 1 次而来
         * m2 = {"9999":3} 代表 "9999" 由 t="9996" 旋转 3 次而来
         */
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        d1.addLast(s);
        m1.put(s, 0);
        d2.addLast(t);
        m2.put(t, 0);

        /*
         * 只有两个队列都不空，才有必要继续往下搜索
         * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
         * e.g.
         * 例如，如果 d1 为空了，说明从 s 搜索到底都搜索不到 t，反向搜索也没必要进行了
         */

        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            if (d1.size() <= d2.size()) {
                t = update(d1, m1, m2);
            } else {
                t = update(d2, m2, m1);
            }
            if (t != -1) return t;
        }
        return -1;
    }

    public int update(Deque<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
        String poll = deque.pollFirst();
        char[] pcs = poll.toCharArray();
        int step = cur.get(poll);
        for (int i = 0; i < 4; i++) {
            for (int j = -1; j <= 1; j++) {
                if (j == 0) continue;
                int origin = pcs[i] - '0';
                int next = (origin + j) % 10;
                if (next == -1) next = 9;

                char[] clone = pcs.clone();
                clone[i] = (char) (next + '0');
                String str = String.valueOf(clone);
                if (set.contains(str)) continue;
                if (cur.containsKey(str)) continue;
                if (other.containsKey(str)) {
                    return step + 1 + other.get(str);
                } else {
                    deque.addLast(str);
                    cur.put(str, step + 1);
                }
            }
        }
        return -1;
    }
}
