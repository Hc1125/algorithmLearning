package cn.zju.leetcode;

public class code11_299 {
    public String getHint(String secret, String guess) {
        int countA = 0;
        int[] cntS = new int[10];
        int[] cntG = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++countA;
            } else {
                ++cntS[secret.charAt(i) - '0'];
                ++cntG[guess.charAt(i) - '0'];
            }
        }
        int countB = 0;
        for (int i = 0; i < 10; ++i) {
            countB += Math.min(cntS[i], cntG[i]);
        }
        return new StringBuilder().append(countA).append('A').append(countB).append('B').toString();
    }
}
