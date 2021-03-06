package main.java.com.game.tank;

import java.awt.*;

/**
 * @Description: 子弹实体类
 * @Author: haole
 * @Date: 2021/7/4 14:00
 */
public class Explode {
    public static final int WIDTH = ResourcrMgr.explodes[0].getWidth(),
            HEIGHT = ResourcrMgr.explodes[0].getHeight();

    private int x, y;
    TankFrame tf = null;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Audio("audio/explode.wav").play();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourcrMgr.explodes[step++], x, y, null);

        if (step >= ResourcrMgr.explodes.length) {
            tf.explodes.remove(this);
        }
    }
}
