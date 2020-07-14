package com.yyh.yyseckill.product.listener;

import com.rabbitmq.client.Channel;
import com.yyh.common.to.SeckillOrderTo;
import com.yyh.yyseckill.product.entity.OrderEntity;
import com.yyh.yyseckill.product.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/14 12:29 上午
 */
@Slf4j
@RabbitListener(queues = "order.seckill.order.queue")
@Component
public class SeckillOrderListener {
    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void listener(SeckillOrderTo seckillOrderTo, Channel channel, Message message) {
        log.info("开始创建秒杀单");
        orderService.createSeckillOrder(seckillOrderTo);
    }
}
