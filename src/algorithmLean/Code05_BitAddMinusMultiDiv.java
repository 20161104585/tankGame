package algorithmLean;

/**
 * @Description: 位图、位运算实现加减乘除
 * @Author: haole
 * @Date: 2021/7/27 17:23
 */
public class Code05_BitAddMinusMultiDiv {
    /**
     * 位运算实现加
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * 位运算实现取相反数
     *
     * @param n
     * @return
     */
    public static int negNum(int n) {
        return add(~n, 1);
    }

    /**
     * 位运算实现减
     *
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * 位运算实现乘
     *
     * @param a
     * @param b
     * @return
     */
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    /**
     * 判断是否是负数
     *
     * @param n
     * @return
     */
    public static boolean isNeg(int n) {
        return n < 0;
    }

    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        } else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else if (dividend == Integer.MIN_VALUE) {
            if (divisor == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int ans = div(add(dividend, 1), divisor);
                return add(ans, div(minus(dividend, multi(ans, divisor)), divisor));
            }
        } else {
            return div(dividend, divisor);
        }
    }

}
