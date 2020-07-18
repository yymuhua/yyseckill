package com.yyh.yyseckill.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yyh.common.utils.R;
import com.yyh.yyseckill.seckill.constant.RedisConstant;
import com.yyh.yyseckill.seckill.feign.SeckillSessionFeignService;
import com.yyh.yyseckill.seckill.service.PutOnShellService;
import com.yyh.yyseckill.seckill.to.ProductRedisTo;
import com.yyh.yyseckill.seckill.vo.ProductVo;
import com.yyh.yyseckill.seckill.vo.SeckillSessionVo;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 1:04 上午
 */
@Service
public class PutOnSehllServiceImpl implements PutOnShellService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private SeckillSessionFeignService seckillSessionFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void putOnShellLatest3Days() {
        // 加分布式锁，锁住10s
        RLock lock = redissonClient.getLock(RedisConstant.PUTONSHELL_LOCK);
        lock.lock(10, TimeUnit.SECONDS);
        try {
            // 1、调用远程商品服务，获取最近三天所有秒杀活动
            R r = seckillSessionFeignService.getLatest3DaysSessions();
            if (r.getCode() == 0) {
                List<SeckillSessionVo> sessions = r.getData(new TypeReference<List<SeckillSessionVo>>() {
                });
                // 2、上架（缓存到Redis中）
                // 2.1 保存秒杀活动信息
                saveSessionInfos(sessions);
                // 2.2 保存秒杀商品信息
                saveProductInfo(sessions);
            }
        } finally {
            lock.unlock();
        }

    }

    /**
     * 保存秒杀活动信息
     * key：SESSIONS_CACHE_PREFIX + startTime + "_" + endTime
     * value：场次id + 关联的商品id
     *
     * @param sessions
     */
    private void saveSessionInfos(List<SeckillSessionVo> sessions) {
        if (CollectionUtils.isEmpty(sessions)) {
            return;
        }
        sessions.stream().forEach(session -> {
            Long startTime = session.getStartTime().getTime();
            Long endTime = session.getEndTime().getTime();
            String key = RedisConstant.SESSIONS_CACHE_PREFIX + startTime + "_" + endTime;
            // 幂等性保证，防止重复上架
            if (!redisTemplate.hasKey(key)) {
                String value = session.getId() + "_" + session.getProductId();
                redisTemplate.opsForList().leftPushAll(key, value);
            }
        });
    }

    /**
     * 缓存商品信息
     * key：商品id
     * value：商品对象JSON数据
     * 同时需要设置信号量
     *
     * @param sessions
     */
    private void saveProductInfo(List<SeckillSessionVo> sessions) {
        if (CollectionUtils.isEmpty(sessions)) {
            return;
        }
        sessions.stream().forEach(session -> {
            BoundHashOperations<String, Object, Object> ops =
                    redisTemplate.boundHashOps(RedisConstant.PRODUCT_CACHE_PREFIX);
            String key = session.getId() + "_" + session.getProductId();
            // 幂等性保证，如果已经加了该商品就无须再加
            if (ops.hasKey(key)) {
                return;
            }
            // 1、设置商品信息
            ProductRedisTo productRedisTo = new ProductRedisTo();
            ProductVo productVo = session.getProduct();
            BeanUtils.copyProperties(productVo, productRedisTo);
            // 设置实际秒杀库存、起止时间、单次购买数量上限等
            Integer stock = session.getTotal();
            productRedisTo.setStock(Long.valueOf(stock));
            productRedisTo.setSessionId(session.getId());
            productRedisTo.setStartTime(session.getStartTime());
            productRedisTo.setEndTime(session.getEndTime());
            productRedisTo.setLimitOnce(5);

            String randomCode = UUID.randomUUID().toString().replace("-", "");
            productRedisTo.setRandomCode(randomCode);
            ops.put(key, JSON.toJSONString(productRedisTo));

            // 2、上架库存信息
            RSemaphore semaphore =
                    redissonClient.getSemaphore(RedisConstant.PRODUCT_STOCK_SEMAPHORE + randomCode);
            // 库存即信号量值
            semaphore.trySetPermits(stock);
        });
    }
}
