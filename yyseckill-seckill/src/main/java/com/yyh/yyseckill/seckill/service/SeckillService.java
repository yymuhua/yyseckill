package com.yyh.yyseckill.seckill.service;

import com.yyh.yyseckill.seckill.dto.KillDto;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 11:22 下午
 */
public interface SeckillService {
    /**
     * 秒杀业务核心接口
     */
    String kill(KillDto killDto);
}
