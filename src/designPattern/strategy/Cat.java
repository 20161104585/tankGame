package designPattern.strategy;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/8/4 15:03
 */
public class Cat implements Comparable<Cat> {
    int height, weight;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Cat cat) {
        if (this.weight < cat.weight) {
            return -1;
        } else if (this.weight > cat.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
