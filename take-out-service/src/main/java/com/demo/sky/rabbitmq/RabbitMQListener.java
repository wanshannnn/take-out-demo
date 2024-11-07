package com.demo.sky.rabbitmq;

import com.demo.sky.config.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.demo.sky.dao.Orders;
import com.demo.sky.mapper.OrderMapper;

import java.time.LocalDateTime;

@Component
public class RabbitMQListener {

    private final OrderMapper orderMapper;
    private final SimpMessagingTemplate messagingTemplate;


    public RabbitMQListener(OrderMapper orderMapper, SimpMessagingTemplate messagingTemplate) {
        this.orderMapper = orderMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = RabbitMQConfiguration.DELAY_QUEUE)
    public void handleOrderTimeout(Long orderId) {
        Orders order = orderMapper.selectById(orderId);

        if (order != null) {
            LocalDateTime now = LocalDateTime.now();

            // 如果订单是待支付状态且超时
            if (order.getStatus().equals(Orders.PENDING_PAYMENT) && order.getOrderTime().isBefore(now.minusMinutes(15))) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("支付超时，自动取消");
                order.setCancelTime(now);
                orderMapper.updateById(order);

                // 推送订单取消的消息给客户端
                messagingTemplate.convertAndSend("/topic/orderStatus", "订单 " + orderId + " 已被取消，原因：支付超时");

            // 如果订单是派送中状态且超时
            } else if (order.getStatus().equals(Orders.DELIVERY_IN_PROGRESS) && order.getOrderTime().isBefore(now.minusMinutes(60))) {
                order.setStatus(Orders.COMPLETED);
                orderMapper.updateById(order);

                // 推送订单完成的消息给客户端
                messagingTemplate.convertAndSend("/topic/orderStatus", "订单 " + orderId + " 已完成");
            }
        }
    }

}
