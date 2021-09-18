package cn.zju.zuochengyun.AStarAlgorithm;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * map[i][j] == '.'，代表随意通行，不用代价
 * map[i][j] == '#'，代表是障碍，可以用a的代价穿过
 * map[i][j] == '*'，代表是传送门，可以传送到另外一个'*',代价为b，'*'的数量一定是0或2
 * 每一步上、下、左、右都能走，返回从左上角走到右下角最小代价是多少
 */
public class code2_WalkToEnd_II {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            char[][] matrix = new char[n][n];
            int turn = 0;
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = s.charAt(j);
                    if (matrix[i][j] == '*') {
                        turn += (i * n + j);
                    }
                }
            }
            PriorityQueue<Node> heap = new PriorityQueue<>((x, y) -> x.cost - y.cost);
            boolean[][] visited = new boolean[n][n];
            add(matrix, 0, 0, 0, heap, visited, n, a, b, turn);
            while (!heap.isEmpty()) {
                Node cur = heap.poll();
                if (cur.i == n - 1 && cur.j == n - 1) {
                    System.out.println(cur.cost);
                    break;
                }
                add(matrix, cur.i - 1, cur.j, cur.cost, heap, visited, n, a, b, turn);
                add(matrix, cur.i + 1, cur.j, cur.cost, heap, visited, n, a, b, turn);
                add(matrix, cur.i, cur.j - 1, cur.cost, heap, visited, n, a, b, turn);
                add(matrix, cur.i, cur.j + 1, cur.cost, heap, visited, n, a, b, turn);
                if (matrix[cur.i][cur.j] == '*') {
                    int other = turn - cur.i * n - cur.j;
                    if (!visited[other / n][other % n]) {
                        add(matrix, other / n, other % n, cur.cost, heap, visited, n, a, b, turn);
                    }
                }
            }
        }
    }

    public static void add(char[][] m, int i, int j, int pre, PriorityQueue<Node> heap, boolean[][] visited, int n, int a, int b, int turn) {
        if (i >= 0 && i < n && j >= 0 && j < n && !visited[i][j]) {
            if (m[i][j] == '.') {
                heap.add(new Node(pre, i, j));
            } else if (m[i][j] == '#') {
                heap.add(new Node(pre + a, i, j));
            } else {
                int other = turn - i * n - j;
                if (visited[other / n][other % n]) {
                    heap.add(new Node(pre + b, i, j));
                } else {
                    heap.add(new Node(pre, i, j));
                }
            }
            visited[i][j] = true;
        }
    }

    public static class Node {
        int cost;
        int i;
        int j;

        public Node(int cost, int i, int j) {
            this.cost = cost;
            this.i = i;
            this.j = j;
        }
    }
}
