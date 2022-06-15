package multiThreadLean;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 多线程的三种创建方式
 * @Author: haole
 * @Date: 2022/6/13 16:12
 */
public class CreationMethod {

    /**
     * 第一种：继承Thread,重写run()方法
     */
    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("第一种方式，继承thread，重写run()方法！");
        }
    }

    /**
     * 第二种：实现Runnable接口，重写run()方法
     */
    public static class MyRun implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("第二种方式，实现Runnable接口，重写run()方法！");
        }
    }

    /**
     * 第五种：带返回值的方式，实现callable<T>接口，重写call方法
     */
    public static class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                Lock lock = new ReentrantLock();
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("第五种：实现callable接口，重写call方法，并返回！");
            return "success";
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 启动方式：直接new对象，调用start方法
         */
        MyThread myThread = new MyThread();
        myThread.start();

        /**
         * 启动方式：new一个thread对象，传入自己实现Runnable接口的对象，一样调用start方法
         */
        Thread thread = new Thread(new MyRun());
        thread.start();
        /**
         * 以上两者较好的是第二种，实现Runnable接口，因为他还可以去继承其他类，更加灵活。
         */

        /**
         * 第三种:使用lmbda表达式
         */
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("第三种，使用Lmbda表达式实现！");
        }).start();

        /**
         * 第四种：使用线程池
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            System.out.println("第四种，使用线城池实现！");
        });
        /**
         * 将mycall扔到线程池中,Future代表异步的概念
         */
        Future<String> f = executorService.submit(new MyCall());
        executorService.shutdown();

        /**
         * 第五种：实现callable接口,需要new futureTask，放到Thread里边去
         */
        FutureTask<String> futureTask = new FutureTask<String>(new MyCall());
        Thread tt = new Thread(futureTask);
        tt.start();

        /**
         * 下边两种方法获取返回值是阻塞的
         */
        System.out.println(futureTask.get());
        System.out.println(f.get());
    }
}
