package com.yyh.common.to;

import lombok.Data;

/**
 * 秒杀订单TO
 *
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/13 11:37 下午
 */
@Data
public class SeckillOrderTo {

    /**
     * 订单号
     */
    private String code;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 秒杀的场次id
     */
    private Integer sessionId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品数量
     */
    private Integer num;
}
