package com.github.monsterhxw.chapter02.section02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-03-28
 */
class RandomStringGeneratorTest {

    @Test
    public void randomStringGeneratorTest() {
        var list = Arrays.asList("List", "Tree", "Array", "LinkedList");
        var generator = new RandomStringGenerator<>(list);

        Iterator<String> iterator = generator.iterator();
        var randomStrList = new ArrayList<>();
        int n = 100;
        for (int i = 0; i < n; i++) {
            randomStrList.add(iterator.next());
        }
        assertEquals(n, randomStrList.size());
    }
}