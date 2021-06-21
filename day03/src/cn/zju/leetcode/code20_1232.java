package cn.zju.leetcode;

public class code20_1232 {
    public boolean checkStraightLine(int[][] coordinates){
        int n = coordinates.length;
        for (int i = 1; i < n; i++) {
            if((coordinates[i][1] - coordinates[0][1]) * (coordinates[i][0] - coordinates[n-1][0])!=
                    (coordinates[i][0] - coordinates[0][0]) * (coordinates[i][1] - coordinates[n-1][1])){
                return false;
            }
        }
        return true;
    }
}
