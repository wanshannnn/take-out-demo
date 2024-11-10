package com.demo.sky.rabbitmq;

import com.demo.sky.config.RabbitMQConfiguration;
import com.demo.sky.dao.Orders;
import com.demo.sky.mapper.OrderMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RabbitMQConsumer {

    private final OrderMapper orderMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public RabbitMQConsumer(OrderMapper orderMapper, SimpMessagingTemplate messagingTemplate) {
        this.orderMapper = orderMapper;
        this.messagingTemplate = messagingTemplate;
    }

    // 支付超时消息监听
    @RabbitListener(queues = RabbitMQConfiguration.PAYMENT_TIMEOUT_QUEUE)
    public void handlePaymentTimeout(Long orderId) {
        Orders order = orderMapper.selectById(orderId);
        if (order != null && order.getStatus().equals(Orders.PENDING_PAYMENT)) {
            LocalDateTime now = LocalDateTime.now();
            if (order.getOrderTime().isBefore(now.minusMinutes(15))) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("支付超时，自动取消");
                order.setCancelTime(now);
                orderMapper.updateById(order);

                // 推送订单取消的消息给客户端
                messagingTemplate.convertAndSend("/topic/orderStatus", "订单 " + orderId + " 已被取消，原因：支付超时");
            }
        }
    }

    // 派送超时消息监听
    @RabbitListener(queues = RabbitMQConfiguration.DELIVERY_TIMEOUT_QUEUE)
    public void handleDeliveryTimeout(Long orderId) {
        Orders order = orderMapper.selectById(orderId);
        if (order != null && order.getStatus().equals(Orders.DELIVERY_IN_PROGRESS)) {
            LocalDateTime now = LocalDateTime.now();
            if (order.getOrderTime().isBefore(now.minusMinutes(60))) {
                order.setStatus(Orders.COMPLETED);
                orderMapper.updateById(order);

                // 推送订单完成的消息给客户端
                messagingTemplate.convertAndSend("/topic/orderStatus", "订单 " + orderId + " 已完成");
            }
        }
    }
}

