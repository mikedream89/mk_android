package com.me.obo.routerguider;

import java.util.HashMap;
import java.util.Map;


final class BigDataManager {
    private static Map<String, Object> bigData = new HashMap<>();
    static void putBigData(String key, Object object) {
        bigData.put(key, object);
    }
    static Object removeBigData(String key) {
        return bigData.remove(key);
    }
}
