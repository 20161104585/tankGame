package algorithmLean;

import java.util.Stack;

/**
 * @Description: 归并排序和快速排序
 * @Author: haole
 * @Date: 2021/7/29 17:14
 */
public class Code08_MergeSortAndQuickSort {
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

    //arr[L...R]范围上，请让这个范围上的数有序
    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        //防止数组很大时越界
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        //要不p1越界，要不p2越界
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    /**
     * 快速排序算法通过多次比较和交换来实现排序，其排序流程如下:
     * (1)首先设定一个分界值，通过该分界值将数组分成左右两部分。
     * (2)将大于或等于分界值的数据集中到数组右边，小于分界值的数据集中到数组的左边。此时，左边部分中各元素都小于或等于分界值，而右边部分中各元素都大于或等于分界值。
     * (3)然后，左边和右边的数据可以独立排序。对于左侧的数组数据，又可以取一个分界值，将该部分数据分成左右两部分，同样在左边放置较小值，右边放置较大值。右侧的数组数据也可以做类似处理。
     * (4)重复上述过程，可以看出，这是一个递归定义。通过递归将左侧部分排好序后，再递归排好右侧部分的顺序。当左、右两个部分各数据排序完成后，整个数组的排序也就完成了。
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processQuick(arr, 0, arr.length - 1);
        return;
    }

    public static void processQuick(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //L<R
        int[] equalE = partition(arr, L, R);
        //equalE[0] 等于区域第一个数
        //equalE[0] 等于区域最后一个数
        partition(arr, L, equalE[0] - 1);
        partition(arr, equalE[0] - 1, R);
    }

    public static class Job {
        public int L;
        public int R;

        public Job(int L, int R) {
            this.L = L;
            this.R = R;
        }
    }

    public static void quickSort2(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Job cur = stack.pop();
            int[] equalE = partition(arr, cur.L, cur.R);
            if (equalE[0] > cur.L) {
                stack.push(new Job(cur.L, equalE[0] - 1));
            }
            if (equalE[1] < cur.R) {
                stack.push(new Job(equalE[1] + 1, cur.R));
            }
        }
    }


    public static int[] partition(int[] arr, int L, int R) {
        int lessEqualR = L - 1;
        int moreEqualL = R;
        int index = L;
        while (index < moreEqualL) {
            if (arr[index] < arr[moreEqualL]) {
//                swap(arr, index, lessEqualR + 1);
//                index++;
//                lessEqualR++;
                swap(arr, index++, ++lessEqualR);
            } else if (arr[index] > arr[moreEqualL]) {
//                swap(arr, index, lessEqualR + 1);
//                index++;
//                lessEqualR++;
                swap(arr, index, --moreEqualL);
            } else {
                index++;
            }
        }
        swap(arr, moreEqualL, moreEqualL);
        return new int[]{lessEqualR + 1, moreEqualL};
    }

    public static void splitNum(int[] arr) {
        int lessEqualR = -1;
        int index = 0;
        int mostR = arr.length - 1;
        while (index < arr.length) {
            if (arr[index] <= arr[mostR]) {
//                swap(arr, index, lessEqualR + 1);
//                index++;
//                lessEqualR++;
                swap(arr, index++, ++lessEqualR);
            } else {
                index++;
            }
        }
    }

    public static void splitNum2(int[] arr) {
        int lessEqualR = -1;
        int moreEqualL = arr.length - 1;
        int index = 0;
        int mostR = arr.length - 1;
        while (index < moreEqualL) {
            if (arr[index] < arr[mostR]) {
//                swap(arr, index, lessEqualR + 1);
//                index++;
//                lessEqualR++;
                swap(arr, index++, ++lessEqualR);
            } else if (arr[index] > arr[mostR]) {
//                swap(arr, index, lessEqualR + 1);
//                index++;
//                lessEqualR++;
                swap(arr, index, --moreEqualL);
            } else {
                index++;
            }
        }
        swap(arr, mostR, moreEqualL);
    }

    private static void swap(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }
}
