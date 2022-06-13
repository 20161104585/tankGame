package designPattern.singleton;

/**
 * @Description: 复习单例模式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐实用
 * 唯一缺点：不管用到与否，类加载时就完成实例化
 * @Author: haole
 * @Date: 2022/6/10 14:40
 */
public class ReviewSgLt01 {

    /**
     * 饿汉式
     */
//    private static final ReviewSgLt01 INSTANCE = new ReviewSgLt01();
//
//    private ReviewSgLt01() {
//    }
//
//    public static ReviewSgLt01 getInstance() {
//        return INSTANCE;
//    }

    /**
     * 懒汉式-线程不安全
     */
//    private static ReviewSgLt01 INSTANCE;
//
//    private ReviewSgLt01() {
//    }
//
//    public static ReviewSgLt01 getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ReviewSgLt01();
//        }
//        return INSTANCE;
//    }

    /**
     * 懒汉式-加锁
     */

//    private static ReviewSgLt01 INSTANCE;
//
//    private ReviewSgLt01() {
//    }
//
//    public static synchronized ReviewSgLt01 getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ReviewSgLt01();
//        }
//        return INSTANCE;
//    }
    /**
     * 懒汉式-双重检查
     */
//    private static ReviewSgLt01 INSTANCE;
//
//    private ReviewSgLt01() {
//    }
//
//    public static ReviewSgLt01 getInstance() throws Exception {
//
//        if (INSTANCE == null) {
//            synchronized (ReviewSgLt01.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new ReviewSgLt01();
//                }
//            }
//        }
//
//        return INSTANCE;
//    }

    /**
     * 静态内部类
     * JVM保证单例
     * 加载外部类时不会加载内部类，这样可以实现懒加载
     */

    private static class MyInstance {
        private static final ReviewSgLt01 INSTANCE = new ReviewSgLt01();
    }

    private ReviewSgLt01() {
    }

    public static ReviewSgLt01 getInstance() {
        return MyInstance.INSTANCE;
    }

}
