package com.github.monsterhxw.chapter02.section04;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-03-29
 */
class LRUCacheTest {

    @Test
    public void test() {
        int max = 3;
        var lruCache = new LRUCache<String, Integer>(max);

        lruCache.put("A", 1);
        lruCache.put("B", 2);
        lruCache.put("C", 3);

        lruCache.put("D", 4);
        lruCache.put("C", 10);

        assertEquals(lruCache.size(), max);

        System.out.println("leave <- " +
                StreamSupport.stream(lruCache.spliterator(), false)
                        .map(Objects::toString)
                        .collect(Collectors.joining(" <- ")));
    }
}