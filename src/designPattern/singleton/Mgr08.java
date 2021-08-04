package designPattern.singleton;

/**
 * @Description: 不仅可以解决线程同步，还可以防止反序列化；
 * @Author: haole
 * @Date: 2021/8/4 14:10
 */
public enum Mgr08 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr08.INSTANCE.hashCode());
            }).start();
        }
    }
}
