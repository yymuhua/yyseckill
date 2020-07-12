package com.yyh.yyseckill.product.dao;

import com.yyh.yyseckill.product.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 * 
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:21
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
