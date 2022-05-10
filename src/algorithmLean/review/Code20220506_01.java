package algorithmLean.review;

/**
 * @Description: 第二节：二分法，时间复杂度、动态数组、哈希表和有序表
 * 题目：
 * 1.有序数组中找到num
 * <p>
 * 2.有序数组中找到>=num最左的位置
 * <p>
 * 3.有序数组中找到<=num最右的位置
 * <p>
 * 4.局部最小值问题
 * @Author: haole
 * @Date: 2022/5/6 15:51
 */
public class Code20220506_01 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 12, 56, 98, 1234, 7674, 54535};
        System.out.println(isExist(arr, 6) ? "存在" : "不存在");
    }

    /**
     * 1.有序数组中找到num
     *
     * @param arr
     * @param num
     * @return
     */
    private static boolean isExist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            //mid = (L + R) / 2; 此方法取中位数可能会造成溢出
            mid = L + ((R - L) >> 1); // 取中位数尽量使用此方法，不会发生溢出
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return arr[L] == num;
    }

    /**
     * 2.有序数组中找到>=num最左的位置
     *
     * @param arr
     * @param num
     * @return
     */
    private static int findMaxLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length;
        int mid = 0;
        int ans = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] >= num) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 3.有序数组中找到<=num最右的位置
     *
     * @param arr
     * @param num
     * @return
     */
    private static int findMinRight(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length;
        int mid = 0;
        int ans = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] >= num) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 4.局部最小值问题
     *
     * @param arr
     * @return
     */
    private static int minValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        int N = arr.length;
        if (arr[N - 1] < arr[N - 2]) {
            return N - 1;
        }
        int L = 1;
        int R = N - 2;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid - 1] < arr[mid]) {
                R = mid - 1;
            } else if (arr[mid + 1] < arr[mid]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;
    }

}
