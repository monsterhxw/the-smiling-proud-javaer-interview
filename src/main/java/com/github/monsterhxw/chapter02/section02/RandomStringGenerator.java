package com.github.monsterhxw.chapter02.section02;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author XueweiHuang
 * @created 2022-03-28
 */
public class RandomStringGenerator<T> implements Iterable<T> {

    private final List<T> list;

    private final Random random;

    public RandomStringGenerator(List<T> list) {
        this.list = list;
        random = new Random();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                int randomIndex = random.nextInt(list.size());
                return list.get(randomIndex);
            }
        };
    }
}
