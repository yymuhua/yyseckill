package com.yyh.yyseckill.seckill.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 1:01 上午
 */
@Data
public class KillDto {
    /**
     * 秒杀id = "sessionId_productId"
     */
    private String killId;

    /**
     * 商品随机码
     */
    private String key;

    /**
     * 用户id - 方便压力测试
     */
    private Integer userId;

    /**
     * 购买数量
     */
    private Integer num;
}
