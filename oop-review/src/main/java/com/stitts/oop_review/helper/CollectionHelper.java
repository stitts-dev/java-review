package com.stitts.oop_review.helper;

import java.util.Collection;

public class CollectionHelper {
    public  static <T> boolean isNullOrEmpty(Collection<T> list){
        return list != null && list.size() > 0;
    }

    public static <T> boolean isNotNullOrEmpty(Collection<T> list){
        return !isNullOrEmpty(list);
    }
}
