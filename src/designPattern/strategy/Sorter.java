package designPattern.strategy;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/8/4 14:54
 */
public class Sorter {
    public void sort(Comparable[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int end = N - 1; end >= 0; end--) {
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1].compareTo(arr[second]) == -1) {
                    swap(arr, second - 1, second);
                }
            }
        }
    }

    public static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


}
