package com.demo.sky.rabbitmq;

import com.demo.sky.config.RabbitMQConfiguration;
import com.demo.sky.dao.Orders;
import com.demo.sky.mapper.OrderMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;
    private final OrderMapper orderMapper;

    public RabbitMQService(RabbitTemplate rabbitTemplate, OrderMapper orderMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderMapper = orderMapper;
    }

    public void createOrder(Orders order) {
        // 保存订单信息到数据库
        orderMapper.insert(order);

        // 发送支付超时延时消息，延时15分钟
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DELAY_EXCHANGE, RabbitMQConfiguration.ROUTING_KEY, order.getId(), message -> {
            message.getMessageProperties().setDelay(15 * 60 * 1000); // 15分钟后触发
            return message;
        });
    }

    public void updateOrderStatusToDeliveryInProgress(Long orderId) {
        Orders order = orderMapper.selectById(orderId);
        order.setStatus(Orders.DELIVERY_IN_PROGRESS);
        orderMapper.updateById(order);

        // 发送派送超时延时消息，延时60分钟
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DELAY_EXCHANGE, RabbitMQConfiguration.ROUTING_KEY, order.getId(), message -> {
            message.getMessageProperties().setDelay(60 * 60 * 1000); // 60分钟后触发
            return message;
        });
    }
}
