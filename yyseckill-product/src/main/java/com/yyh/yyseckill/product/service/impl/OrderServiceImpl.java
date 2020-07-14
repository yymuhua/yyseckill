package com.yyh.yyseckill.product.service.impl;

import com.yyh.common.to.SeckillOrderTo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.common.utils.PageUtils;
import com.yyh.common.utils.Query;

import com.yyh.yyseckill.product.dao.OrderDao;
import com.yyh.yyseckill.product.entity.OrderEntity;
import com.yyh.yyseckill.product.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void createSeckillOrder(SeckillOrderTo seckillOrderTo) {
        OrderEntity entity = new OrderEntity();
        entity.setCode(seckillOrderTo.getCode());
        entity.setNum(seckillOrderTo.getNum());
        entity.setProductId(seckillOrderTo.getProductId());
        entity.setSeckillId(seckillOrderTo.getSessionId());
        entity.setUserId(seckillOrderTo.getUserId().toString());
        entity.setCreateTime(new Date());
        entity.setStatus(0);
        super.save(entity);
        // 订单保存完之后立即减库存

    }

}