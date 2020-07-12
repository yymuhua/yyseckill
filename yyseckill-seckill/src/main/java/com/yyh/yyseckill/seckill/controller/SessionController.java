package com.yyh.yyseckill.seckill.controller;

import com.yyh.common.utils.R;
import com.yyh.yyseckill.seckill.service.SessionService;
import com.yyh.yyseckill.seckill.to.ProductRedisTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 秒杀活动Controller
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 11:30 下午
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    /**
     * 获取当前时间所有的秒杀商品
     * @return
     */
    @GetMapping(value = "/list")
    public R list() {
        List<ProductRedisTo> productRedisTo = sessionService.getCurrentProductRedisTo();
        return R.ok().setData(productRedisTo);
    }
}
