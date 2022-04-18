package com.github.monsterhxw.chapter03.section03.linkedlist.leetcode203;

/**
 * @author Xuewei Huang
 * @created 2022-04-18
 */
public class RemoveLinkedListElements {

    static class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummyHead = new ListNode(-1, head);
            ListNode prev = dummyHead;
            while (prev != null && prev.next != null) {
                if (prev.next.val == val) {
                    ListNode delNode = prev.next;
                    prev.next = prev.next.next;
                    delNode.next = null;
                } else {
                    prev = prev.next;
                }
            }
            return dummyHead.next;
        }
    }

    static class Solution2 {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummyHead = new ListNode(-1, head);
            ListNode prev = dummyHead;
            while (prev != null && prev.next != null) {
                if (prev.next.val == val) {
                    ListNode delNode = prev.next;
                    prev.next = prev.next.next;
                    delNode.next = null;
                } else {
                    prev = prev.next;
                }
            }
            return dummyHead.next;
        }

    }
}