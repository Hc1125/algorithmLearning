package week9_2021_11_13;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个在 XY 平面上的 width x height 的网格图，左下角 的格子为 (0, 0) ，右上角 的格子为 (width - 1, height - 1) 。网格图中相邻格子为四个基本方向之一（"North"，"East"，"South" 和 "West"）。一个机器人 初始 在格子 (0, 0) ，方向为 "East" 。
 *
 * 机器人可以根据指令移动指定的 步数 。每一步，它可以执行以下操作。
 *
 * 沿着当前方向尝试 往前一步 。
 * 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步。
 * 如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。
 *
 * 请你实现 Robot 类：
 *
 * Robot(int width, int height) 初始化一个 width x height 的网格图，机器人初始在 (0, 0) ，方向朝 "East" 。
 * void move(int num) 给机器人下达前进 num 步的指令。
 * int[] getPos() 返回机器人当前所处的格子位置，用一个长度为 2 的数组 [x, y] 表示。
 * String getDir() 返回当前机器人的朝向，为 "North" ，"East" ，"South" 或者 "West" 。
 */
public class code1_5911 {
    class Robot {
        int w;
        int h;
        int step;
        public Robot(int width, int height) {
            w = width;
            h = height;
            step = 0;
        }

        public void move(int num) {
            // 由于机器人只能走外圈，那么走 (w+h-2)*2 步后会回到起点
            // 同时，将 step 取模固定在 [1,(w+h-2)*2] 范围内，这样不需要特判处于原点时的方向
            step = (step + num - 1) % ((w + h - 2) << 1) + 1;
        }

        public int[] getPos() {
            if (step < w) {
                return new int[]{step, 0};
            } else if (step < w + h - 1) {
                return new int[]{w - 1, step - w + 1};
            } else if (step < w * 2 + h - 2) {
                return new int[]{w * 2 + h - 3 - step, h - 1};
            } else {
                return new int[]{0, (w + h - 2) * 2 - step};
            }
        }

        public String getDir() {
            if (step < w) {
                return "East";
            } else if (step < w + h - 1) {
                return "North";
            } else if (step < w * 2 + h - 2) {
                return "West";
            } else {
                return "South";
            }
        }
    }

    class Robot1 {
        int m;
        int n;
        int curX;
        int curY;
        int dir;
        int limit;
        int circle;
        int[][] turn = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Map<Integer, String> map;
        public Robot1(int width, int height) {
            m = width;
            n = height;
            dir = 0;
            map = new HashMap<>();
            map.put(0, "East");
            map.put(1, "North");
            map.put(2, "West");
            map.put(3, "South");
            circle = (m + n - 2) << 1;
            limit = Math.max(m, n) + circle;
        }

        public void move(int num) {
            if (num > limit) {
                num %= circle;
                num += circle;
            }
            while (num > 0) {
                if (dir == 0) {
                    int step = m - 1 - curX;
                    if (step > num) {
                        curX += num;
                        break;
                    } else {
                        curX = m - 1;
                        dir = 1;
                        num -= step;
                    }
                } else if (dir == 1) {
                    int step = n - 1 - curY;
                    if (step > num) {
                        curY += num;
                        break;
                    } else {
                        curY = n - 1;
                        dir = 2;
                        num -= step;
                    }
                } else if (dir == 2) {
                    int step = curX;
                    if (step > num) {
                        curX -= num;
                        break;
                    } else {
                        curX = 0;
                        dir = 3;
                        num -= step;
                    }
                } else {
                    int step = curY;
                    if (step > num) {
                        curY -= num;
                        break;
                    } else {
                        curY = 0;
                        dir = 0;
                        num -= step;
                    }
                }
                if (num == 0) {
                    dir = (dir + 3) % 4;
                }
            }
        }

        public int[] getPos() {
            return new int[]{curX, curY};
        }

        public String getDir() {
            return map.get(dir);
        }
    }
}
