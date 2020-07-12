package com.yyh.yyseckill.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 秒杀活动表
 * 
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:21
 */
@Data
@TableName("seckill_session")
public class SeckillSessionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
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
	@TableField(exist = false)
	private ProductEntity product;
}
