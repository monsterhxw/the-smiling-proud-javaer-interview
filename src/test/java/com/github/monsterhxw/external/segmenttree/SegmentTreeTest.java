package com.github.monsterhxw.external.segmenttree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-16
 */
class SegmentTreeTest {

    @Test
    void test() {
        Integer[] numbs = {1, 3, -2, 8, -7};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(numbs, Integer::sum);
        assertEquals(expected(), segmentTree.toString());
        System.out.println(segmentTree);
    }

    private String expected() {
        return "[3, 2, 1, 4, -2, 8, -7, 1, 3, null, null, null, null, null, null, null, null, null, null, null]";
    }
}