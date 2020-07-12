package com.yyh.yyseckill.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yyh.yyseckill.seckill.constant.RedisConstant;
import com.yyh.yyseckill.seckill.dto.KillDto;
import com.yyh.yyseckill.seckill.service.SeckillService;
import com.yyh.yyseckill.seckill.to.ProductRedisTo;
import com.yyh.yyseckill.seckill.vo.SeckillSessionVo;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 11:22 下午
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String kill(KillDto killDto) {
        // 1、获取当前秒杀商品的详细信息
        BoundHashOperations<String, String, String> hashOps =
                redisTemplate.boundHashOps(RedisConstant.PRODUCT_CACHE_PREFIX);
        String json = hashOps.get(killDto.getKillId());
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        ProductRedisTo productRedisTo = JSON.parseObject(json, ProductRedisTo.class);
        // 2、校验秒杀商品的合法性
        Long startTime = productRedisTo.getStartTime().getTime();
        Long endTime = productRedisTo.getEndTime().getTime();
        long now = new Date().getTime();
        // redis占位自动失效时间
        long ttl = endTime - now;
        // 2.1 时间合法性
        if (now >= startTime && now <= endTime) {
            // 2.2 随机码合法性
            String randomCode = productRedisTo.getRandomCode();
            if (!StringUtils.isEmpty(killDto.getKey()) && killDto.getKey().equals(randomCode)) {
                // 2.3 购物数量是否合法
                if (killDto.getNum() <= productRedisTo.getLimitOnce()) {
                    // 2.4 幂等性保证。确保每人每场的每个商品只能购买一次
                    String redisKey = killDto.getUserId() + "_" + killDto.getKillId();
                    // 自动过期
                    Boolean aBoolean =
                            redisTemplate.opsForValue().setIfAbsent(redisKey, killDto.toString(), ttl, TimeUnit.MILLISECONDS);
                    if (aBoolean) {
                        // 2.5 获取信号量
                        RSemaphore semaphore =
                                redissonClient.getSemaphore(RedisConstant.PRODUCT_STOCK_SEMAPHORE + randomCode);
                        try {
                            semaphore.tryAcquire(killDto.getNum(), 100, TimeUnit.MILLISECONDS);
                            // TODO 秒杀成功
                            String timeId = IdWorker.getTimeId();
                            return timeId;
                        } catch (InterruptedException e) {
                            return null;
                        }

                    }
                }
            }
        }
        return null;
    }
}
