package algorithmLean;

import java.util.HashMap;

/**
 * @Description: 二叉树递归
 * @Author: haole
 * @Date: 2021/7/28 17:32
 */
public class Code06_TraversalBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 题目：相同的树
     * 链接：https://leetcode-cn.com/problems/same-tree/
     * 描述：给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 题目：镜面树
     * 链接：https://leetcode-cn.com/problems/symmetric-tree/
     * 描述：给定一个二叉树，检查它是否是镜像对称的
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    /**
     * 题目：返回一棵树的深度
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * 描述：给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点
     *
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 题目：用先序数组和中序数组重建一棵树
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * 描述：给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
     *
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        return findTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 有一棵树，先序遍历是pre[L1...R1],中序遍历是ino[L2....R2]
     * 建好整棵树返回
     */
    public static TreeNode findTree(int[] pre, int L1, int R1, int[] ino, int L2, int R2) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int find = L2;
//        for (; ino[find] != pre[L1]; find++) ;
        while (ino[find] != pre[L1]) {
            find++;
        }
        head.left = findTree(pre, L1 + 1, L1 + find - L2, ino, L2, find - 1);
        head.right = findTree(pre, L1 + find - L2 + 1, R1, ino, find + 1, R2);
        return head;
    }

    /**
     * 方法二
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndexMap.put(inorder[i], i);
        }
        return findTreeByMap(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, valueIndexMap);
    }

    /**
     * 有一棵树，先序遍历是pre[L1...R1],中序遍历是ino[L2....R2]
     * 建好整棵树返回
     */
    public static TreeNode findTreeByMap(int[] pre, int L1, int R1, int[] ino, int L2, int R2,
                                         HashMap<Integer, Integer> valueIndexMap) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int find = valueIndexMap.get(pre[L1]);
        head.left = findTreeByMap(pre, L1 + 1, L1 + find - L2, ino, L2, find - 1, valueIndexMap);
        head.right = findTreeByMap(pre, L1 + find - L2 + 1, R1, ino, find + 1, R2, valueIndexMap);
        return head;
    }

    public static void printTree(TreeNode head) {
        if (head == null) {
            return;
        }
        //System.out.println(head.val);先序遍历（头左右）
        printTree(head.left);
        //中序遍历（左头右）
        printTree(head.right);
        //后序遍历（左右头）
    }

    public static void main(String[] args) {

    }
}
