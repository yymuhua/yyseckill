package com.yyh.yyseckill.seckill.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 12:52 下午
 */
@Data
public class SeckillSessionVo {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 秒杀商品数
     */
    private Integer total;
    /**
     * 秒杀开始时间
     */
    private Date startTime;
    /**
     * 秒杀结束时间
     */
    private Date endTime;
    /**
     * 是否有效（1=是；0=否）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 关联的商品
     */
    private ProductVo product;
}
