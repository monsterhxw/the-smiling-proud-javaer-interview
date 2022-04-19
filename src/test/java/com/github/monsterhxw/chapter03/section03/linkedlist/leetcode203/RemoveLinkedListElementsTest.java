package com.github.monsterhxw.chapter03.section03.linkedlist.leetcode203;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Xuewei Huang
 * @created 2022-04-18
 */
class RemoveLinkedListElementsTest {

    private ListNode head;

    private int[] headVal;

    @BeforeEach
    void setUp() {
        headVal = new int[]{1, 2, 6, 3, 4, 5, 6};
        ListNode tail = head;
        for (int i : headVal) {
            if (tail == null) {
                head = new ListNode(i);
                tail = head;
            } else {
                tail.next = new ListNode(i);
                tail = tail.next;
            }
        }
    }

    @Test
    public void testSolution() {
        ListNode result = new RemoveLinkedListElements.Solution().removeElements(head, 6);
        int[] resultVal = new int[5];
        ListNode cur = result;
        int i = 0;
        while (cur != null) {
            resultVal[i] = cur.val;
            cur = cur.next;
            i++;
        }
        String s = Arrays.toString(resultVal);
        System.out.println(s);
        assertEquals(s, "[1, 2, 3, 4, 5]");
    }

    @Test
    public void testSolution2() {
        ListNode result = new RemoveLinkedListElements.Solution2().removeElements(head, 6);
        int[] resultVal = new int[5];
        ListNode cur = result;
        int i = 0;
        while (cur != null) {
            resultVal[i] = cur.val;
            cur = cur.next;
            i++;
        }
        String s = Arrays.toString(resultVal);
        System.out.println(s);
        assertEquals(s, "[1, 2, 3, 4, 5]");
    }

    @Test
    public void testSolution3() {
        ListNode result = new RemoveLinkedListElements.Solution3().removeElements(head, 6);
        int[] resultVal = new int[5];
        ListNode cur = result;
        int i = 0;
        while (cur != null) {
            resultVal[i] = cur.val;
            cur = cur.next;
            i++;
        }
        String s = Arrays.toString(resultVal);
        System.out.println(s);
        assertEquals(s, "[1, 2, 3, 4, 5]");
    }
}