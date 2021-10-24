package cn.zju.zuochengyun.SegmentTree;

public class code7_DynamicSegementTree {
    public static class DynamicSegmentTree {
        public int[] left;
        public int[] right;
        public int[] sum;
        public int cnt;
        public int size;

        public DynamicSegmentTree(int max) {
            left = new int[max << 1];
            right = new int[max << 1];
            sum = new int[max << 1];
            cnt = 1;
            size = max;
        }

        public void add(int i, int v) {
            add(1, 1, size, i, v);
        }

        private void add(int c, int l, int r, int i, int v) {
            if (l == r) {
                sum[c] += v;
            } else {
                int mid = (l + r) / 2;
                if (i <= mid) {
                    if (left[c] == 0) {
                        left[c] = ++cnt;
                    }
                    add(left[c], l, mid, i, v);
                } else {
                    if (right[c] == 0) {
                        right[c] = ++cnt;
                    }
                    add(right[c], mid + 1, r, i, v);
                }
                sum[c] = sum[left[c]] + sum[right[c]];
            }
        }

        public int sum(int s, int e) {
            return sum(1, 1, size, s, e);
        }

        private int sum(int c, int l, int r, int s, int e) {
            if (sum[c] == 0 || (s <= l && r <= e)) {
                return sum[c];
            }
            int mid = (l + r) / 2;
            if (e <= mid) {
                return sum(left[c], l, mid, s, e);
            } else if (s > mid) {
                return sum(right[c], mid + 1, r, s, e);
            } else {
                return sum(left[c], l, mid, s, e) + sum(right[c], mid + 1, r, s, e);
            }
        }

    }

    public static class Right {
        public int[] arr;

        public Right(int size) {
            arr = new int[size + 1];
        }

        public void add(int i, int v) {
            arr[i] += v;
        }

        public int sum(int s, int e) {
            int sum = 0;
            for (int i = s; i <= e; i++) {
                sum += arr[i];
            }
            return sum;
        }

    }

    public static void main(String[] args) {
        int size = 100;
        int testTime = 5000;
        int value = 500;
        DynamicSegmentTree dst = new DynamicSegmentTree(size);
        Right right = new Right(size);
        System.out.println("测试开始");
        for (int k = 0; k < testTime; k++) {
            if (Math.random() < 0.5) {
                int i = (int) (Math.random() * size) + 1;
                int v = (int) (Math.random() * value);
                dst.add(i, v);
                right.add(i, v);
            } else {
                int a = (int) (Math.random() * size) + 1;
                int b = (int) (Math.random() * size) + 1;
                int s = Math.min(a, b);
                int e = Math.max(a, b);
                int ans1 = dst.sum(s, e);
                int ans2 = right.sum(s, e);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                    System.out.println(ans1);
                    System.out.println(ans2);
                }
            }
        }
        System.out.println("测试结束");
    }
}
