package org.wpy.leetcode.offer;

import org.wpy.leetcode.common.TreeNode;

/**
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 例如输入：
 * <p>
 * 4<p>
 * /  \<p>
 * 2   7<p>
 * / \  / \<p>
 * 1  3 6  9<p>
 * 镜像输出：
 * <p>
 * 4<p>
 * /  \<p>
 * 7   2<p>
 * / \  / \<p>
 * 9  6 3 1<p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [4,2,7,1,3,6,9]<p>
 * 输出：[4,7,2,9,6,3,1]<p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer27 {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode left = root.left;
        // 由于函数带返回值，交换操作代码可以合并到同一行
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(left);
        return root;
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
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        new Offer27().mirrorTree(root);
        System.out.println(root);
    }
}
