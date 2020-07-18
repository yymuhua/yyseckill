package com.yyh.yyseckill.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 订单表
 *
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-14 00:01:13
 */
@Data
@TableName("order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId
    private String code;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 商品数量
     */
    private Integer num;
    /**
     * 秒杀场次id
     */
    private Integer seckillId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 秒杀结果: -1无效  0成功(未付款)  1已付款  2已取消
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
