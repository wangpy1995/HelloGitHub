package org.wpy.leetcode.offer;

import org.wpy.leetcode.common.ListNode;

/**
 * <p>
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。<p>
 * 示例:<p>
 * 输入: 1->2->3->4->5->NULL<p>
 * 输出: 5->4->3->2->1->NULL<p>
 * 限制：<p>
 * 0 <= 节点个数 <= 5000<p>
 * <p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。<p>
 */

public class Offer24 {
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next == null) {
            return head;
        }
        ListNode preNode = head;
        ListNode curNode = head.next;
        ListNode nextNode;
        while (curNode != null) {
            //保存下一个节点
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        System.out.println(new Offer24().reverseList(node1).val);
    }
}
