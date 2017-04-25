package com.mk.coffee.utils;

import java.util.Collection;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }
}
