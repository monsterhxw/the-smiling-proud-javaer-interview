package com.github.monsterhxw.chapter02.section06;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-03-29
 */
class StreamPracticeTest {

    private static final Random random = new Random();

    @Test
    public void testParallel() throws ExecutionException, InterruptedException {
        int upperBound = 1_000_000;
        var list = IntStream.range(0, upperBound)
                .map(i -> random.nextInt(upperBound))
                .boxed()
                .collect(Collectors.toList());
        assertEquals(upperBound, list.size());

        long startTime = System.currentTimeMillis();
        System.out.println(list.stream().max(Comparator.comparingInt(i -> i)));
        System.out.println("sequence cost time: " + (System.currentTimeMillis() - startTime));

        // parallel
        startTime = System.currentTimeMillis();
        list.parallelStream().max(Comparator.comparingInt(i -> i));
        System.out.println("parallel cost time: " + (System.currentTimeMillis() - startTime));
        // parallel default processors is core processors - 1

        System.out.println("processors: " + Runtime.getRuntime().availableProcessors());

        var pool = new ForkJoinPool(2);
        startTime = System.currentTimeMillis();
        var max = pool.submit(() -> list.parallelStream().max(Comparator.naturalOrder())).get();
        System.out.println("fork join pool cost time: " + (System.currentTimeMillis() - startTime) + ", max: " + max);
    }
}