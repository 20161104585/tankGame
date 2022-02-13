package algorithmLean.giantAlgorithmProblem;

import java.util.*;

/**
 * @Description: 二叉树的基本算法
 * @Author: haole
 * @Date: 2022/2/10 22:33
 */
public class Code20220210_01 {
    /**
     * 二叉树的先序、中序、后序遍历
     * 先：头左右
     * 中：左头右
     * 后：左右头
     * 结论1：给定一个包含x的先序遍历，后序遍历，先序中x的左部分并后序x的右部分，为x的祖先节点
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int date) {
            this.value = date;
        }
    }

    public static class TreeNode {
        public int value;
        public List<TreeNode> children;

        public TreeNode(int date, List<TreeNode> children) {
            this.value = date;
            this.children = children;
        }
    }

    public static class SuccNode {
        public int value;
        public SuccNode left;
        public SuccNode right;
        public SuccNode parent;

        public SuccNode(int date) {
            this.value = date;
        }
    }

    /**
     * 递归版本
     *
     * @param head
     */
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        //先序
        System.out.println(head.value);
        pre(head.left);
        //中序
        System.out.println(head.value);
        pre(head.right);
        //后序
        System.out.println(head.value);
    }

    /**
     * 非递归版本先序遍历
     *
     * @param head
     */
    public static void pre2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value);
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    /**
     * 非递归版本后序遍历
     *
     * @param head
     */
    public static void pos2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Stack<Node> pos = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop(); //头 右 左
                pos.push(head);
                if (head.left != null) {
                    stack.push(head.left);
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
            }
            while (!pos.isEmpty()) {
                System.out.println(pos.pop().value);
            }
        }
    }

    /**
     * 非递归版本中序遍历
     *
     * @param head
     */
    public static void in2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }
    }

    /**
     * 按层遍历
     */
    public static void level(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 二叉树的序列化
     */
    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pres(head, queue);
        return queue;
    }

    private static void pres(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add("#");
        } else {
            queue.add(String.valueOf(head.value));
            pres(head.left, queue);
            pres(head.right, queue);
        }
    }

    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add("#");
        } else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                } else {
                    ans.add("#");
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                } else {
                    ans.add("#");
                }
            }
        }
        return ans;
    }

    /**
     * 二叉树的反序列化
     */
    public static Node bulidByPreQueue(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return preb(queue);
    }

    private static Node preb(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(queue);
        head.right = preb(queue);
        return head;
    }


    public static Node bulidByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = generateNode(levelList.poll());
            cur.right = generateNode(levelList.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

    /**
     * 多叉树序列化为二叉树，
     */
    public static Node treeNodeSerial(TreeNode head) {
        if (head == null) {
            return null;
        }
        Node ans = new Node(head.value);
        ans.left = en(head.children);
        return ans;
    }

    private static Node en(List<TreeNode> children) {
        Node head = null;
        Node cur = null;
        for (TreeNode treeNode : children) {
            Node node = new Node(treeNode.value);
            if (head == null) {
                head = node;
            } else {
                cur.right = node;
            }
            cur = node;
            cur.left = en(treeNode.children);
        }
        return head;
    }

    /**
     * 二叉树反序列化为多叉树，
     */
    public static TreeNode decode(Node root) {
        if (root == null) {
            return null;
        }
        return new TreeNode(root.value, de(root.left));
    }

    private static List<TreeNode> de(Node root) {
        List<TreeNode> list = new ArrayList<>();
        while (root != null) {
            list.add(new TreeNode(root.value, de(root.left)));
            root = root.right;
        }
        return list;
    }

    /**
     * 求二叉树最宽层的值
     */
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curNode = head;
        Node nextNode = null;
        int max = 0;
        int curWidth = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextNode = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextNode = cur.right;
            }
            curWidth++;
            if (cur == curNode) {
                max = Math.max(max, curWidth);
                curWidth = 0;
                curNode = nextNode;
            }
        }
        return max;
    }


    /**
     * 寻找一个二叉树的后继节点
     * 描述：给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     */
    public static SuccNode getSuccessorNode(SuccNode node) {
        if (node == null) {
            return null;
        }
        //如果存在右树
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {//无右树
            SuccNode parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }

    }

    private static SuccNode getLeftMost(SuccNode node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    /**
     * 二叉树，折纸问题
     * 描述：请把纸条竖着放在桌⼦上，然后从纸条的下边向上⽅对折，压出折痕后再展 开。此时有1条折痕，突起的⽅向指向纸条的背⾯，
     * 这条折痕叫做“下”折痕 ；突起的⽅向指向纸条正⾯的折痕叫做“上”折痕。如果每次都从下边向上⽅ 对折，对折N次。
     * <p>
     * 请从上到下计算出所有折痕的⽅向。
     * <p>
     * 给定折的次数n,请返回从上到下的折痕的数组，若为下折痕则对应元素为"down",若为上折痕则为"up".
     * <p>
     * 例如：
     * N = 1时，打印：[Down]
     * N = 2时，打印：[Down、Down、Up]
     */
    public static void printAllFolds(int n) {
        printProcess(1, n, true);
    }

    private static void printProcess(int i, int n, boolean b) {
        if (i > n) {
            return;
        }
        printProcess(i + 1, n, true);
        System.out.println(b ? "Down" : "Up");
        printProcess(i + 1, n, false);
    }

    /**
     * 判断一颗二叉树是否为完全二叉树
     */
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        //是否遇到过左右孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            //System.out.println(cur.value);
            //如果遇到过叶节点,并且当前节点左或右孩子不为空，返回false,或者左节点为空，右节点不为空，返回false，其他情况继续
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 给定一颗二叉树的头节点，判断该树是否为平衡二叉树
     * 描述：平衡二叉树（Balanced Binary Tree）又被称为AVL树（有别于AVL算法），
     * 且具有以下性质：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     */
    public static class Info {
        private boolean isBal;
        private int height;

        public Info(boolean isBal, int height) {
            this.isBal = isBal;
            this.height = height;
        }
    }

    public static boolean isAVLTree(Node head) {
        return processIsBal(head).isBal;
    }

    private static Info processIsBal(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info left = processIsBal(head.left);
        Info right = processIsBal(head.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isBal = true;
        if (!left.isBal) {
            isBal = false;
        }
        if (!right.isBal) {
            isBal = false;
        }
        if (Math.abs(left.height - right.height) > 1) {
            isBal = false;
        }
        return new Info(isBal, height);
    }

    /**
     * 给定一颗二叉树的头节点，判断该树是否为搜索二叉树
     * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值
     */
    public static class InfoBST {
        private boolean isBST;
        private int max;
        private int min;

        public InfoBST(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        return processIsBST(head).isBST;
    }

    private static InfoBST processIsBST(Node head) {
        if (head == null) {
            return null;

        }
        InfoBST left = processIsBST(head.left);
        InfoBST right = processIsBST(head.right);
        int max = head.value;
        int min = head.value;
        boolean isBST = false;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            if (!left.isBST || left.max >= head.value) {
                isBST = false;
            }
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            if (!right.isBST || right.min <= head.value) {
                isBST = false;
            }
        }


        return new InfoBST(isBST, max, min);
    }

    /**
     * 给定一颗二叉树的头节点，任何两个节点之间都存在距离，返回整棵树的最大距离
     */
    public static class InfoMaxLen {
        private int max;
        private int height;

        public InfoMaxLen(int max, int height) {
            this.max = max;
            this.height = height;
        }
    }

    public static int betwMaxLen(Node head) {
        return processMaxLen(head).max;
    }

    private static InfoMaxLen processMaxLen(Node head) {
        if (head == null) {
            return new InfoMaxLen(0, 0);
        }
        InfoMaxLen left = processMaxLen(head.left);
        InfoMaxLen right = processMaxLen(head.right);
        int height = Math.max(left.height, right.height) + 1;
        int max = Math.max(left.max, right.max);
        max = Math.max(max, left.height + right.height + 1);
        return new InfoMaxLen(max, height);
    }

    /**
     * 给定一颗二叉树的头节点，判断该树是否为满二叉树
     */
    public static class InfoFull {
        private int n;
        private int height;

        public InfoFull(int n, int height) {
            this.n = n;
            this.height = height;
        }
    }

    public static boolean isFullTree(Node head) {
        InfoFull infoFull = processIsFT(head);
        return infoFull.n == (1 << infoFull.height) - 1;
    }

    private static InfoFull processIsFT(Node head) {
        if (head == null) {
            return new InfoFull(0, 0);
        }
        InfoFull left = processIsFT(head.left);
        InfoFull right = processIsFT(head.right);
        return new InfoFull(left.n + right.n + 1, Math.max(left.height, right.height) + 1);
    }

    /**
     *
     */
    public static class InfoMaxBST {
        private int maxBSTSize;
        private int allSize;
        private int max;
        private int min;

        public InfoMaxBST(int maxBSTSize, int allSize, int max, int min) {
            this.maxBSTSize = maxBSTSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public static int maxBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return processMaxBSTSize(head).maxBSTSize;
    }

    private static InfoMaxBST processMaxBSTSize(Node head) {
        if (head == null) {
            return null;

        }
        InfoMaxBST left = processMaxBSTSize(head.left);
        InfoMaxBST right = processMaxBSTSize(head.right);
        int max = head.value;
        int min = head.value;
        int allSize = 1;
        boolean isBST = true;
        int maxBSTSize = -1;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            allSize += left.allSize;

            maxBSTSize = Math.max(maxBSTSize, left.maxBSTSize);
            if (left.maxBSTSize != left.allSize || left.max >= head.value) {
                isBST = false;
            }
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            allSize += right.allSize;
            maxBSTSize = Math.max(maxBSTSize, right.maxBSTSize);
            if (right.maxBSTSize != right.allSize || right.min <= head.value) {
                isBST = false;
            }
        }
        if (isBST) {
            maxBSTSize = allSize;
        }
        return new InfoMaxBST(maxBSTSize, allSize, max, min);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxBSTSize(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
