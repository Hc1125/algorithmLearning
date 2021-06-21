package cn.zju.leetcode;

public class code2_461 {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
