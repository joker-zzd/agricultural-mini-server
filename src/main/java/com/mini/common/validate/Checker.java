package com.mini.common.validate;

public interface Checker<T> {
    /**
     * 用于实现validation不能校验的数据逻辑
     */
    default void check() {

    }

    default void check(T data) {
    }
}
