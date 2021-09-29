package cn.zju.zuochengyun.AStarAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * X星卫士接到了一个紧急任务。大BOSS被困Y星的地狱迷宫，X星卫士必须携带“时空转移丸”尽快前往营救。
 * Y星的地狱迷宫是一个有M*N个小房间构成的矩形结构，迷宫中的小房间一共有M行，每一行有N列。
 * 迷宫的入口为第1行第1列，标记为“S”，大BOSS被困的位置标记为“B”。
 * X星卫士通过每个普通小房间的时间均为1分钟。但是在这个迷宫中，有一些房间中设置了机关，一共包含三类不同的机关，分别是：死亡陷阱、延时陷阱和定向陷阱。
 * 死亡陷阱是不能进入的房间，一旦进入将万劫不复，营救失败；
 * 延时陷阱中布满死亡光线，需要3分钟时间才能够顺利通过；
 * 定向陷阱中设有一个定向诱导装置，进入该房间者将丧失转向功能（只在该房间中丧失），只能沿着直线行进，例如从左边进入只能从右边出来，从上面进入只能从下面出来，不过通过时间还是1分钟。
 *
 * 对于一个给定的地狱迷宫地图，请问最少需要多少时间（分钟）才能够营救到大BOSS（一旦进入大BOSS所在房间即营救成功）？
 *
 *
 *
 * 输入描述
 * 单组输入。
 *
 * 第1行输入两个正整数M和N，分别表示迷宫矩阵的行和列。（M<=10^3，N<=10^3）
 *
 * 第2行到第M+1行，每行N列，对应一个二维字符矩阵，用于表示地狱迷宫。
 *
 * 在字符矩阵中，“S”表示迷宫入口，“B”表示大BOSS被困的位置，“0”表示可以正常通过的房间（通过时间为1分钟），“#”表示死亡陷阱，“*”表示延时陷阱，“@”表示定向陷阱。
 *
 * 输出描述
 * X星卫士营救到大BOSS所需最少时间（单位：分钟）；如果不能营救成功，则输出“Failure"。
 */
public class code4_RescueBoss {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            char[][] matrix = new char[m][n];
            for (int i = 0; i < m; i++) {
                String s = sc.next();
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = s.charAt(j);
                }
            }
            if (m == 0 || n == 0 || matrix[0][0] == '#') {
                System.out.println("Failure");
                break;
            }
            if (matrix[0][0] == 'B') {
                System.out.println(0);
                break;
            }
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;
            pq.offer(new Node(0, 0, 0, 0, 0));
            int ans = -1;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (matrix[cur.row][cur.col] == 'B') {
                    ans = cur.cost - 1;
                    break;
                }
                if (matrix[cur.row][cur.col] == '@') {
                    add(matrix, cur.row, cur.col, cur.cost, cur.dirX, cur.dirY, pq, visited);
                } else {
                    add(matrix, cur.row, cur.col, cur.cost, -1, 0, pq, visited);
                    add(matrix, cur.row, cur.col, cur.cost, 1, 0, pq, visited);
                    add(matrix, cur.row, cur.col, cur.cost, 0, -1, pq, visited);
                    add(matrix, cur.row, cur.col, cur.cost, 0, 1, pq, visited);
                }
            }
            System.out.println(ans == -1 ? "Failure" : ans);
        }
    }

    public static void add(char[][] m, int i, int j, int pre, int dirX, int dirY, PriorityQueue<Node> pq, boolean[][] visited) {
        int x = i + dirX, y = j + dirY;
        if (x >= 0 && x < m.length && y >= 0 && y < m[0].length && m[x][y] != '#' && !visited[x][y]) {
            pq.add(new Node(x, y, pre + (m[x][y] == '*' ? 3 : 1), dirX, dirY));
            visited[x][y] = true;
        }
    }

    public static class Node {
        public int row;
        public int col;
        public int cost;
        public int dirX;
        public int dirY;

        public Node(int row, int col, int cost, int dirX, int dirY) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }
}
