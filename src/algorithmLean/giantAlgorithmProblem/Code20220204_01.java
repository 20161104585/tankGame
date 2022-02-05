package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 归并排序及其相关面试题
 * @Author: haole
 * @Date: 2022/2/4 9:45
 */
public class Code20220204_01 {
    /**
     * 归并排序（Merge Sort）是建立在归并操作上的一种有效，稳定的排序算法。
     * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
     * 若将两个有序表合并成一个有序表，称为二路归并。速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[i++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
    }

    /**
     * 归并排序非递归版
     */
    private static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        int mergeSize = 1;
        //步长小于数组最大值，进行循环
        while (mergeSize < n) {
            int l = 0;
            //每组步长内左右数据merge
            while (l < n) {
                int mid = l + mergeSize - 1;
                //控制边界
                if (mid >= n) {
                    break;
                }
                //取右边界
                int r = Math.min(mid + mergeSize, n - 1);
                merge(arr, l, mid, r);
                l = r + 1;
            }
            //防止溢出
            if (mergeSize > n / 2) {
                break;
            }

            //步长每次乘2
            mergeSize <<= 1;
        }
    }

    /**
     * 练习1
     * 题目：小和问题
     * 描述：在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
     * 时间复杂度 O(N*logN)，空间复杂度 O(N)。
     */
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process2(arr, 0, arr.length - 1);
    }

    /**
     * 使用归并排序求解，将左递归、右递归、merge中产生的小和总数累加返回
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int process2(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process2(arr, l, mid) + process2(arr, mid + 1, r) + merge2(arr, l, mid, r);
    }

    private static int merge2(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int smallSum = 0;
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            if (arr[p1] < arr[p2]) {
                smallSum += arr[p1] * (r - p2 + 1);
                temp[i++] = arr[p1++];

            } else {
                temp[i++] = arr[p2++];
            }
        }

        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[i++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
        return smallSum;
    }

    /**
     * 练习2
     * 题目：逆序对
     * 描述：设有一个序列{a_1, a_2, a_3,...a_{n-1}, a_n},对于序列中任意两个元素ai,aj，
     * 若i<j,ai>aj，则说明ai和aj是一对逆序对。求数组中有多少逆序对。
     */
    public static int mergeSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process3(arr, 0, arr.length - 1);
    }

    private static int process3(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 2);
        return process3(arr, l, mid) + process3(arr, mid + 1, r) + merge3(arr, l, mid, r);
    }

    /**
     * 注意，此处merge需要从左往右开始
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     * @return
     */
    private static int merge3(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = r;
        int res = 0;
        while (p1 >= l && p2 >= mid + 1) {
            if (arr[p1] > arr[p2]) {
                res += p2 - mid;
                help[i--] = arr[p1--];
            } else {
                help[i--] = arr[p2--];
            }
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    /**
     * 练习3
     * 描述：设有一个序列{a_1, a_2, a_3,...a_{n-1}, a_n},对于序列中任意两个元素ai,aj，
     * 若i<j,ai>aj*2，。求数组中有多少这样的数。
     */
    public static int mergeSort4(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process4(arr, 0, arr.length - 1);
    }

    private static int process4(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 2);
        return process4(arr, l, mid) + process4(arr, mid + 1, r) + merge4(arr, l, mid, r);
    }

    /**
     * 注意，此处需要先求和再merge
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     * @return
     */
    private static int merge4(int[] arr, int l, int mid, int r) {
        //先计算结果，再merge
        int ans = 0;
        int windowR = mid + 1;
        for (int j = l; j <= mid; j++) {
            while (windowR < r && arr[j] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - mid - 1;
        }

        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[i++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
        return ans;
    }

    /**
     * 练习4
     * 描述：给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
     * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-of-range-sum
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //获取该数组前缀和
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        return process5(sum, 0, sum.length - 1, lower, upper);
    }

    private int process5(long[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        return process5(sum, l, mid, lower, upper)
                + process5(sum, mid + 1, r, lower, upper)
                + merge5(sum, l, mid, r, lower, upper);
    }

    private int merge5(long[] sum, int l, int mid, int r, int lower, int upper) {
        //先根据前缀和求出所有符合条件的个数
        int res = 0;
        int windowR = l;
        int windowL = l;
        for (int i = mid + 1; i <= r; i++) {
            //先求出边界
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowR <= mid && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && sum[windowL] < min) {
                windowL++;
            }

            res += windowR - windowL;
        }


        //然后执行原merge操作
        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return res;
    }

}

