package cn.zju.zuochengyun.DirectedGraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class code2_BFS {
    public static void bfs(Node node) {
        if(node ==null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
