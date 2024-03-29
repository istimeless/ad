package com.ls.adsearch.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author lijiayin
 */
public class CommonUtils {
    
    public static <K, V> V getOrCreate(K key, Map<K, V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }
}
