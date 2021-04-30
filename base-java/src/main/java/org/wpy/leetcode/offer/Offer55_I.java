package org.wpy.leetcode.offer;

import org.wpy.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 例如：
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3<p>
 * / \<p>
 * 9  20<p>
 * /  \<p>
 * 15   7<p>
 * 返回它的最大深度3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer55_I {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        int depth = 1;
        int maxDepth = 1;
        stack.push(root);
        depthStack.push(1);
        while (!stack.empty()) {
            root = stack.pop();
            depth = depthStack.pop();
            if (depth > maxDepth) {
                maxDepth = depth;
            }
            if (root.left!=null) {
                stack.push(root.left);
                depthStack.push(depth+1);
            }
            if (root.right != null) {
                stack.push(root.right);
                depthStack.push(depth+1);
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);

        root.left = node1;
        root.right = node2;
//        node1.left = node3;
//        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        Offer55_I q = new Offer55_I();

        System.out.println(q.maxDepth(root));
    }
}
