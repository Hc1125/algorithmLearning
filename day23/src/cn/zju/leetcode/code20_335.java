package cn.zju.leetcode;

public class code20_335 {
    public static boolean isSelfCrossing(int[] distance) {
        if (distance == null || distance.length < 4) {
            return false;
        }
        if ((distance[2] <= distance[0] && distance[3] >= distance[1]) ||
                (distance.length > 4 && ((distance[3] <= distance[1] && distance[4] >= distance[2]) ||
                        (distance[3] == distance[1] && distance[0] + distance[4] == distance[2])))) {
            return true;
        }
        for (int i = 5; i < distance.length; i++) {
            if (distance[i - 1] <= distance[i - 3] &&
                    ((distance[i] >= distance[i - 2]) ||
                            (distance[i - 2] >= distance[i - 4] &&
                                    distance[i - 5] + distance[i - 1] >= distance[i - 3] &&
                                    distance[i - 4] + distance[i] >= distance[i - 2]))) {
                return true;
            }
        }
        return false;
    }
}
