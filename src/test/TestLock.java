package test;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程测试类
 * @Author: haole
 * @Date: 2022/5/24 16:11
 */
public class TestLock {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
