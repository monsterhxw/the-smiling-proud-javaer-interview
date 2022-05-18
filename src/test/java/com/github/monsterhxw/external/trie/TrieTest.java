package com.github.monsterhxw.external.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuewei Huang
 * @created 2022-05-18
 */
class TrieTest {

    private Trie trie;

    private String[] words;

    @BeforeEach
    void setup() {
        trie = new Trie();
        words = new String[]{"trie", "github", "string", "tree"};
    }

    @Test
    void add() {
        for (String word : words) {
            trie.add(word);
        }
        assertEquals(words.length, trie.getSize());
    }

    @Test
    void contains() {
        add();
        for (String word : words) {
            assertTrue(trie.contains(word));
        }
    }
}