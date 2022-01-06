package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 学习二分法
 * @Author: haole
 * @Date: 2022/1/5 17:01
 */
public class Code01 {
    /**
     * 在一个有序数组内，用二分法判断一个数是否存在
     */
    public static boolean exist(int[] arr, int num) {
        //先判断边界
        if (arr == null || arr.length == 0) {
            return false;
        }

        int l = 0;//记录左边界
        int r = arr.length - 1;//记录右边界
        int mid = 0;//记录中间
        //如果没有越界，循环查找
        while (l < r) {
            mid = l + ((r - l) >> 1);//等价于mid = (l+r)/2 ----等价于mid = l + (r-l)/2
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return arr[r] == num;
    }


    /**
     * 在一个无序数组内,相邻值不相等，用二分法查询局部最小值并返回
     */
    public static int locaMin(int[] arr) {
        //先判断边界
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int l = 1;
        int r = arr.length - 2;
        int mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
