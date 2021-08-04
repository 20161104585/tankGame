package designPattern.singleton;

/**
 * @Description: 单例模式-懒汉式（lazy loading）
 * 虽然达到了按需初始化的目的，但是却带来线程不安全的问题
 * @Author: haole
 * @Date: 2021/8/4 13:35
 */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03() {
    }

    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }


}
