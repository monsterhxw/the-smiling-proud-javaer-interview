package com.github.monsterhxw.chapter03.section03.linkedlist.leetcode206;

/**
 * @author Xuewei Huang
 * @created 2022-04-21
 */
public class ReverseLinkedListRecursion {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverse = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }
}
