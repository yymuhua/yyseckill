package com.yyh.yyseckill.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/14 12:18 上午
 */
@Configuration
public class RabbitMQConfig {

    @Autowired
    private Environment env;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 秒杀订单队列
     *
     * @return
     */
    @Bean
    public Queue seckillOrderQueue() {
        return new Queue(env.getProperty("mq.order.queue"), true, false, false);
    }

    /**
     * 秒杀订单绑定
     *
     * @return
     */
    @Bean
    public Binding seckillOrderBinding() {
        return new Binding(env.getProperty("mq.order.queue"),
                Binding.DestinationType.QUEUE,
                env.getProperty("mq.order.exchange"),
                env.getProperty("mq.order.routing.key"), null);
    }

    /**
     * 秒杀交换机
     *
     * @return
     */
    @Bean
    public TopicExchange seckillOrderExchange() {
        return new TopicExchange(env.getProperty("mq.order.exchange"), true, false);
    }
}
