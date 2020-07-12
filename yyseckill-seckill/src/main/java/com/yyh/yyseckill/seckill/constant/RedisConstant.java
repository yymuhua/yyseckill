package com.yyh.yyseckill.seckill.constant;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 11:34 下午
 */
public class RedisConstant {
    public static final String SESSIONS_CACHE_PREFIX = "seckill:sessions";

    public static final String PRODUCT_CACHE_PREFIX = "seckill:product";

    public static final String PRODUCT_STOCK_SEMAPHORE = "seckill:stock:";

    public static final String PUTONSHELL_LOCK = "seckill:putonshell:lock";
}
