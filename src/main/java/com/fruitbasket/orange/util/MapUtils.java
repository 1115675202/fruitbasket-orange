package com.fruitbasket.orange.util;

/**
 * @author LiuBing
 * @date 2021/4/20
 */
public class MapUtils {

    /**
     * 根据将要放入Map、Set中的元素个数，计算起始容量，防止没必要的扩容
     *
     * @param elementCount 元素个数
     * @return 初始容量
     */
    public static final int capacity(int elementCount) {
        return elementCount * 4 / 3 + 1;
    }
}
