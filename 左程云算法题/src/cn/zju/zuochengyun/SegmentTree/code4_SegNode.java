package cn.zju.zuochengyun.SegmentTree;

public class code4_SegNode {
    public class SegNode {
        long lo, hi;
        int add;
        SegNode lchild, rchild;

        public SegNode(long left, long right) {
            lo = left;
            hi = right;
            add = 0;
            lchild = null;
            rchild = null;
        }
    }

    public SegNode build(int left, int right) {
        SegNode node = new SegNode(left, right);
        if (left == right) {
            return node;
        }
        int mid = (left + right) >> 1;
        node.lchild = build(left, mid);
        node.rchild = build(right, mid);
        return node;
    }
    public int count(SegNode root, int left, int right) {
        if (left > root.hi || right < root.lo) {
            return 0;
        }
        if (left <= root.lo && root.hi <= right) {
            return root.add;
        }
        return count(root.lchild, left, right) + count(root.rchild, left, right);
    }

    public void insert(SegNode root, int val) {
        root.add++;
        if (root.lo == root.hi) {
            return;
        }
        long mid = (root.lo + root.hi) / 2;
        if (val <= mid) {
            insert(root.lchild, val);
        } else {
            insert(root.rchild, val);
        }
    }

    public void insertDynamic(SegNode root, long val) {
        root.add++;
        if (root.lo == root.hi) {
            return;
        }
        long mid = (root.lo + root.hi) >> 1;
        if (val <= mid) {
            if (root.lchild == null) {
                root.lchild = new SegNode(root.lo, mid);
            }
            insertDynamic(root.lchild, val);
        } else {
            if (root.rchild == null) {
                root.rchild = new SegNode(mid + 1, root.hi);
            }
            insertDynamic(root.rchild, val);
        }
    }

    public int countDynamic(SegNode root, long left, long right) {
        if (root == null) {
            return 0;
        }
        if (left > root.hi || right < root.lo) {
            return 0;
        }
        if (left <= root.lo && root.hi <= right) {
            return root.add;
        }
        return countDynamic(root.lchild, left, right) + countDynamic(root.rchild, left, right);
    }
}
