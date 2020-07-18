package com.yyh.yyseckill.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 商品表
 *
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:21
 */
@Data
@TableName("product")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
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

}
