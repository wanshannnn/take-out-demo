package com.demo.sky.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    // 支付超时交换机和队列
    public static final String PAYMENT_TIMEOUT_EXCHANGE = "order.payment.timeout.exchange";
    public static final String PAYMENT_TIMEOUT_QUEUE = "order.payment.timeout.queue";
    public static final String PAYMENT_TIMEOUT_ROUTING_KEY = "order.payment.timeout";

    // 派送超时交换机和队列
    public static final String DELIVERY_TIMEOUT_EXCHANGE = "order.delivery.timeout.exchange";
    public static final String DELIVERY_TIMEOUT_QUEUE = "order.delivery.timeout.queue";
    public static final String DELIVERY_TIMEOUT_ROUTING_KEY = "order.delivery.timeout";

    @Bean
    public DirectExchange paymentTimeoutExchange() {
        return new DirectExchange(PAYMENT_TIMEOUT_EXCHANGE);
    }

    @Bean
    public Queue paymentTimeoutQueue() {
        return QueueBuilder.durable(PAYMENT_TIMEOUT_QUEUE).build();
    }

    @Bean
    public Binding paymentTimeoutBinding() {
        return BindingBuilder.bind(paymentTimeoutQueue()).to(paymentTimeoutExchange()).with(PAYMENT_TIMEOUT_ROUTING_KEY);
    }

    @Bean
    public DirectExchange deliveryTimeoutExchange() {
        return new DirectExchange(DELIVERY_TIMEOUT_EXCHANGE);
    }

    @Bean
    public Queue deliveryTimeoutQueue() {
        return QueueBuilder.durable(DELIVERY_TIMEOUT_QUEUE).build();
    }

    @Bean
    public Binding deliveryTimeoutBinding() {
        return BindingBuilder.bind(deliveryTimeoutQueue()).to(deliveryTimeoutExchange()).with(DELIVERY_TIMEOUT_ROUTING_KEY);
    }
}
