package cn.zju.zuochengyun.Practice2;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 一个数组中，如果两个数的公共因子有大于1的，则认为这两个数之间有通路
 * 返回数组中，有多少个独立的域
 */
public class code14_LargestComponentSizebyCommonFactor {
    public static class UnionFind {
        public int n;
        public int[] parent;
        public int[] size;
        public int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.setCount = n;
        }

        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return;
            }
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            size[x] += size[y];
            parent[y] = x;
            setCount--;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public int maxSize() {
            int ans = 0;
            for (int s : size) {
                ans = Math.max(ans, s);
            }
            return ans;
        }
    }
    // arr中没有小于1的数
    // 利用并查集O(N ^ 2)遍历构建集合
    // 最后返回并查集的setCount
    public static int largestComponentSize(int[] arr) {
        UnionFind uf = new UnionFind(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (gcd(arr[i], arr[j]) != 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.setCount;
    }

    // 求任意两个数的最大公约数
    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    // O(N * (V ^ 0.5))
    // V 为arr中的最大值
    public static int largestComponentSize2(int[] arr) {
        UnionFind uf = new UnionFind(arr.length);
        // key是某一个因子
        // value是包含key因子的，其中的一个位置
        HashMap<Integer, Integer> factorMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int limit = (int) Math.sqrt(num);
            for (int j = 1; j <= limit; j++) {
                if (num % j == 0) {
                    if (j != 1) {
                        if (!factorMap.containsKey(j)) {
                            factorMap.put(j, i);
                        } else {
                            uf.union(factorMap.get(j), i);
                        }
                    }
                    int other = num / j;
                    if (other != 1) {
                        if (!factorMap.containsKey(other)) {
                            factorMap.put(other, i);
                        } else {
                            uf.union(factorMap.get(other), i);
                        }
                    }
                }
            }
        }
        return uf.setCount;
    }
}
