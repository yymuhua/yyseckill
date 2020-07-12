package com.yyh.yyseckill.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.yyh.yyseckill.seckill.constant.RedisConstant;
import com.yyh.yyseckill.seckill.service.SessionService;
import com.yyh.yyseckill.seckill.to.ProductRedisTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 11:47 下午
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<ProductRedisTo> getCurrentProductRedisTo() {

        List<ProductRedisTo> productRedisTos = new ArrayList<>();

        long now = new Date().getTime();
        // 获取所有的场次，遍历筛选
        Set<String> keys = redisTemplate.keys(RedisConstant.SESSIONS_CACHE_PREFIX + "*");
        keys.forEach(key -> {
            String times = key.replace(RedisConstant.SESSIONS_CACHE_PREFIX, "");
            String[] s = times.split("_");
            Long startTime = Long.parseLong(s[0]);
            Long endTime = Long.parseLong(s[1]);
            if (now >= startTime && now <= endTime) {
                // 获取该场次所有的商品
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String, String, String> hashOps =
                        redisTemplate.boundHashOps(RedisConstant.PRODUCT_CACHE_PREFIX);
                List<String> products = hashOps.multiGet(range);
                if (!CollectionUtils.isEmpty(products)) {
                    productRedisTos.addAll(products.stream().map(product -> {
                        // JSON字符串转换为对象
                        ProductRedisTo productRedisTo = JSON.parseObject(product, ProductRedisTo.class);
                        return productRedisTo;
                    }).collect(Collectors.toList()));
                }

            }
        });
        return productRedisTos;
    }
}
