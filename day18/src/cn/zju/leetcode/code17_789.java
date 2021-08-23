package cn.zju.leetcode;

public class code17_789 {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int[] source = {0, 0};
        int distance = getDistance(source, target);
        for (int[] ghost : ghosts) {
            int gDistance = getDistance(ghost, target);
            if (gDistance <= distance) {
                return false;
            }
        }
        return true;
    }

    public int getDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}
