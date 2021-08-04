package main.java.com.game.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/8/4 12:39
 */
public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (props == null) {
            return null;
        }
        return props.get(key);
    }
}
