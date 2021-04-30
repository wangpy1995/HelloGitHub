package org.wpy.leetcode.offer;

import org.wpy.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * <p>
 * 给定一棵二叉搜索树，请找出其中第k大的节点。<p>
 * 示例 1:<p>
 * 输入: root = [3,1,4,null,2], k = 1<p>
 * 3<p>
 * / \<p>
 * 1   4<p>
 * \<p>
 * 2<p>
 * 输出: 4<p>
 * 示例 2:<p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3<p>
 * 5<p>
 * / \<p>
 * 3   6<p>
 * / \<p>
 * 2   4<p>
 * /<p>
 * 1<p>
 * 输出: 4<p>
 * 限制：<p>
 * 1 ≤ k ≤ 二叉搜索树元素个数<p>
 * <p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。<p>
 */
public class Offer54 {
    /**
     * 从右后序遍历
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root.right != null) {
            stack.push(root.right);
            root = root.right;
        }
        TreeNode node = null;
        while (!stack.empty()) {
            node = stack.pop();
            k--;
            if (k == 0) {
                return node.val;
            }
            if (node.left != null) {
                stack.push(node.left);
                node = node.left;
                while (node.right != null) {
                    stack.push(node.right);
                    node = node.right;
                }
            }
        }
        if(node==null){
            return 0;
        }
        return node.val;
    }

    public static void main(String[] args) {
        /*
        TreeNode node4 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1, null, node4);
        TreeNode node2 = new TreeNode(4);
        TreeNode root = new TreeNode(3, node1, node2);
        */
        TreeNode node7 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2, node7, null);
        TreeNode node2 = new TreeNode(6);
        TreeNode node1 = new TreeNode(3, node3, node4);
        TreeNode root = new TreeNode(5, node1, node2);
        System.out.println(new Offer54().kthLargest(root, 3));
    }
}
