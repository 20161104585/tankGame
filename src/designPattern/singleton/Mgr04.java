package designPattern.singleton;

/**
 * @Description: 单例模式-懒汉式（lazy loading）
 * 虽然达到了按需初始化的目的，但是却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来效率下降
 * @Author: haole
 * @Date: 2021/8/4 13:49
 */
public class Mgr04 {
    private static Mgr04 INSTANCE;

    private Mgr04() {
    }

    public static synchronized Mgr04 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }
}
