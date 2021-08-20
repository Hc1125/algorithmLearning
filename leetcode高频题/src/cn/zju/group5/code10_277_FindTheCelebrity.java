package cn.zju.group5;

public class code10_277_FindTheCelebrity {
    // knows方法，默认自己认识自己
    public boolean knows(int x, int j) {
        return true;
    }

    public int findCelebrity(int n) {
        int cand = 0;
        for (int i = 1; i < n; i++) {
            if (knows(cand, i)) {
                cand = i;
            }
        }
        // cand 是唯一可能是明星的人
        // 下一步验证，cand到底是不是明星
        // 只用验证cand左侧，右侧已经保证cand都不认识
        for (int i = 0; i < cand; i++) {
            if (knows(cand, i)) {
                return -1;
            }
        }
        // 验证所有人是否都认识cand
        for (int i = 0; i < n; i++) {
            if (!knows(i, cand)) {
                return -1;
            }
        }
        return cand;
    }
}
