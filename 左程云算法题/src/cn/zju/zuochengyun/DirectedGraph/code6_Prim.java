package cn.zju.zuochengyun.DirectedGraph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class code6_Prim {
    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        // 哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();
        // 已经考虑过的边不要重复考虑
        HashSet<Edge> edgeSet = new HashSet<>();
        // 依次挑选的边在result里
        Set<Edge> result = new HashSet<>();
        // 随便挑一个点
        for (Node node : graph.nodes.values()) {
            if(!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) { // 由一个点，解锁所有相连的边
                    if(!edgeSet.contains(edge)){ // 不含有的时候，就是新的点
                        edgeSet.add(edge);
                        priorityQueue.add(edge);
                    }
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
                    Node toNode = edge.to;
                    if(!nodeSet.contains(toNode)) {
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            if(!edgeSet.contains(nextEdge)){ // 不含有的时候，就是新的点
                                edgeSet.add(nextEdge);
                                priorityQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
            //  break;
        }
        return result;
    }
}
