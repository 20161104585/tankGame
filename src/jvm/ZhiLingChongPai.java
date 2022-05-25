package jvm;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: jvm调优-指令重排
 * @Author: haole
 * @Date: 2022/3/5 20:31
 */
public class ZhiLingChongPai {
    public static final int TOTAL = 1_000_000_000;
    public static int a = 0, b = 0;
    public static int x = 0, y = 0;

    //验证指令重排
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < TOTAL; i++) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            // 门栓锁
            CountDownLatch countDownLatch = new CountDownLatch(2);

            new Thread(() -> {
                a = 1;
                x = b;
                countDownLatch.countDown();
            }).start();

            new Thread(() -> {
                b = 1;
                y = a;
                countDownLatch.countDown();

            }).start();

            countDownLatch.await();
            System.out.println(x + " " + y + " " + i);

            if (x == 0 && y == 0) {
                System.out.println("i \t" + i);
                break;
            }
        }
    }
}
