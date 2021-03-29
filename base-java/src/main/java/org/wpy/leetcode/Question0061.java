package org.wpy.leetcode;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 */
public class Question0061 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode cur = this;
            while (cur.next != null) {
                sb.append(cur.val).append(", ");
                cur = cur.next;
            }
            sb.append(cur.val);
            return sb.toString();
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        int count = 1; // 用来统计链表总结点数
        ListNode tmp = head;
        while (tmp.next != null) {
            count++;
            tmp = tmp.next;
        }
        k %= count;
        // 当k为0时，不需要旋转，
        if (k == 0) return head;

        // 不满足上述条件，必将进行旋转，所以先将首尾相连
        tmp.next = head;
        // 找到倒数第k+1个节点
        for (int i = 0; i < count - k; i++) {
            tmp = tmp.next;
        }
        ListNode newHead = tmp.next;
        tmp.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
        System.out.println(new Question0061().rotateRight(head, 2));
    }
}
