package com.yyh.yyseckill.seckill.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 12:53 下午
 */
@Data
public class ProductVo {
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
}
