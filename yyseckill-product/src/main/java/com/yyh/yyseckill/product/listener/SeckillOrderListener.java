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

import java.io.IOException;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/14 12:29 上午
 */
@Slf4j
@Component
public class SeckillOrderListener {
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "${mq.order.queue}")
    public void createOrderListener(SeckillOrderTo seckillOrderTo) {
        try {
            log.info("秒杀异步邮件通知-接收消息:{}", seckillOrderTo);
            // 创建订单
            orderService.createSeckillOrder(seckillOrderTo);
        } catch (Exception e) {
            log.error("秒杀异步邮件通知-接收消息-发生异常：",e.fillInStackTrace());
        }
    }
}
