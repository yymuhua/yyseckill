package com.yyh.yyseckill.seckill.service;

import com.yyh.yyseckill.seckill.to.ProductRedisTo;

import java.util.List;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 11:42 下午
 */
public interface SessionService {
    /**
     * 获取当前生效的所有秒杀商品
     * @return
     */
    List<ProductRedisTo> getCurrentProductRedisTo();
}
