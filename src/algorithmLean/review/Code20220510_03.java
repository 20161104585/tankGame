package algorithmLean.review;

/**
 * @Description: 4.用环形数组实现栈和队列
 * @Author: haole
 * @Date: 2022/6/3 21:10
 */
public class Code20220510_03 {
    // 数组实现队列
    public static class MyQueue {

        private int[] arr;
        private int begin;
        private int end;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            this.arr = new int[limit];
            this.begin = 0;
            this.end = 0;
            this.size = 0;
            this.limit = limit;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

        public void add(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了！！！");
            }
            size++;
            arr[end] = value;
            end = nextIndex(end);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了！！！");
            }
            size--;
            int res = arr[begin];
            begin = nextIndex(begin);
            return res;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }

    // 栈的特性是先进后出。pop：把栈顶元素弹出栈  push：把元素压入栈  peek：得到栈顶元素的值

    public static class MyStack {
        private int[] arr;
        private int size;
        private final int limit;


        public MyStack(int limit) {
            this.arr = new int[limit];
            this.size = -1;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit - 1) {
                throw new RuntimeException("栈满了，不能再加了！！！");
            }
            size++;
            arr[size] = value;
        }

        public int pop() {
            if (size < 0) {
                throw new RuntimeException("栈空了，不能再拿了！！！");
            }

            int ans = arr[size];
            size--;
            return ans;
        }

        public boolean isEmpty() {
            return size < 0;
        }
    }
}
