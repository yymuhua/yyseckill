package com.yyh.yyseckill.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.common.to.SeckillOrderTo;
import com.yyh.common.utils.PageUtils;
import com.yyh.yyseckill.product.entity.OrderEntity;

import java.util.Map;

/**
 * 订单表
 *
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-14 00:01:13
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void createSeckillOrder(SeckillOrderTo seckillOrderTo);
}

