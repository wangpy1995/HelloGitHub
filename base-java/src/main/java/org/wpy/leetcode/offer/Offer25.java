package org.wpy.leetcode.offer;

import org.wpy.leetcode.common.ListNode;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。<p>
 * 示例1：<p>
 * 输入：1->2->4, 1->3->4<p>
 * 输出：1->1->2->3->4->4<p>
 * 限制：<p>
 * 0 <= 链表长度 <= 1000<p>
 * <p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode node = new ListNode(-1);
        ListNode pre = node;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                pre = pre.next;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = pre.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            pre.next = l1;
        }
        if (l2 != null) {
            pre.next = l2;
        }
        return node.next;
    }

    public static void main(String[] args) {
        /**
         *  输入：1->2->4, 1->3->4<p>
         *  输出：1->1->2->3->4->4<p>
         */
        ListNode l3 = new ListNode(4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);

        ListNode r3 = new ListNode(4);
        ListNode r2 = new ListNode(3, r3);
        ListNode r1 = new ListNode(1, r2);

        ListNode l = new Offer25().mergeTwoLists(l1, r1);
        while (l != null) {
            System.out.print(l.val + "->");
            l = l.next;
        }
    }
}
