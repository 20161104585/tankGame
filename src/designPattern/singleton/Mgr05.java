package designPattern.singleton;

/**
 * @Description: 单例模式-懒汉式（lazy loading）
 * 虽然达到了按需初始化的目的，但是却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来效率下降
 */
public class Mgr05 {
    private static Mgr05 INSTANCE;

    private Mgr05() {
    }

    public static Mgr05 getInstance() {
        //双重检查
        if (INSTANCE == null) {
            synchronized (Mgr05.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr05();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr05.getInstance().hashCode());
            }).start();
        }
    }
}
