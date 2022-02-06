package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 随机快速排序
 * @Author: haole
 * @Date: 2022/2/5 15:58
 */
public class Code20220205_01 {
    /**
     * 简化版荷兰国旗问题
     * 给定一个数组arr,和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partiton(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }

        int lessEqual = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index] <= arr[r]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, r, lessEqual + 1);
        return lessEqual;
    }

    /**
     * 题目：荷兰国旗问题
     * 描述：给定一个数组arr,和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。要求额外空间复杂度O(1)，时间复杂度O(N)
     */
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int lessEqual = l - 1;
        int moreEqual = r;
        int index = l;
        while (index < moreEqual) {
            if (arr[index] < arr[r]) {
                swap(arr, index++, ++lessEqual);
            } else if (arr[index] == arr[r]) {
                index++;
            } else {
                swap(arr, index, --moreEqual);
            }
        }
        swap(arr, r, moreEqual);
        return new int[]{lessEqual + 1, moreEqual};

    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        //快排3.0增加，为了抵消最差情况，使复杂度收敛于O(N*logN)
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] equalArea = netherlandsFlag(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);
    }

    private static void swap(int[] arr, int p2, int i) {
        int temp = arr[p2];
        arr[p2] = arr[i];
        arr[i] = temp;
    }
}
