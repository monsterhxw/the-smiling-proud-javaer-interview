package com.github.monsterhxw.chapter03.section02;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-04-02
 */
class SortTest {

    private static final Random RANDOM = new Random();

    private static final int THRESHOLD = 10_000;

    @Test
    public void insertionSort() {
        sortTest(InsertionSort.class, THRESHOLD);
    }

    @Test
    public void selectionSort() {
        sortTest(SelectionSort.class, THRESHOLD);
    }

    @Test
    public void bubbleSort() {
        sortTest(BubbleSort.class, THRESHOLD);
    }

    @Test
    public void mergeSort() {
        sortTest(MergeSort.class, THRESHOLD);
    }

    @Test
    public void quickSort() {
        sortTest(QuickSort.class, THRESHOLD);
    }

    @Test
    public void quickSort2Way() {
        sortTest(QuickSort2Way.class, THRESHOLD);
    }

    @Test
    public void quickSort3Way() {
        sortTest(QuickSort3Way.class, THRESHOLD);
    }

    private <T> void sortTest(Class<T> clazz, int n) {
        try {
            var inst = clazz.getConstructor().newInstance();
            if (inst instanceof IMutableSorter) {
                int[] A = generateRandom(n, THRESHOLD).stream().mapToInt(x -> x).toArray();
                var startTime = System.currentTimeMillis();
                ((IMutableSorter) inst).sort(A);
                System.out.format("Random: %s time usage = %dms.\n", clazz.getSimpleName(), (System.currentTimeMillis() - startTime));
                assertSorted(A);

                startTime = System.currentTimeMillis();
                ((IMutableSorter) inst).sort(A);
                System.out.format("Ordered: %s time usage = %dms.\n", clazz.getSimpleName(), (System.currentTimeMillis() - startTime));
                assertSorted(A);

                if (inst instanceof QuickSort) {
                    System.out.println();
                    return;
                }
                startTime = System.currentTimeMillis();
                A = new int[n];
                ((IMutableSorter) inst).sort(A);
                System.out.format("Same: %s time usage = %dms.\n\n", clazz.getSimpleName(), (System.currentTimeMillis() - startTime));
                assertSorted(A);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        }
    }

    private void assertSorted(int[] A) {
        assertSorted(Arrays.stream(A).boxed().collect(Collectors.toList()));
    }

    private void assertSorted(List<Integer> A) {
        for (int i = 1; i < A.size(); i++) {
            assertTrue(A.get(i) >= A.get(i - 1));
        }
    }

    private List<Integer> generateRandom(int n, int threshold) {
        var res = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            res.add(RANDOM.nextInt(threshold));
        }
        return res;
    }
}