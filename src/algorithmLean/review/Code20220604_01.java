package algorithmLean.review;

/**
 * @Description: 归并排序相关面试题
 * 题目：
 * 1.归并排序递归和非递归实现
 * 2.求数组小和
 * 3.在一个数组中求所有的逆序对
 * 4.求数组中的大两倍数对数量
 * 5.荷兰国旗问题的实现
 * 6.快速排序从1.0到3.0的实现
 * 7.区间和的个数
 * @Author: haole
 * @Date: 2022/6/4 21:49
 */
public class Code20220604_01 {

    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{0, 5, 9, 7, 4, 1, 5, 3, 4, 9, 2, 5, 7, 6, 2, 4, 9};
        // int[] arr = new int[]{1, 3, 4, 2, 5};
        // int[] arr = new int[]{3, 1, 7, 0, 2};
        // int res = arrMinSum(arr);
        // int res = reverseOrderPair(arr);
//        int res = biggerThanRightTwice(arr);
//        System.out.println(res);
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 1.归并排序递归和非递归实现
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        // 首先准备一个辅助数组，大小为L-R的长度
        int[] help = new int[R - L + 1];
        int i = 0; // 辅助help填数
        int p1 = L; //左指针
        int p2 = M + 1; //右指针

        // 如果左指针不越界并且右指针不越界，循环比较并拷贝
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 如果p1没拷贝完，继续拷贝剩下的
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        // 如果p2没拷贝完，继续拷贝剩下的
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 将辅助数组的数拷贝回去
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static void mergeSortTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M > N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 2.求数组小和
     * <p>
     * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和,所有数的小和累加起来，叫数组小和
     * 例子： [1,3,4,2,5]
     * 1左边比1小的数：没有
     * 3左边比3小的数：1
     * 4左边比4小的数：1、3
     * 2左边比2小的数：1
     * 5左边比5小的数：1、3、4、 2
     * 所以数组的小和为1+1+3+1+1+3+4+2=16
     * 给定一个数组arr，求数组小和
     *
     * @param arr
     * @return
     */
    public static int arrMinSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return processMinSum(arr, 0, arr.length - 1);
    }

    private static int processMinSum(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int lMinSum = processMinSum(arr, L, M);
        int rMinSum = processMinSum(arr, M + 1, R);
        int mergeSum = mergeMinSum(arr, L, M, R);
        return lMinSum + rMinSum + mergeSum;
    }

    private static int mergeMinSum(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] < arr[p2]) {
                res += (arr[p1] * (R - p2 + 1));
                help[i++] = arr[p1++];
            } else {
                help[i++] = arr[p2++];
            }
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }


    /**
     * 3.在一个数组中求所有的逆序对
     * <p>
     * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为降序对
     * 给定一个数组arr，求数组的降序对总数量
     */
    public static int reverseOrderPair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return processRevOrdPair(arr, 0, arr.length - 1);
    }

    private static int processRevOrdPair(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int lBTG = processRevOrdPair(arr, L, M);
        int rBTG = processRevOrdPair(arr, M + 1, R);
        int mBTG = mergeRevOrdPair(arr, L, M, R);
        return lBTG + rBTG + mBTG;
    }

    private static int mergeRevOrdPair(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] <= arr[p2]) {
                help[i++] = arr[p1++];
            } else {
                res += (M - p1 + 1);
                help[i++] = arr[p2++];
            }
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    /**
     * 4.求数组中的大两倍数对数量
     * 在一个数组中，对于任何一个数num，求有多少个(后面的数*2)依然<num，返回总个数
     * 比如：[3,1,7,0,2]
     * 3的后面有：1，0
     * 1的后面有：0
     * 7的后面有：0，2
     * 0的后面没有
     * 2的后面没有
     * 所以总共有5个
     */
    public static int biggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return processBTG(arr, 0, arr.length - 1);
    }

    private static int processBTG(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int lBTG = processBTG(arr, L, M);
        int rBTG = processBTG(arr, M + 1, R);
        int mBTG = mergeBTG(arr, L, M, R);
        return lBTG + rBTG + mBTG;
    }

    private static int mergeBTG(int[] arr, int L, int M, int R) {
        // 先计算有符合条件的有几个数，指针不回退的方法
        int ans = 0;
        // 目前囊括进来的数。是从[M+1,windowR)
        int windowR = M + 1;
        for (int i = L; i <= M; i++) {
            while (windowR <= R && arr[i] > (arr[windowR] << 1)) {
                windowR++;
            }
            ans += windowR - M - 1;
        }


        // 后merge
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    /**
     * 5.荷兰国旗问题的实现，以数组最后一个为划分值
     *
     * @param arr
     * @param l
     * @param r
     * @return 返回等于区域的下标范围
     */
    public static int[] partiton(int[] arr, int l, int r) {
        // 判读划分区域是否越界
        if (l > r) {
            return new int[]{-1, -1};
        }
        // 判断划分区域是否重叠
        if (l == r) {
            return new int[]{l, r};
        }
        // 定义小于区域边界
        int less = l - 1;
        // 大于区域边界
        int more = r;
        // 遍历到的数
        int index = l;
        // 当前位置不能和大于区域撞上
        while (index < more) {
            // 如果当前位置等于划分值，当前位置直接移动到下一个位置
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) { // 如果当前位置小于划分值，当前位置和小于区域下一个值交换，小于区域右移一位，当前位置移动到下一个位置
                swap(arr, index++, ++less);
            } else { // 其他情况就是当前位置大于划分值，当前位置和大于区域上一个值交换，当前位置不变，大于区域左移一位
                swap(arr, index, --more);
            }
        }
        // 最后将划分值和大于区域边界值交换，完成等于区域放中间工作
        swap(arr, r, more);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int p2, int i) {
        int temp = arr[p2];
        arr[p2] = arr[i];
        arr[i] = temp;
    }

    /**
     * 6.快速排序从1.0到3.0的实现
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processQuicckSort(arr, 0, arr.length - 1);
    }

    private static void processQuicckSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //快排3.0增加，为了抵消最差情况，使复杂度收敛于O(N*logN)
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] sortAns = mergerQuickSort(arr, L, R);
        processQuicckSort(arr, L, sortAns[0] - 1);
        processQuicckSort(arr, sortAns[1] + 1, R);
    }

    private static int[] mergerQuickSort(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }


    /**
     * 7.区间和的个数
     * 给定一个数组arr，两个整数lower和upper，
     * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
     * Leetcode题目：https://leetcode.com/problems/count-of-range-sum/
     *
     * 需要知识：前缀和+归并
     */
}
