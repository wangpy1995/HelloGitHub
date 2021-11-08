package org.wpy.leetcode.offer;

import org.wpy.leetcode.common.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p> * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * <p> * 示例 1：
 * <p> * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p> * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p> * 示例 2：
 * <p> * 输入：head = [[1,1],[2,1]]
 * <p> * 输出：[[1,1],[2,1]]
 * <p> * 示例 3：
 * <p> * 输入：head = [[3,null],[3,0],[3,null]]
 * <p> * 输出：[[3,null],[3,0],[3,null]]
 * <p> * 示例 4：
 * <p> * 输入：head = []
 * <p> * 输出：[]
 * <p> * 解释：给定的链表为空（空指针），因此返回 null。
 * <p> * 提示：
 * <p> * -10000 <= Node.val <= 10000
 * <p> * Node.random为空（null）或指向链表中的节点。
 * <p> * 节点数目不超过 1000 。
 * <p> *
 * <p> * 来源：力扣（LeetCode）
 * <p> * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * <p> * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 */
public class Offer35 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node newNode = new Node(cur.val);
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            if (cur.next != null) {
                Node next = cur.next;
                Node random = cur.random;
                if(random!=null) {
                    next.random = random.next;
                }
                cur = next.next;
            } else {
                cur = null;
            }
        }
        Node newHead = head.next;
        cur = head;
        while (cur.next != null) {
            Node temp = cur.next;
            if (temp.next != null) {
                cur.next = temp.next;
                temp.next = temp.next.next;
            }else{
                cur.next=null;
            }
        }
        return newHead;
    }

    public static void main(String[] args) {

    }
}