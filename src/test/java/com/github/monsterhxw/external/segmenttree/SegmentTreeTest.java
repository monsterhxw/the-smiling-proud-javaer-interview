package com.github.monsterhxw.external.segmenttree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-16
 */
class SegmentTreeTest {

    private Integer[] numbs;

    private BiFunction<Integer, Integer, Integer> merger;

    private SegmentTree<Integer> segmentTree;

    @BeforeEach
    void setup() {
        numbs = new Integer[]{1, 3, -2, 8, -7};
        merger = (x1, x2) -> x1 + x2;
        segmentTree = new SegmentTree<>(numbs, merger);
    }

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

    @Test
    void set() {
        segmentTree.set(4, 7);
        assertEquals("[17, 2, 15, 4, -2, 8, 7, 1, 3, null, null, null, null, null, null, null, null, null, null, null]", segmentTree.toString());
        System.out.println(segmentTree);
    }

    private String expected() {
        return "[3, 2, 1, 4, -2, 8, -7, 1, 3, null, null, null, null, null, null, null, null, null, null, null]";
    }
}