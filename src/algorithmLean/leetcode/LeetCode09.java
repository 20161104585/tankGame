package algorithmLean.leetcode;

/**
 * @Description: 9. 回文数
 * @Author: haole
 * @Date: 2022/2/18 10:48
 */
public class LeetCode09 {
    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 例如，121 是回文，而 123 不是。
     * <p>
     * 链接：https://leetcode-cn.com/problems/palindrome-number
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        //如果小于0，或者不等于0的时候，数字是以0结尾的，一定不是回文数
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int cur = 0;
        int temp = 0;
        //从末尾截数字，例如：1234321 -> 123432   1  ->  12343   12   -> 1234   123
        while (x > cur) {
            temp = x % 10;
            x = x / 10;
            cur = cur * 10 + temp;
        }
        //如果偶数后半段数字和前半段相等，或者奇数后半段去掉最后一个单独字段，和前半段相等，就为回文数，否则不是
        return x == cur || x == cur / 10;
    }
}
