package com.yyh.yyseckill.seckill.to;

import lombok.Data;

import java.util.Date;

/**
 * 秒杀商品信息
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 5:15 下午
 */
@Data
public class ProductRedisTo {
    /**
     *
     */
    private Integer id;
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品编号
     */
    private String code;
    /**
     * 库存
     */
    private Long stock;
    /**
     * 采购时间
     */
    private Date purchaseTime;
    /**
     * 是否有效（1=是；0=否）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 秒杀开始时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;

    /**
     * 商品随机码
     */
    private String randomCode;

    /**
     * 单次购买的数量上限
     */
    private Integer limitOnce;
}
