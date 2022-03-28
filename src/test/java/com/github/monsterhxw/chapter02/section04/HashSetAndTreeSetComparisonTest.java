package com.github.monsterhxw.chapter02.section04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-03-28
 */
class HashSetAndTreeSetComparisonTest {

    private static final HashSet<Integer> INT_HASH_SET = new HashSet<>();
    private static final TreeSet<Integer> INT_TREE_SET = new TreeSet<>();

    private static final HashSet<String> STR_HASH_SET = new HashSet<>();
    private static final TreeSet<String> STR_TREE_SET = new TreeSet<>();

    private static final Random random = new Random();

    @BeforeEach
    public void clearHashSetAndTreeSet() {
        INT_HASH_SET.clear();
        INT_TREE_SET.clear();
        STR_HASH_SET.clear();
        STR_TREE_SET.clear();
    }

    @Test
    public void hashSetAndTressSetOrder() {
        int n = 10;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = Math.abs(random.nextInt(200 + 1)) + i;
        }
        arr[n - 1] = arr[n - 2];
        String arrStr = Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("Array to string is: " + arrStr + ", length is: " + arr.length);

        INT_HASH_SET.addAll(Arrays.asList(arr));
        String hashSetStr = INT_HASH_SET.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("Hash set to string is: " + hashSetStr + ", length is: " + INT_HASH_SET.size());

        assertNotEquals(arr.length, INT_HASH_SET.size());

        INT_TREE_SET.addAll(Arrays.asList(arr));
        String treeSetStr = INT_TREE_SET.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("Tree set to string is: " + treeSetStr + ", length is: " + INT_TREE_SET.size());
        Integer[] treeSetToArray = INT_TREE_SET.toArray(Integer[]::new);

        assertEquals(INT_HASH_SET.size(), INT_TREE_SET.size());
        assertNotEquals(hashSetStr, treeSetStr);

        validateSorted(treeSetToArray);
    }

    @Test
    public void hashSetAndTreeSetBenchmark() {
        int n = 1000000;
        LinkedList<String> words = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            var word = random.ints(97, 123)
                    .limit(12)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            words.add(word);
        }
        // test hash set
        var startTime = System.currentTimeMillis();
        for (var word : words) {
            STR_HASH_SET.add(word);
        }
        for (var word : words) {
            STR_HASH_SET.contains(word);
        }
        long hashSetCostTimeMillis = System.currentTimeMillis() - startTime;
        System.out.println("Hash Set cost time: " + hashSetCostTimeMillis);
        // test tree set
        startTime = System.currentTimeMillis();
        for (var word : words) {
            STR_TREE_SET.add(word);
        }
        for (var word : words) {
            STR_TREE_SET.contains(word);
        }
        long treeSetCostTimeMillis = System.currentTimeMillis() - startTime;
        System.out.println("Tree Set cost time: " + treeSetCostTimeMillis);

        assertTrue(hashSetCostTimeMillis < treeSetCostTimeMillis);
    }


    private void validateSorted(Integer[] arr) {
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            assertTrue(arr[i] >= arr[i - 1]);
        }
    }
}