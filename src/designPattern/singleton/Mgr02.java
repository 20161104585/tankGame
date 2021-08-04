package designPattern.singleton;

/**
 * @Description: 单例模式-饿汉式(与01一致)
 * @Author: haole
 * @Date: 2021/8/4 13:31
 */
public class Mgr02 {
    private static final Mgr02 INSTANCE;

    static {
        INSTANCE = new Mgr02();
    }

    private Mgr02() {
    }

    public static Mgr02 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr02 m1 = Mgr02.getInstance();
        Mgr02 m2 = Mgr02.getInstance();
        System.out.println(m1 == m2);
    }
}
