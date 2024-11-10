package com.demo.sky.rabbitmq;

import com.demo.sky.config.RabbitMQConfiguration;
import com.demo.sky.dao.Orders;
import com.demo.sky.mapper.OrderMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final OrderMapper orderMapper;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate, OrderMapper orderMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderMapper = orderMapper;
    }

    public void createOrder(Orders order) {
        // 发送支付超时延时消息
        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.PAYMENT_TIMEOUT_EXCHANGE,
                RabbitMQConfiguration.PAYMENT_TIMEOUT_ROUTING_KEY,
                order.getId(),
                message -> {
                    message.getMessageProperties().setDelay(15 * 60 * 1000); // 15分钟后触发
                    return message;
                }
        );
    }

    public void updateOrderStatusToDeliveryInProgress(Long id) {
        Orders order = orderMapper.selectById(id);

        // 发送派送超时延时消息
        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.DELIVERY_TIMEOUT_EXCHANGE,
                RabbitMQConfiguration.DELIVERY_TIMEOUT_ROUTING_KEY,
                order.getId(),
                message -> {
                    message.getMessageProperties().setDelay(60 * 60 * 1000); // 60分钟后触发
                    return message;
                }
        );
    }
}

