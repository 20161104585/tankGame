import java.util.concurrent.ConcurrentHashMap;

public class Tets {
    public static class WaitThread implements Runnable{
        Object lock;

        public WaitThread(Object lock){
            this.lock = lock;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            synchronized (lock){
                System.out.println(threadName + "开始进入同步代码块区域");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + "准备离开同步代码块区域");
            }
        }
    }

    public static class NotifyThread implements Runnable{

        Object lock;

        public NotifyThread(Object lock){
            this.lock = lock;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            synchronized (lock){
                System.out.println(threadName + "开始进入同步代码块区域");
                lock.notify();
                try {
                    System.out.println(threadName + "业务处理开始");
                    // 暂停 2s 表示业务处理
                    Thread.sleep(2000);
                    System.out.println(threadName + "业务处理结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + "准备离开同步代码块区域");
                //lock.notify();放在这一行唤醒，效果一样
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "退出同步代码块后续操作");
        }
    }
    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        Thread waitThread = new Thread(new WaitThread(lock), "waitThread");
        Thread notifyThread = new Thread(new NotifyThread(lock), "notifyThread");
        waitThread.start();
        Thread.sleep(1000);
        notifyThread.start();
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("1", "msb");
    }
}
