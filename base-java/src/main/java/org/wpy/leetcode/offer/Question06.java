package org.wpy.leetcode.offer;

import org.wpy.leetcode.common.ListNode;

import java.util.Arrays;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * 限制：
 * 0 <= 链表长度 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question06 {

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ListNode secondNode = head.next;
        if (secondNode == null) {
            return new int[]{head.val};
        } else {
            int len = 2;
            ListNode node = secondNode;
            while (node.next != null) {
                len++;
                node = node.next;
            }
            int[] res = new int[len];
            res[len - 1] = head.val;
            res[0] = node.val;
            for (int i = len - 2; i > 0; i--) {
                res[i] = secondNode.val;
                secondNode = secondNode.next;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next= node3;
        System.out.println(Arrays.toString(new Question06().reversePrint(node1)));
    }
}
