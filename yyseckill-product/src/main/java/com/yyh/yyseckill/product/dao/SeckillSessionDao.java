package com.yyh.yyseckill.product.dao;

import com.yyh.yyseckill.product.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动表
 * 
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:21
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {
	
}
