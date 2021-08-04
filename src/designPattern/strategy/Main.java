package designPattern.strategy;


import java.util.Arrays;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/8/4 14:51
 */
public class Main {
    public static void main(String[] args) {
        int[] a = {9, 2, 3, 5, 7, 1, 4};
        Cat[] cats = {new Cat(5, 6), new Cat(4, 6), new Cat(8, 6)};
        Sorter sorter = new Sorter();
        sorter.sort(cats);
        System.out.println(Arrays.toString(cats));
    }
}
