package com.mk.coffee.utils;

import java.util.Collection;
import java.util.Objects;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class EmptyUtils {
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String string) {
        if (string == null || string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Objects[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(int[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        return false;
    }
}
