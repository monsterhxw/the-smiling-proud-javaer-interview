package com.github.monsterhxw.chapter02.section04;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author XueweiHuang
 * @created 2022-03-29
 */
public class LRUCache<K, V> implements Iterable<K> {

    private int capacity;

    private LinkedHashMap<K, V> cache;

    public LRUCache() {
        this(3);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        } else if (cache.size() >= capacity) {
            var iterator = cache.keySet().iterator();
            K firstKey = iterator.next();
            cache.remove(firstKey);
        }
        cache.put(key, value);
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        V v = cache.get(key);
        put(key, v);
        return v;
    }

    public int size() {
        return cache.size();
    }

    @Override
    public Iterator<K> iterator() {
        return cache.keySet().iterator();
    }
}
