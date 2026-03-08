package com.mini.common.constant;

public interface ProductRedisConstant {
    String PRODUCT_DETAIL_KEY_PREFIX = "product:detail:";
    String CACHE_NULL_VALUE = "null";

    long PRODUCT_DETAIL_TTL_MINUTES = 30L;
    long PRODUCT_DETAIL_NULL_TTL_MINUTES = 5L;

    static String productDetailKey(Long productId) {
        return PRODUCT_DETAIL_KEY_PREFIX + productId;
    }
}
