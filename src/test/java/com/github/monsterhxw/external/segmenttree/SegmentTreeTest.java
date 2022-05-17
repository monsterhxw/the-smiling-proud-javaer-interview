package com.github.monsterhxw.external.segmenttree;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-16
 */
class SegmentTreeTest {

    private final Integer[] numbs = {1, 3, -2, 8, -7};

    private final BiFunction<Integer, Integer, Integer> merger = (x1, x2) -> x1 + x2;

    private final SegmentTree<Integer> segmentTree = new SegmentTree<>(numbs, merger);

    @Test
    void create() {
        assertEquals(expected(), segmentTree.toString());
        System.out.println(segmentTree);
    }

    @Test
    void query() {
        assertEquals(3, segmentTree.query(0, 4));
        System.out.println(segmentTree.query(0, 4));
        assertEquals(4, segmentTree.query(0, 1));
        System.out.println(segmentTree.query(0, 1));
    }

    private String expected() {
        return "[3, 2, 1, 4, -2, 8, -7, 1, 3, null, null, null, null, null, null, null, null, null, null, null]";
    }
}