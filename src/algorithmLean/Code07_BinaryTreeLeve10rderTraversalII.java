package algorithmLean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: 二叉树的很多题目
 * @Author: haole
 * @Date: 2021/7/29 11:11
 */
public class Code07_BinaryTreeLeve10rderTraversalII {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 题目：二叉树按层遍历并收集节点
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
     * 描述：给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            ans.add(0, curAns);
        }
        return ans;
    }

    // 以某个节点为头的时候 1）整棵树是否平 2）整棵树的高度是什么
    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            this.isBalanced = i;
            this.height = h;
        }
    }

    //判断一棵树是否为平衡二叉树
    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;

        return new Info(isBalanced, height);
    }

    public static class SearchInfo {
        public boolean isBST;
        public int min;
        public int max;

        public SearchInfo(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static SearchInfo isProcess(TreeNode x) {
        if (x == null) {
            return null;
        }
        SearchInfo leftInfo = isProcess(x.left);
        SearchInfo rightInfo = isProcess(x.right);

        int max = x.val;
        int min = x.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }

        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);

        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);

        if (!leftMaxLessX || !rightMinMoreX) {
            isBST = false;
        }
        return new SearchInfo(isBST, min, max);
    }

    /**
     * 题目：判断是否是平衡搜索二叉树
     * 链接：https://leetcode-cn.com/problems/balanced-binary-tree/
     * 描述：本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced && isProcess(root).isBST;
    }


    public static boolean isSum = false;

    /**
     * 题目：能否组成路径和
     * 链接：https://leetcode-cn.com/problems/path-sum/
     * 描述：给你二叉树的根节点root 和一个表示目标和的整数targetSum ，
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
     * 叶子节点 是指没有子节点的节点。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        isSum = false;
        isPathSum(root, 0, targetSum);
        return isSum;
    }

    private static void isPathSum(TreeNode root, int preSum, int targetSum) {
        if (root.left == null && root.right == null) {
            if (root.val + preSum == targetSum) {
                isSum = true;
            }
            return;
        }
        preSum += root.val;
        if (root.left != null) {
            isPathSum(root.left, preSum, targetSum);
        }
        if (root.right != null) {
            isPathSum(root.right, preSum, targetSum);
        }
    }


    /**
     * 题目：求所有组成路径和的路径
     * 链接：https://leetcode-cn.com/problems/path-sum-ii/
     * 描述：给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        getAllPath(root, path, 0, targetSum, ans);
        return ans;

    }

    public static void getAllPath(TreeNode x, List<Integer> path, int preSum, int sum, List<List<Integer>> ans) {
        if (x.left == null && x.right == null) {
            if (x.val + preSum == sum) {
                path.add(x.val);
                ans.add(copy(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(x.val);
        preSum += x.val;
        if (x.left != null) {
            getAllPath(x.left, path, preSum, sum, ans);
        }
        if (x.right != null) {
            getAllPath(x.right, path, preSum, sum, ans);
        }
        path.remove(path.size() - 1);
    }

    public static List<Integer> copy(List<Integer> path) {
        List<Integer> ans = new ArrayList<>();
        for (Integer num : path) {
            ans.add(num);
        }
        return ans;
    }
}
