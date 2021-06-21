package cn.zju.zuochengyun.DirectedGraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class code3_DFS {
    public static void dfs(Node node) {
        if(node == null) {
            return;
        }
        Deque<Node> stack = new ArrayDeque<>();
        HashSet<Node> set = new HashSet<>();
        stack.addLast(node);
        set.add(node);
        System.out.println(node.value);
        while(!stack.isEmpty()) {
            Node cur = stack.pollLast();
            for (Node next : cur.nexts) {
                if(!set.contains(next)) {
                    stack.addLast(cur);
                    stack.addLast(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
