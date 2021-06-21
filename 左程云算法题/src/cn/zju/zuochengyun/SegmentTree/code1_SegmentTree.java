package cn.zju.zuochengyun.SegmentTree;

public class code1_SegmentTree {
    public static class SegmentTree{
        // arr[]为原序列的信息从0开始，但在arr里是从1开始的
        // sum[]模拟线段树维护区间和
        // lazy[]为累加懒惰标记
        // change[]为更新的值
        // update[]为更新慵懒标记
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;
        public SegmentTree(int[] origin){
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2];  //用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[MAXN << 2]; //用来支持脑补概念中，某一个范围没有往下传达的累加值
            change = new int[MAXN << 2]; //用来支持脑补概念中，某一个范围有没有更新操作的值
            update = new boolean[MAXN << 2]; //用来支持脑补概念中，某一个范围更新任务，
        }
        private void pushUp(int rt){
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }
        // 之前所有的懒增加和懒更新，从父范围，发给左右两个子范围
        // 分发策略是什么
        // ln表示左子树结点个数， rn表示右子树结点个数
        private void pushDown(int rt, int ln, int rn){
            if(update[rt]){
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
            if(lazy[rt] != 0){
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }
        /**
         * 在初始化阶段，先把sum数组，填好
         * 在arr[1~r]范围上，去build，1~N
         * rt ：这个范围在sum中的下标
         */
        public void build(int l, int r, int rt){
            if(l == r){
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }
        public void update(int L, int R, int C, int l, int r, int rt){
            if(L <= l && r <= R){
                update[rt] = true;
                change[rt] = C;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            //当前任务躲不住， 无法懒更新，要往下发
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if(L <= mid){
                update(L, R, C, l, mid, rt << 1);
            }
            if(R > mid){
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        /**
         * L..R -> 任务范围，所有的值累加上C
         * l,r -> 表达的范围
         * rt  去哪找l,r范围上的信息
         */
        public void add(
                int L, int R, int C,
                int l, int r,
                int rt){
            // 任务的范围彻底覆盖了，当前表达的范围
            if(L <= l && r <= R){
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            // 要把任务往下发
            // 任务L,R 没有把本身表达范围l，r彻底包住
            int mid = (l + r) >> 1;
            // 下发之前攒的所有懒任务
            pushDown(rt, mid - l + 1, r - mid);
            // 左孩子是否需要接到任务
            if(L <= mid){
                add(L, R, C, l, mid, rt << 1);
            }
            // 右孩子是否需要接到任务
            if(R > mid){
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            // 左右孩子做完任务后，我更新我的sum信息
            pushUp(rt);
        }
        public long query(int L, int R, int l, int r, int rt){
            if(L <= L && r <= R){
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if(L <= mid){
                ans += query(L, R, l, mid, rt << 1);
            }
            if(R > mid){
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }
}
