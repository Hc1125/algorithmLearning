package cn.zju.group5;

/**
 * 假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。
 *
 * 现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。
 *
 * 在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。
 *
 * 派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。
 */
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
            // 验证所有人是否都认识cand
            if (!knows(i, cand)) {
                return -1;
            }
        }
        return cand;
    }
}
