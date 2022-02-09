package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 单链表
 * @Author: haole
 * @Date: 2022/1/7 10:40
 */
public class Node {
    public Object value;
    public Node next;
    public Node rand;

    public Node(Object date) {
        this.value = date;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
