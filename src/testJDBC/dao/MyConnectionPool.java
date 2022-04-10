package testJDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @Description:
 * @Author: haole
 * @Date: 2022/4/10 15:32
 */
public class MyConnectionPool {
    /**
     * url：统一资源定位符，定位到我们要连接的数据库
     * 1协议       jdbc:mysql
     * 2IP        39.104.162.252
     * 3端口号     3306
     * 4数据库名字  mydev
     * 5参数
     * 协议://ip:端口号/数据库名称?参数名=参数值&参数名=参数值&....
     * jdbc:mysql://39.104.162.252:3306/mydev?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
     * user：用户名
     * password：密码
     */
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://39.104.162.252:3306/mydev?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static String user = "dmsTest";
    private static String password = "Bb123123@.";
    /**
     * 初始连接数量
     */
    private static int initSize = 5;
    /**
     * 连接最大数量
     */
    private static int maxSize = 10;
    /**
     * 连接池
     */
    private static LinkedList<Connection> pool;

    static {
        //加载并注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pool = new LinkedList<>();
        for (int i = 0; i < initSize; i++) {
            Connection conn = initConnection();
            if (conn != null) {
                pool.add(conn);
            }
        }
    }

    /**
     * 初始化一个私有的连接对象
     *
     * @return
     */
    private static Connection initConnection() {
        Connection conn = null;
        try {
            DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 共有的向外界提供链接的方法
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        if (pool.size() > 0) {
            connection = pool.remove();
        } else {
            connection = initConnection();
        }
        return connection;
    }

    public static void returnConnection(Connection connection) {
        if (null != connection) {
            try {
                if (!connection.isClosed()) {
                    if (pool.size() < maxSize) {
                        connection.setAutoCommit(true);
                        pool.add(connection);
                    } else {
                        if (null != connection) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
