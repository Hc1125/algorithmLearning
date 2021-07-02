package cn.zju.leetcode;

public class code2_1401 {
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        double x0 = (x1 + x2) / 2.0;
        double y0 = (y1 + y2) / 2.0;
        // 将圆挪到相对于矩形的第一象限
        // 构成三角形三点：圆心，矩形中心，矩形右上角点
        // 圆心到矩形中心距离为 p 挪到第一象限的
        // 矩形中心到矩形右上角点距离为 q
        // 圆心到矩形右上角点距离为 u
        // u 小于等于 r 则说明圆与矩形相交
        double[] p = new double[]{Math.abs(x_center - x0), Math.abs(y_center - y0)};
        double[] q = new double[]{x2 - x0, y2 - y0};
        double[] u = new double[]{Math.max(p[0] - q[0], 0.0), Math.max(p[1] - q[1], 0.0)};
        return Math.sqrt(u[0] * u[0] + u[1] * u[1]) <= radius;
    }
}
