package cn.zju.group6;

public class code6_324_WiggleSortII {
    // 从题中所给条件出发，0 <= nums[i] <= 5000，5000并不是一个很大的数字，因此可以考虑用桶排序。
    public void wiggleSort1(int[] nums) {
        int[] bukcet = new int[5001];
        for (int num : nums) {
            bukcet[num]++;
        }
        int len = nums.length;
        int small, big;
        if ((len & 1) == 0) {
            big = len - 1;
            small = len - 2;
        } else {
            big = len - 2;
            small = len - 1;
        }
        int j = 5000;
        for (int i = 1; i <= big; i += 2) {
            while (bukcet[j] == 0) j--;
            nums[i] = j;
            bukcet[j]--;
        }
        for (int i = 0; i <= small; i += 2) {
            while (bukcet[j] == 0) j--;
            nums[i] = j;
            bukcet[j]--;
        }
    }
    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        findIndexNum(nums, 0, n - 1, n / 2);
        if ((n & 1) == 0) {
            shuffle(nums, 0, n - 1);
            reverse(nums, 0, n - 1);
        } else {
            shuffle(nums, 1, n - 1);
        }
    }

    public int findIndexNum(int[] arr, int l, int r, int index) {
        int pivot = 0;
        int[] range = null;
        while (l < r) {
            pivot = arr[l + (int) (Math.random() * (r - l + 1))];
            range = partition(arr, l, r, pivot);
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (range[0] > index) {
                r = range[0] - 1;
            } else {
                l = range[1] + 1;
            }
        }
        return arr[l];
    }

    public int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    public void shuffle(int[] nums, int l, int r) {
        while (r - l + 1 > 0) {
            int lenAndOne = r - l + 2;
            int bloom = 3;
            int k = 1;
            while (bloom <= lenAndOne / 3) {
                bloom *= 3;
                k++;
            }
            int m = (bloom - 1) / 2;
            int mid = (l + r) / 2;
            rotate(nums, l + m, mid, mid + m);
            cycles(nums, l - 1, bloom, k);
            l = l + bloom - 1;
        }
    }

    public void cycles(int[] nums, int base, int bloom, int k) {
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int next = (2 * trigger) % bloom;
            int cur = next;
            int record = nums[next + base];
            int tmp = 0;
            nums[next + base] = nums[trigger + base];
            while (cur != trigger) {
                next = (2 * cur) % bloom;
                tmp = nums[next + base];
                nums[next + base] = record;
                cur = next;
                record = tmp;
            }
        }
    }

    public void rotate(int[] arr, int l, int m, int r) {
        reverse(arr, l, m);
        reverse(arr, m + 1, r);
        reverse(arr, l, r);
    }
}
