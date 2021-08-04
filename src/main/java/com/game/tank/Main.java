package main.java.com.game.tank;

/**
 * @Description: 坦克大战主函数
 * @Author: haole
 * @Date: 2021/7/4 7:43
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            tf.tankList.add(new Tank(50 + i * 50, 50, Dir.DOWN, Group.BAD, tf));
        }
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
